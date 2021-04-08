package com.kintexgroup.hkpsi.information.service.impl;

import com.kintexgroup.hkpsi.common.constants.ResponseCode;
import com.kintexgroup.hkpsi.common.exception.BusinessException;
import com.kintexgroup.hkpsi.common.util.ContextHolderUtil;
import com.kintexgroup.hkpsi.common.util.ExceptionUtil;
import com.kintexgroup.hkpsi.information.dao.CategoryDao;
import com.kintexgroup.hkpsi.information.dao.SkuAttributeDao;
import com.kintexgroup.hkpsi.information.entity.Category;
import com.kintexgroup.hkpsi.information.entity.SkuAttribute;
import com.kintexgroup.hkpsi.information.model.CategoryDTO;
import com.kintexgroup.hkpsi.information.model.CategoryVO;
import com.kintexgroup.hkpsi.information.model.SkuAttributeDTO;
import com.kintexgroup.hkpsi.information.service.CategoryService;
import com.kintexgroup.hkpsi.information.util.CategoryPojoConverter;
import com.kintexgroup.hkpsi.information.util.SkuAttributePojoConverter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * @author lmao
 * @author penli
 * @since 2020/9/8 15:09   2020/11/10
 */
@Service("CategoryService")
@Transactional(rollbackFor = BusinessException.class)
public class CategoryServiceImpl implements CategoryService {

    @Resource
    private CategoryDao categoryDao;
    @Resource
    private SkuAttributeDao skuAttributeDao;

    /**
     * 查询多条的类别记录
     *
     * @return 多条的类别记录
     */
    @Override
    public List<CategoryVO> selectMany() {
        return categoryDao.selectMany();
    }

    /**
     * 通过categoryId查询属性信息
     *
     * @param id 类别Id
     * @return list
     */
    @Override
    public CategoryVO selectOne(Integer id) {
        return categoryDao.selectOneById(id);
    }

    /**
     * 插入单条记录
     *
     * @param dto 插入的类别记录
     */
    @Override
    public Boolean addCategory(CategoryDTO dto) {
        int creator = ContextHolderUtil.getAuthedUserId();
        Category category = CategoryPojoConverter.toCreate(creator, dto);
        if (categoryDao.check(category.getName())) {
            throw new BusinessException(ResponseCode.DB_INSERT_DUPLICATE_ERROR);
        }
        if (categoryDao.insertOne(category)) {
            insertBatchAttribute(category.getId(), dto.getAttributes());
        }
        return true;
    }

    /**
     * 根据种类的id 添加属性
     *
     * @param categoryId 类别id
     * @param list       参数
     */
    private void insertBatchAttribute(Integer categoryId, List<SkuAttributeDTO> list) {
        var entities = new HashSet<SkuAttribute>();
        for (var record : list) {
            var entity = SkuAttributePojoConverter.toCreateEntity(categoryId, record);
            entities.add(entity);
        }
        ExceptionUtil.checkState(skuAttributeDao.insert(new ArrayList<>(entities)),
            ResponseCode.DB_INSERT_ERROR);
    }

    /**
     * 更改单条记录
     *
     * @param id  类别 id
     * @param dto 更改的类别记录
     */
    @Override
    public Boolean updateOne(Integer id, CategoryDTO dto) {

        // 获取当前登录用户的ID
        int updater = ContextHolderUtil.getAuthedUserId();

        // 新建一个实体类，用于插入数据库
        Category entity = new Category();

        // 从数据库中找出已有的记录
        var inDatabaseRecord = categoryDao.selectOneById(id);
        if (inDatabaseRecord == null) {
            throw new BusinessException(ResponseCode.DB_ID_NULL_ERROR);
        }

        //当 disabled 不一致时，更新该 record
        if (!inDatabaseRecord.getDisabled().equals(dto.getDisabled())) {
            entity.setId(id);
            entity.setDisabled(dto.getDisabled());
            entity.setUpdatedBy(updater);
            ExceptionUtil.isUpdateSuccess(categoryDao.updateById(entity));
        }

        // 更新分类属性
        // 获取传入的属性
        var attributes = new ArrayList<>(dto.getAttributes());
        final int entitiesSize = dto.getAttributes().size();
        var entities = new HashMap<>(entitiesSize);
        /*
         * 从 sqlEntity 拿出来，将 attribute_id 作为 Key , description 作为 value
         * 是为了在 dtoList.forEach 里通过 id 快捷匹配 description 是否被修改
         */
        inDatabaseRecord.getAttributes()
            .forEach(attribute -> entities.put(attribute.get("id"), attribute.get("description")));

        var deleteList = new ArrayList<Integer>(entitiesSize);
        var updateList = new ArrayList<SkuAttributeDTO>(entitiesSize);
        var createList = new ArrayList<SkuAttributeDTO>(entitiesSize);

        attributes.forEach(attribute -> {

            Integer attributeId = attribute.getId();

            //标记为真则执行删除
            if (Boolean.TRUE.equals(attribute.getIsDeleted())) {
                deleteList.add(attributeId);
                return;
            }

            //dto.id ==null 则创建
            if (attributeId == null) {
                createList.add(attribute);
                return;
            }

            if (!entities.get(attributeId).equals(attribute.getDescription())) {
                updateList.add(attribute);
            }

        });

        if (!deleteList.isEmpty()) {
            skuAttributeDao.delete(deleteList);
        }

        if (!createList.isEmpty()) {
            insertBatchAttribute(id, createList);
        }

        //批量更新skuAttribute信息
        if (!updateList.isEmpty()) {
            update(id, updateList);
        }
        return true;
    }

    /**
     * 根据categoryId,批量更新sku key信息
     *
     * @param categoryId
     * @param dto
     * @return
     */
    @Override
    public Boolean update(Integer categoryId, List<SkuAttributeDTO> dto) {
        var skuAttrKey = new ArrayList<SkuAttribute>(dto.size());
        for (var record : dto) {
            var entity = SkuAttributePojoConverter.toUpdateEntity(record);
            skuAttrKey.add(entity);
        }
        return skuAttributeDao.updateOne(categoryId, skuAttrKey);
    }

//    public boolean checkAttribute(boolean checkIsExistIdAndName, Integer attributeId,
//        List<SkuAttributeDTO> attributes) {
//        var attributeIds = new int[]{};
//        attributes.forEach(record -> {
//            attributeIds.
//        });
//    }

    /**
     * 删除一条记录
     *
     * @param id 类别 id
     */
    @Override
    public boolean deleteOne(Integer id) {
        //根据id查询spu表里的分类名，>0说明spu表里面有这个分类名，就不能删除这个分类
        if (selectByCategory(id) > 0) {
            throw new BusinessException(ResponseCode.BIZ_HAVE_CATEGORY_SPU);
        } else if (categoryDao.deleteOne(id)) {
            return true;
        } else {
            throw new BusinessException(ResponseCode.DB_ID_NULL_ERROR);
        }
    }

    @Override
    public Integer selectByCategory(int id) {
        return categoryDao.selectByCategory(id);
    }
}
