package com.kintexgroup.hkpsi.information.service;


import com.kintexgroup.hkpsi.information.model.SkuAttributeDTO;
import com.kintexgroup.hkpsi.information.model.SkuAttributeVO;

import java.util.List;
import java.util.Set;

/**
 * @author pengli
 * @since 2020/9/15 7:07 下午
 */
public interface SkuAttributeService {

    /**
     * 添加一条SkuAttribute信息
     *
     * @param categoryId
     * @param list;
     * @return 一条记录
     */
    boolean create(Integer categoryId, Set<SkuAttributeDTO> list);

    /**
     * 根据categoryId,批量更新SkuAttribute信息
     *
     * @param categoryId
     * @param dto
     * @return 成功 true/失败 false
     */
    Boolean update(Integer categoryId, List<SkuAttributeDTO> dto);

    /**
     * 根据id删除一条SkuAttribute信息
     *
     * @param ids id集合
     * @return 成功 true/失败 false
     */
    boolean delete(List<Integer> ids);

    /**
     * 查询所有
     *
     * @return 结果集list
     */
    List<SkuAttributeVO> selectAll();

    /**
     * 根据id查询一条sku key信息
     *
     * @param id
     * @return 一条记录
     */
    List<SkuAttributeVO> selectOne(Integer id);
}
