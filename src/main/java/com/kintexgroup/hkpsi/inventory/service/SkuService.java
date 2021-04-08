package com.kintexgroup.hkpsi.inventory.service;


import com.github.pagehelper.PageInfo;
import com.kintexgroup.hkpsi.inventory.model.SkuDTO;
import com.kintexgroup.hkpsi.inventory.model.SkuPageDTO;
import com.kintexgroup.hkpsi.inventory.model.SkuVO;

/**
 * @author lmao
 * @since 2020/9/11 13:58
 */
public interface SkuService {

    /**
     * 根据id删除一条sku信息
     *
     * @param id id
     * @return 成功 true/失败 false
     */
    boolean delete(Integer id);

    /**
     * 查询所有
     *
     * @param dto  dto
     * @param page
     * @param size
     * @return 结果集list
     */
    PageInfo<SkuVO> selectAll(SkuPageDTO dto, int page, int size);

    /**
     * 根据ID更新一条sku信息
     *
     * @param id  Id
     * @param dto DTO中的字段
     * @return 成功 true/失败 false
     */
    boolean updateOne(int id, SkuDTO dto);
}
