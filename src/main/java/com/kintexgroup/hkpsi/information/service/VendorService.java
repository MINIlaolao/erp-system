package com.kintexgroup.hkpsi.information.service;

import com.github.pagehelper.PageInfo;
import com.kintexgroup.hkpsi.information.model.VendorDTO;
import com.kintexgroup.hkpsi.information.model.VendorPageDTO;
import com.kintexgroup.hkpsi.information.model.VendorVO;
import org.springframework.stereotype.Repository;

/**
 * @author junhangs
 * @since 2020/8/23 21:50
 */
@Repository
public interface VendorService {

    /**
     * 添加一条运营商信息
     *
     * @param dto vendor
     * @return VendorVO
     */
    VendorVO create(VendorDTO dto);

    /**
     * 根据ID更新一条供应商信息
     *
     * @param id  供应商ID
     * @param dto 数据
     * @return 更新是否成功
     */
    boolean update(Integer id, VendorDTO dto);


    /**
     * 根据ID删除一条供应商信息
     *
     * @param id
     * @return 成功 true / 失败 false
     */
    boolean delete(Integer id);

    /**
     * 根据ID查询一条供应商信息
     *
     * @param id
     * @return 单条记录
     */
    VendorVO selectOne(Integer id);

    /**
     * 根据不同的查询条件查询一条或多条供应商信息
     *
     * @param dto      dto
     * @param current  当前页面
     * @param pageSize 每页的数量
     * @return 结果集list
     */
    PageInfo<VendorVO> selectOneOrManyVendor(VendorPageDTO dto, int current, int pageSize);
}