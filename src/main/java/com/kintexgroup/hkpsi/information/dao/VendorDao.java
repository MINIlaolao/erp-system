package com.kintexgroup.hkpsi.information.dao;

import com.github.pagehelper.Page;
import com.kintexgroup.hkpsi.information.entity.Vendor;
import com.kintexgroup.hkpsi.information.model.VendorPageDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author junhangs
 * @since 2020/8/23 21:50
 */
@SuppressWarnings("ALL")
@Repository
@Mapper
public interface VendorDao {

    /**
     * 检查数据库是否存在这条记录
     *
     * @param code
     * @param name
     * @return 查询成功 : true | 查询失败 : false
     */
    boolean check(@Param("code") String code, @Param("name") String name);

    /**
     * 检查数据库是否存在这条记录
     *
     * @param code
     * @return 查询成功 : true | 查询失败 : false
     */
    boolean checkCode(@Param("code") String code);

    /**
     * 添加一条 供应商信息
     *
     * @param record
     * @return
     */
    boolean insertOne(Vendor record);

    /**
     * 更新一条供应商信息
     *
     * @param record
     * @return
     */
    boolean updateOne(Vendor record);

    /**
     * 删除一条供应商信息
     *
     * @param id
     * @return
     */
    boolean deleteOne(Integer id);

    /**
     * 检查数据库中是否存在这条记录
     *
     * @param id 供应商 id
     * @return 存在 : true | 不存在 : false
     */
    boolean checkVendor(@Param("id") Integer id);

    /**
     * 查询一条供应商信息
     *
     * @param id
     * @return 查询的那条供应商记录
     */
    Vendor selectOne(Integer id);

    /**
     * 根据ID查询供应商在vendor_sku表中是否存在
     *
     * @param id
     * @return 返回查询出来的条数
     */
    Integer selectByVendorId(Integer id);

    /**
     * 查询所有的供应商信息
     *
     * @param dto
     * @return 供应商的结果集
     */
    Page<Vendor> selectOneOrManyVendor(VendorPageDTO dto);

    String selectCodeById(@Param("vendorId") String vendorId);
}

