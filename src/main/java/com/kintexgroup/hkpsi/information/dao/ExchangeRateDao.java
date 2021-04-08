package com.kintexgroup.hkpsi.information.dao;


import com.github.pagehelper.Page;
import com.kintexgroup.hkpsi.information.entity.ExchangeRate;
import com.kintexgroup.hkpsi.information.model.ExchangeRatePageDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


/**
 * @author pengli
 * @since 2020/11/13 10:58 上午
 */
@Repository
public interface ExchangeRateDao {
    /**
     * 新增一条汇率记录
     *
     * @param entity 实体类
     * @return 成功 : true | 查询失败 : false
     */
    boolean insertOne(ExchangeRate entity);


    /**
     * 更新一条汇率记录
     *
     * @param entity 实体类
     * @return 成功 : true | 查询失败 : false
     */
    boolean updateOne(ExchangeRate entity);

    /**
     * 根据唯一标识 移除一条汇率记录
     *
     * @param id 汇率唯一标识
     * @return 成功 : true | 查询失败 : false
     */
    boolean removeOne(@Param("id") int id);

    /**
     * 根据汇率唯一标识查询一条汇率记录
     *
     * @param dto dto
     * @return 查询到的结果
     */
    Page<ExchangeRate> selectOneOrMany(ExchangeRatePageDTO dto);

    /**
     * 根据id查询一条信息
     *
     * @param id id
     * @return 查询的结果
     */
    ExchangeRate selectById(@Param("id") int id);

    /**
     * 根据currencyCode（货币）查询
     *
     * @param currencyCode currencyCode（货币）
     * @return 查询到的信息
     */
    ExchangeRate selectByCode(@Param("currencyCode") String currencyCode);

    /**
     * 根据currencyCode（货币）查询货币符号
     *
     * @param currencyCode currencyCode（货币）查询
     * @return 查询到的货币符号
     */
    String selectSymbolByCode(String currencyCode);
}
