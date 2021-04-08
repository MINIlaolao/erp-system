package com.kintexgroup.hkpsi.inventory.service.impl;

import com.github.pagehelper.PageInfo;
import com.github.pagehelper.page.PageMethod;
import com.kintexgroup.hkpsi.sales.dao.GoodsDao;
import com.kintexgroup.hkpsi.common.constants.ResponseCode;
import com.kintexgroup.hkpsi.common.dao.SessionDao;
import com.kintexgroup.hkpsi.common.exception.BusinessException;
import com.kintexgroup.hkpsi.common.handler.CommonPageConverter;
import com.kintexgroup.hkpsi.common.util.ContextHolderUtil;
import com.kintexgroup.hkpsi.common.util.ExceptionUtil;
import com.kintexgroup.hkpsi.inventory.dao.StageDao;
import com.kintexgroup.hkpsi.inventory.entity.Stage;
import com.kintexgroup.hkpsi.inventory.model.ImeiList;
import com.kintexgroup.hkpsi.inventory.model.StageDTO;
import com.kintexgroup.hkpsi.inventory.model.StagePageDTO;
import com.kintexgroup.hkpsi.inventory.model.StageVO;
import com.kintexgroup.hkpsi.inventory.service.StageService;
import com.kintexgroup.hkpsi.inventory.util.StagePojoConverter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author lmao             pengli
 * @since 2020/8/26 17:58   2020/10/10
 */
@Transactional(rollbackFor = BusinessException.class)
@Service("StageService")
public class StageServiceImpl implements StageService {

    @Resource
    private StageDao stageDao;

    @Resource
    private SessionDao sessionDao;

    @Resource
    private GoodsDao goodsDao;

    /**
     * 根据 id 删除单条信息
     *
     * @param imei device_id
     * @return 成功 : true | 失败 : false
     */
    @Override
    public boolean deleteOne(String imei) {
        return stageDao.deleteOne(imei);
    }

    @Override
    public boolean deleteMany(List<String> imei) {
        stageDao.deleteMany(imei);
        return true;
    }

    /**
     * 插入单条虚拟库存信息
     *
     * @param dto 插入的信息
     * @return 单条虚拟库存信息
     */
    @Override
    public StageVO insertOne(StageDTO dto) {
        Stage entity = StagePojoConverter.toCreateEntity(ContextHolderUtil.getAuthedUserId(), dto);
        if (stageDao.checkDuplicate(entity.getImei())) {
            throw new BusinessException(ResponseCode.DB_INSERT_DUPLICATE_ERROR);
        }
        stageDao.insertOne(entity);
        return selectOne(entity.getId());
    }

    /**
     * 根据 id 更新单条信息
     *
     * @param dto 更新的信息
     * @param id  device_id
     * @return 单条更新过后虚拟库存信息
     */
    @Override
    public StageVO updateOne(StageDTO dto, String id) {
        var entity = StagePojoConverter
            .toUpdateEntity(id, ContextHolderUtil.getAuthedUserId(), dto);

        try {
            ExceptionUtil.checkState(stageDao.checkDuplicate(entity.getImei()),
                ResponseCode.DB_UPDATE_DUPLICATE_ERROR.getMessage());
            if (stageDao.updateOne(entity)) {
                return selectOne(entity.getId());
            }
        } catch (IllegalStateException e) {
            throw new BusinessException(e.getCause(), ResponseCode.DB_UPDATE_DUPLICATE_ERROR);
        }
        return null;
    }

    /**
     * 根据 id 查询单条信息
     *
     * @param id device_id
     * @return 单条虚拟库存信息
     */
    @Override
    public StageVO selectOne(String id) {
        return stageDao.selectOne(id);

    }

    /**
     * 查询所有record信息
     *
     * @return 所有record信息
     */
    @Override
    public List<StageVO> selectAll() {
        var records = stageDao.selectAll();
        var list = new ArrayList<StageVO>(records.size());
        if (records.isEmpty()) {
            return list;
        }
        for (var record : records) {
            list.add(StagePojoConverter.fromStage(record));
        }
        return list;
    }

    @Override
    public PageInfo<StageVO> selectOneOrMany(StagePageDTO dto, int page, int size) {
        PageMethod.startPage(page, size, true);
        var list = new ArrayList<StageVO>();
        var records = stageDao.selectOneOrMany(dto);
        records.forEach(record -> list.add(StagePojoConverter.fromStage(record)));
        return CommonPageConverter.toPageInfo(records, list);
    }

    /**
     * 向goods表中批量插入信息
     *
     * @param imeiList imeiList
     * @return 成功 : true | 失败 : false
     */
    @Override
    public void insertMany(List<String> imeiList) {
        // 根据前端传进来的imeiList查询stage表中是否存在相同的imei的信息
        var stages = stageDao.selectById(imeiList);
        if (stages == null || stages.isEmpty()) {
            throw new BusinessException(ResponseCode.DB_SELECT_ERROR);
        }
        var entity = StagePojoConverter.toGoods(stages);

        if (goodsDao.checkImei(entity)) {
            throw new BusinessException(ResponseCode.DB_INSERT_DUPLICATE_ERROR);
        }

        // 向goods表中 批量添加 查到的那些imei的信息
        stageDao.insertMany(entity);
        // 批量删除stage表中的 上一步向goods表中添加的那些imei的信息
        stageDao.deleteMany(imeiList);

        Set<String> set = new HashSet<>();
        // 把skuid放到set集合中
        for (String s : imeiList) {
            // 根据imei向goods表中查询skuId
            //把查询出来的skuId添加到set中
            set.add(stageDao.selectSkuId(s));
        }
        //FIXME 
        for (String skuId : set) {
            // 查询库存表内是否有skuid对应的库存信息，如果有则更新没有则新增一条信息
            if (stageDao.check(skuId) != 0) {
                ExceptionUtil.isUpdateSuccess(stageDao.updateInventory(skuId));
                return;
            }
            ExceptionUtil.isInsertSuccess(stageDao.insertInventory(skuId));
        }
    }

    @Override
    public String insert(String imei) {
        if (stageDao.insert(imei)) {
            stageDao.deleteOne(imei);
            return "入库成功";
        }
        return "操作失败";
    }

    @Override
    public void batchInsertStageRecord(List<ImeiList> imeiLists) {

        var stages = new ArrayList<Stage>();
        var descSet = new HashSet<String>();
        imeiLists.forEach(imeiList -> {
            var imeiCap = converterCapacity(imeiList.getCapacity());
            // String desc = imeiList.getModelName() + " " + imeiList.getColor() + " " + imeiList.getCapacity() + "|" + toOurGrade(imeiList.getGrade());
            String desc = imeiList.getModelName() + " " + imeiList.getColor() + " " + imeiCap;
            //test
            descSet.add(desc);
            imeiList.setDescription(desc);
        });

        // k:skuId v: tag
        Map<String, String> map = sessionDao.selectDescriptionConverterMap(descSet);
        imeiLists.forEach(imeiList -> map.forEach((k, v) -> {
            //忽略大小写
            if (v.equalsIgnoreCase(imeiList.getDescription())) {
                imeiList.setSkuId(k);
            }
        }));
        imeiLists.forEach(imeiList -> {
            Stage stage = converterToStage(imeiList);
            stages.add(stage);
        });
        ExceptionUtil.isInsertSuccess(stageDao.batchInsertStageRecord(stages));
    }


    /**
     * 统一内存格式为xxxGB
     *
     * @param capacity 内存
     * @return 格式化内存
     */
    private String converterCapacity(String capacity) {
        final String capacityUpper = capacity.toUpperCase();
        final String gb = "GB";
        final String g = "G";
        if (capacityUpper.contains(gb)) {
            return capacityUpper;
        }
        if (!capacityUpper.contains(g)) {
            return capacityUpper + gb;
        }
        return capacityUpper + "B";
    }

    /**
     * 根据供应商等级定制转换我们自己的等级
     *
     * @param grade 等级
     * @return 我们自己等级
     */
    private static String toOurGrade(String grade) {
        return switch (grade) {
            case "1 - Excellent" -> "DPA";
            case "2 - Good" -> "DPB";
            case "3 - Fair" -> "DPC";
            default -> "";
        };
    }

    private Stage converterToStage(ImeiList imeiList) {
        Stage stage = new Stage();

        var vendorSku = imeiList.getSkuId();
        if (vendorSku == null) {
            vendorSku = "0";
        }
        stage.setVendorSku(vendorSku);
        stage.setTag(imeiList.getDescription());
        stage.setImei(imeiList.getImei());
        stage.setWonId(imeiList.getWonId());
        stage.setVendorGrade(imeiList.getGrade());
        stage.setCreatedBy(ContextHolderUtil.getAuthedUserId());
        stage.setUpdatedBy(ContextHolderUtil.getAuthedUserId());
        return stage;
    }
}
