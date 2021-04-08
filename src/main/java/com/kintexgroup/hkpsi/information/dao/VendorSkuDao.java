package com.kintexgroup.hkpsi.information.dao;

import com.github.pagehelper.Page;
import com.kintexgroup.hkpsi.information.entity.VendorSkuEntity;
import com.kintexgroup.hkpsi.information.model.vendorsku.IdTagPair;
import com.kintexgroup.hkpsi.information.model.vendorsku.VendorSkuPageDTO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author junhangs
 * @since 2020/9/11 11:26
 */
@Repository
public interface VendorSkuDao {

    /**
     * 插入一条
     *
     * @param entity VendorSku1
     * @return 是否成功
     */
    boolean insertOne(VendorSkuEntity entity);

    /**
     * 批量插入
     *
     * @param list list
     * @return 是否成功
     */
    int insertBatch(List<VendorSkuEntity> list);

    /**
     * 删除一条
     *
     * @param id id
     * @return 是否成功
     */
    boolean deleteOneById(String id);

    /**
     * 查询一条
     *
     * @param id id
     * @return VendorSku
     */
    VendorSkuEntity selectOneById(String id);

    /**
     * 查询多条记录
     * @param args 参数
     */
    Page<VendorSkuEntity> selectBatch(VendorSkuPageDTO args);

    /**
     * 批量查询
     *
     * @param tags sku描述
     * @return 查询的结果
     */
    List<String> queryForExistingTags(List<String> tags);

    /**
     * 
     * @param list a
     */
    List<IdTagPair> selectIdTagKV(List<String> list);

    /**
     * 更新单条
     *
     * @param entity VendorSku
     * @return 是否成功
     */
    boolean updateOneById(VendorSkuEntity entity);
}