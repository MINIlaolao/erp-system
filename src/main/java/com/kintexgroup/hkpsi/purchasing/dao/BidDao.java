package com.kintexgroup.hkpsi.purchasing.dao;

import com.github.pagehelper.Page;
import com.kintexgroup.hkpsi.purchasing.entity.BidEntity;
import com.kintexgroup.hkpsi.purchasing.model.BidDetailVO;
import com.kintexgroup.hkpsi.purchasing.model.BidPageDTO;
import com.kintexgroup.hkpsi.purchasing.model.BidVO;
import com.kintexgroup.hkpsi.purchasing.model.wonbid.WonBidDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * @author lmao
 */
@Repository
public interface BidDao {

    /**
     * 根据 id 删除一条投标信息
     *
     * @param id bidId
     * @return Success :true || Fail  :false
     */
    boolean deleteOne(String id);

    /**
     * 插入一条新的投标信息
     *
     * @param entity 插入的投标信息
     * @return Success :true || Fail  :false
     */
    boolean insertOne(BidEntity entity);


    /**
     * 根据 id 更新一条投标信息
     *
     * @param record 更新的投标信息
     * @return Success :true || Fail  :false
     */
    boolean updateOne(BidEntity record);

    /**
     * 查询所有投标信息
     *
     * @return 所有投标信息
     */
    List<BidVO> selectAll();

    /**
     * 判断插入或更新时数据库中的 number 和 invitationNumber 是否重复
     *
     * @param id 需要判重的投标信息
     * @return false : 查询到重复的值 || true  : 没有查询到重复的值
     */
    boolean checkDuplicate(@Param("id") String id);


    /**
     * 查询今年对应供应商的单数
     *
     * @param id   供应商 id
     * @param year 批次年份
     * @return 今年对应供应商的总单数
     */
    String countVendorBetweenYear(@Param("id") Integer id, @Param("year") Integer year);

    /**
     * 查询今年所有供应商的单数
     *
     * @param year 批次年份
     * @return 今年所有供应商的单数
     */
    String countAll(@Param("year") Integer year);

    /**
     * 根据 id 查询一条投标信息
     *
     * @param id bidId
     * @return 一条 BId 记录
     */
    BidVO selectOne(@Param("id") String id);

    /**
     * 根据 ID 查询投标单详情
     *
     * @param id 投标 ID
     * @return 查询投标单详情
     */
    BidDetailVO selectOneDetail(String id);

    /**
     * 查询所有投标记录
     *
     * @return 所有投标记录
     */
    List<BidDetailVO> selectManyDetail();

    /**
     * 根据不同的查询条件，查询一条或多条投标记录。查询条件为空时 查所有
     *
     * @param dto dto
     * @return 查询出来的结果集
     */
    Page<BidVO> selectBatch(BidPageDTO dto);

    /**
     * 根据bidID查询bidDate
     *
     * @param dto dto
     * @return 查到的投标日期
     */
    Date selectBidDate(WonBidDTO dto);

    /**
     * 根据创建时间，查询一条投标信息
     *
     * @param date 创建时间
     * @return 查询出来的一条信息
     */
    BidEntity selectCreatedAt(String date);

    /**
     * 根据bidID中对应的exchange_rate联合汇率表查询汇率符号
     *
     * @param bidId 投标id
     * @return 查询出来的汇率符号
     */
    String selectCurrencySymbolByBidId(String bidId);
}
