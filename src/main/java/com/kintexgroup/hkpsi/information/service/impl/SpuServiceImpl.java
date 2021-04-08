package com.kintexgroup.hkpsi.information.service.impl;

import com.github.pagehelper.PageInfo;
import com.github.pagehelper.page.PageMethod;
import com.google.common.collect.Sets;
import com.kintexgroup.hkpsi.common.constants.Carrier;
import com.kintexgroup.hkpsi.common.constants.ResponseCode;
import com.kintexgroup.hkpsi.common.exception.BusinessException;
import com.kintexgroup.hkpsi.common.handler.CommonPageConverter;
import com.kintexgroup.hkpsi.common.util.CommonUtil;
import com.kintexgroup.hkpsi.common.util.ContextHolderUtil;
import com.kintexgroup.hkpsi.common.util.ExceptionUtil;
import com.kintexgroup.hkpsi.information.dao.SpuDao;
import com.kintexgroup.hkpsi.information.entity.Spu;
import com.kintexgroup.hkpsi.information.model.*;
import com.kintexgroup.hkpsi.information.service.SpuService;
import com.kintexgroup.hkpsi.information.util.SpuPojoConverter;
import com.kintexgroup.hkpsi.inventory.dao.SkuDao;
import com.kintexgroup.hkpsi.inventory.entity.Sku;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author pengli    2020/11/10
 * @author lmao
 * @since 2020/9/8 2:39 下午
 */
@Service("SpuService")
@Transactional(rollbackFor = BusinessException.class)
@Slf4j
public class SpuServiceImpl implements SpuService {


    private static final String COLOR = "color";
    private static final String CAPACITY = "capacity";
    private static final String CARRIER = "carrier";
    private static final String MODEL = "model";
    private static final String SIZE = "size";


    @Resource
    private SpuDao spuDao;

    @Resource
    private SkuDao skuDao;

    /**
     * TODO 根据分类动态新增spu
     *
     * @param dto SpuDTO中的字段
     */
    @Override
    public void create(SpuDTO dto) {
        var spu = SpuPojoConverter.toCreateEntity(ContextHolderUtil.getAuthedUserId(), dto);
        if (spuDao.check(spu.getName(), spu.getBrand()) > 0) {
            throw new BusinessException(ResponseCode.DB_INSERT_DUPLICATE_ERROR);
        }
        spuDao.insert(spu);
        var category = spu.getCategory();
        switch (category) {
            case "手机" -> skuDao.batchInsert(generateSkuPhone(spu));
            case "手表" -> skuDao.batchInsert(generateSkuWatch(spu));
            default -> throw new IllegalArgumentException(category + "is not match value");
        }
    }


    @Override
    public SpuVO update(Integer id, SpuDTO dto) {
        Spu updateEntity = SpuPojoConverter
            .toUpdateEntity(id, ContextHolderUtil.getAuthedUserId(), dto);
        Spu sqlEntity = spuDao.selectOne(id);

        /**
         * 前端传进来的
         */
        var disabled = updateEntity.getDisabled();
        var color = updateEntity.getSpec().get(COLOR);
        var capacity = updateEntity.getSpec().get(CAPACITY);
        var carrier = updateEntity.getSpec().get(CARRIER);
        var model = updateEntity.getSpec().get(MODEL);

        /**
         * 数据库中原来的
         */
        var formerDisabled = sqlEntity.getDisabled();
        var formerColor = sqlEntity.getSpec().get(COLOR);
        var formerCapacity = sqlEntity.getSpec().get(CAPACITY);
        var formerCarrier = sqlEntity.getSpec().get(CARRIER);
        var formerModel = sqlEntity.getSpec().get(MODEL);

//        if (disabled.equals(formerDisabled) && color.equals(formerColor) &&
//            capacity.equals(formerCapacity) && carrier.equals(formerCarrier) &&
//            grade.equals(formerGrade) && model.equals(formerModel)){
//            throw new BusinessException(ResponseCode.BIZ_SPU_NO_CHANGE);
//        }
//        var updateSpec = updateEntity.getSpec();
//        var sqlSpec = sqlEntity.getSpec();
//        if (
//            //除 spec 外,查重
//            sqlEntity.equals(updateEntity)
//                ||
//                //spec查重
//                isUpdateSpecExistEntity(updateSpec, sqlSpec)) {
//            throw new BusinessException(ResponseCode.DB_UPDATE_DUPLICATE_ERROR);
//        }
//        Map<String, Object> spuGenerateSpec = spuGenerateSpec(updateSpec, sqlSpec);
//        updateEntity.setSpec(spuGenerateSpec);
//        spuDao.updateOne(updateEntity);
//        //TODO SKU 增加属性
//        skuDao.batchUpdateBySpuId(generateSku(updateEntity));
        return selectOne(id);
    }


    @Override
    public boolean delete(int id) {
        if (spuDao.deleteOne(id)) {
            return true;
        } else {
            throw new BusinessException(ResponseCode.DB_ID_NULL_ERROR);
        }
    }

    @Override
    public List<SpuVO> selectAll() {
        var records = spuDao.selectAll();
        var list = new ArrayList<SpuVO>(records.size());
        if (records.isEmpty()) {
            return list;
        }
        for (var record : records) {
            list.add(SpuPojoConverter.fromSpu(record));
        }
        return list;
    }

    @Override
    public PageInfo<SpuVO> selectOneOrMany(SpuPageDTO dto, int current, int pageSize) {
        PageMethod.startPage(current, pageSize, true).setOrderBy("updated_at desc");
        var entity = spuDao.selectOneOrMany(dto);
        var list = new ArrayList<SpuVO>(entity.size());
        if (entity.isEmpty()) {
            return new PageInfo<>();
        }
        for (var record : entity) {
            list.add(SpuPojoConverter.fromSpu(record));
        }
        return CommonPageConverter.toPageInfo(entity, list);
    }

    @Override
    public SpuVO selectOne(Integer id) {
        var entity = spuDao.selectOne(id);
        if (entity == null) {
            throw new BusinessException(ResponseCode.DB_ID_NULL_ERROR);
        }
        return SpuPojoConverter.fromSpu(entity);
    }

    @Override
    public void batchAddSpuAndGenerateSku(SpuDTO[] dtoList) {
        int size = dtoList.length;
        int index = 0;
        var spuList = new Spu[size];

        for (SpuDTO spuDTO : dtoList) {
            var mapSp = spuDTO.getSpec();
            selectable(mapSp);
            mapSp.put(CARRIER, Carrier.listCarriers());
            spuDTO.setSpec(mapSp);
            spuList[index] = SpuPojoConverter.toCreateEntity(1, spuDTO);
            index++;
        }
        //批量插入 spu 表
        ExceptionUtil.isInsertSuccess(spuDao.batchInsertSpu(spuList));
        List<Sku> list;
        switch (spuList[0].getCategory()) {
            //自动生成 sku
            case "手机" -> list = generateSkuPhone(spuList);
            case "手表" -> list = generateSkuWatch(spuList);
            default -> throw new IllegalArgumentException(spuList[0].getCategory() + "is not match value");
        }
        ExceptionUtil.isInsertSuccess(skuDao.batchInsert(list));

    }

    @SuppressWarnings("unchecked")
    private void selectable(Map<String, Object> mapSp) {
        var colors = (List<String>) mapSp.get(COLOR);
        colors.add("-");
        var models = (List<String>) mapSp.get(MODEL);
        models.add("-");
        var capacities = (List<Integer>) mapSp.get(CAPACITY);
        capacities.add(0);
        mapSp.put(COLOR, colors);
        mapSp.put(MODEL, models);
        mapSp.put(CAPACITY, capacities);
    }


    /**
     * 根据 spu 生成 sku (笛卡尔积)
     *
     * @param spus 可以是一个,可以是多个
     */
    @SuppressWarnings("unchecked")
    private List<Sku> generateSkuPhone(Spu... spus) {
        var skuList = new ArrayList<Sku>();

        var attributeList = separatePhone(spus);

        //新建 sku
        for (Object attribute : attributeList) {

            var attrList = Collections
                .singletonList((Set<? extends List<Object>>) attribute);

            var objListSet = (Set<List<Object>>) attrList.get(0);
            objListSet.forEach(objectList -> {

                Sku sku = new Sku();
                sku.setSpuId(Integer.valueOf((String) objectList.get(0)));

                Attribute attr = new Attribute();

                attr.setModel((String) objectList.get(1));
                attr.setColor((String) objectList.get(2));
                attr.setCapacity(Integer.parseInt(String.valueOf(objectList.get(3))));
                attr.setCarrier((String) objectList.get(4));

                //Object to JsonString
                sku.setAttribute(attr);
                sku.setCreatedBy(ContextHolderUtil.getAuthedUserId());
                sku.setUpdatedBy(ContextHolderUtil.getAuthedUserId());
                sku.setDisabled(0);
                log.info(sku.toString());
                skuList.add(sku);
            });
        }
        return skuList;
    }

    /**
     * 分离描述中的变量
     *
     * @param spus 单或多条 spu
     * @return 笛卡尔积过后的属性表
     */
    @SuppressWarnings("unchecked")
    private ArrayList<Set<? extends String>> separatePhone(Spu... spus) {

        ArrayList attributeList = new ArrayList<Set<? extends String>>();

        for (@NonNull Spu spu : spus) {
            var spec = spu.getSpec();
            var spuId = String.valueOf(spu.getId());
            var list1 = CommonUtil.castList(spec.get(MODEL), String.class);
            list1.add("-");
            var list2 = CommonUtil.castList(spec.get(COLOR), String.class);
            list2.add("-");
            var list3 = (List<String>) spec.get(CAPACITY);
//            list3.add("0");
            var list4 = CommonUtil.castList(spec.get(CARRIER), String.class);
            list4.add("-");
            Set<? extends String> models = new HashSet<>(list1);
            Set<? extends String> colors = new HashSet<>(list2);
            Set<? extends String> capacities = new HashSet<>(list3);
            Set<? extends String> carriers = new HashSet<>(list4);

            //生成 sku 笛卡尔积
            Set<List<String>> cartesianProduct = Sets.cartesianProduct(
                new HashSet<>(Collections.singleton(spuId)),
                models, colors, capacities, carriers);
            attributeList.add(cartesianProduct);
        }
        return attributeList;
    }

    /**
     * 分离描述中的变量
     *
     * @param spus 单或多条 spu
     * @return 笛卡尔积过后的属性表
     */
    @SuppressWarnings("unchecked")
    private ArrayList<Set<? extends String>> separateWatch(Spu... spus) {

        ArrayList attributeList = new ArrayList<Set<? extends String>>();

        for (@NonNull Spu spu : spus) {
            var spec = spu.getSpec();
            var spuId = String.valueOf(spu.getId());
            var list1 = CommonUtil.castList(spec.get(MODEL), String.class);
            list1.add("-");
            var list2 = CommonUtil.castList(spec.get(COLOR), String.class);
            list2.add("-");
            var list3 = CommonUtil.castList(spec.get(SIZE), String.class);
            list3.add("-");
            Set<? extends String> models = new HashSet<>(list1);
            Set<? extends String> colors = new HashSet<>(list2);
            Set<? extends String> sizes = new HashSet<>(list3);

            //生成 sku 笛卡尔积
            Set<List<String>> cartesianProduct = Sets.cartesianProduct(
                new HashSet<>(Collections.singleton(spuId)),
                models, colors, sizes);
            attributeList.add(cartesianProduct);
        }
        return attributeList;
    }


    /**
     * 检查要添加的 spec 字段,是否存在于要添加的 spec 里面
     *
     * @param addSpec 要添加的 spec 字段
     * @param sqlSpec 数据库原有的 spec 字段
     * @return 是否存在重复字段
     */
    private boolean isUpdateSpecExistEntity(
        Map<String, Object> addSpec, Map<String, Object> sqlSpec) {
        if (addSpec.equals(sqlSpec)) {
            return true;
        }
        //取交集
        Set<String> duplicateSet = CommonUtil.duplicateSet(sqlSpec, addSpec);
        HashMap<String, Object> map = new HashMap<>(16);
        duplicateSet.forEach(key -> map.put(key, sqlSpec.get(key)));
        return !map.isEmpty();
    }

    /**
     * 合并加入的属性并去重
     *
     * @param updateSpec 更新的属性
     * @param sqlSpec    数据库原有属性
     * @return 合并好的属性
     */
    @SuppressWarnings("unchecked")
    private Map<String, Object> spuGenerateSpec(Map<String, Object> updateSpec,
                                                Map<String, Object> sqlSpec) {

        var duplicateSet = CommonUtil.duplicateSet(sqlSpec, updateSpec);
        var result = new HashMap<String, Object>(16);
        duplicateSet.forEach(key -> {
            var addSpec = (List) updateSpec.get(key);
            var spec = (List) sqlSpec.get(key);
            Set appendSet = new HashSet<>(spec);
            appendSet.addAll(addSpec);
            result.put(key, appendSet.toArray());
        });
        return result;
    }

    /**
     * 根据 spu 生成 sku (笛卡尔积)
     *
     * @param spus 可以是一个,可以是多个
     */
    @SuppressWarnings("unchecked")
    private List<Sku> generateSkuWatch(Spu... spus) {
        var skuList = new ArrayList<Sku>();

        var attributeList = separateWatch(spus);

        //新建 sku
        for (Object attribute : attributeList) {

            var attrList = Collections
                .singletonList((Set<? extends List<Object>>) attribute);

            var objListSet = (Set<List<Object>>) attrList.get(0);
            objListSet.forEach(objectList -> {

                Sku sku = new Sku();
                sku.setSpuId(Integer.valueOf((String) objectList.get(0)));

                AttributeWatch attr = new AttributeWatch();

                attr.setModel((String) objectList.get(1));
                attr.setColor((String) objectList.get(2));
                attr.setSize((String) objectList.get(3));

                //Object to JsonString
                sku.setAttribute(attr);
                sku.setCreatedBy(ContextHolderUtil.getAuthedUserId());
                sku.setUpdatedBy(ContextHolderUtil.getAuthedUserId());
                sku.setDisabled(0);
                log.info(sku.toString());
                skuList.add(sku);
            });
        }
        return skuList;
    }
}

