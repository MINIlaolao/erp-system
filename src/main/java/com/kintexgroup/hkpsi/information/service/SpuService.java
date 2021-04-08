package com.kintexgroup.hkpsi.information.service;

import com.github.pagehelper.PageInfo;
import com.kintexgroup.hkpsi.information.model.SpuDTO;
import com.kintexgroup.hkpsi.information.model.SpuPageDTO;
import com.kintexgroup.hkpsi.information.model.SpuVO;

import java.util.List;

/**
 * @author pengli
 * @since 2020/9/8 2:28 下午
 */

public interface SpuService {

    /**
     * 添加一条spu信息
     *
     * @param dto SpuDTO中的字段
     * @return 一条记录
     */
    void create(SpuDTO dto);

    /**
     * 根据id,更新一条spu信息,输入 attribute 增加
     *
     * @param id  id
     * @param dto SpuDTO中的字段
     * @return 成功 true/失败 false
     */
    SpuVO update(Integer id, SpuDTO dto);

    /**
     * 根据id删除一条spu信息
     *
     * @param id id
     * @return 成功 true/失败 false
     */
    boolean delete(int id);

    /**
     * 查询所有
     *
     * @return 结果集list
     */
    List<SpuVO> selectAll();

    /**
     * 根据不同的查询条件查询一条或多条spu信息
     *
     * @param dto      dto
     * @param current  当前页面
     * @param pageSize 每页的数量
     * @return 一条或多条spu记录
     */
    PageInfo<SpuVO> selectOneOrMany(SpuPageDTO dto, int current, int pageSize);

    /**
     * 根据id查询一条spu信息
     *
     * @param id spuId
     * @return 一条spu信息
     */
    SpuVO selectOne(Integer id);

    /**
     * 根据 spuList 批量插入 spu 以及自动生成对应的 sku
     *
     * @param dtoList 传入的 spuDTOList
     */
    void batchAddSpuAndGenerateSku(SpuDTO[] dtoList);

}
