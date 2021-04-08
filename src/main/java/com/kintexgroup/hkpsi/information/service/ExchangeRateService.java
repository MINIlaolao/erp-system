package com.kintexgroup.hkpsi.information.service;


import com.github.pagehelper.PageInfo;
import com.kintexgroup.hkpsi.information.model.ExchangeRateDTO;
import com.kintexgroup.hkpsi.information.model.ExchangeRatePageDTO;
import com.kintexgroup.hkpsi.information.model.ExchangeRateVO;

/**
 * @author pengli
 * @since 2020/11/13 11:21 上午
 */
public interface ExchangeRateService {
    /**
     * 新增一条汇率记录
     *
     * @param dto dto
     * @return 添加的记录
     */
    ExchangeRateVO insertOne(ExchangeRateDTO dto);

    /**
     * 根据id删除一条记录
     *
     * @param id  id
     * @param dto dto
     * @return 成功 : true | 查询失败 : false
     */
    void updateOne(int id, ExchangeRateDTO dto);

    /**
     * 根据唯一标识 移除一条汇率记录
     *
     * @param id 汇率唯一标识
     * @return 成功 : true | 查询失败 : false
     */
    boolean removeOne(int id);

    /**
     * 根据汇率唯一标识查询一条汇率记录
     *
     * @param id 汇率唯一标识
     * @return 查询到的数据
     */
    ExchangeRateVO selectById(int id);

    /**
     * 根据不同的查询条件，查询一条或多条信息，查询条件为空时，查所有
     *
     * @param dto      dto
     * @param current  第几页
     * @param pageSize 每页有多少条数据
     * @return
     */
    PageInfo<ExchangeRateVO> selectOneOrMany(ExchangeRatePageDTO dto, int current, int pageSize);
}
