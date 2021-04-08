package com.kintexgroup.hkpsi.purchasing.service;

import com.github.pagehelper.PageInfo;
import com.kintexgroup.hkpsi.purchasing.model.BidDTO;
import com.kintexgroup.hkpsi.purchasing.model.BidDetailVO;
import com.kintexgroup.hkpsi.purchasing.model.BidPageDTO;
import com.kintexgroup.hkpsi.purchasing.model.BidVO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author junhangs
 * @since 2020/8/23 21:50
 */
@Repository
public interface BidService {

    /**
     * 插入一条新的投标信息
     *
     * @param args 插入的投标信息
     * @return 一条 Bid 记录
     */
    String create(BidDTO args);

    /**
     * 根据 id 更新一条投标信息
     *
     * @param dto 更新的投标信息
     * @param id  bidId
     * @return Success :true || Fail  :false
     */
    BidVO update(String id, BidDTO dto);

    /**
     * 根据 id 删除一条投标信息
     *
     * @param id bidId
     * @return Success :true || Fail  :false
     */
    boolean delete(String id);

    /**
     * 根据 id 查询一条投标信息
     *
     * @param id bidId
     * @return 一条 Bid 记录
     */
    BidVO selectOne(String id);


    /**
     * 根据 ID 获取投标详情
     *
     * @param id 投标 ID
     * @return 投标详情
     */
    BidDetailVO getBidDetailById(String id);


    /**
     * 直接获取投标详情
     *
     * @return 投标详情列表
     */
    List<BidDetailVO> getBidDetailList();

    /**
     * 查询所有 Bid
     *
     * @return 所有 Bid 记录
     */
    List<BidVO> selectAll();

    /**
     * 根据不同的查询条件，查询一条或多条投标信息
     *
     * @param dto  dto
     * @return 查询出来的结果集
     */
    PageInfo<BidVO> list(BidPageDTO dto);


    /**
     * 生成一条新的bidNumber 例如: '20201112002' : 2020年+11月份+12日+今天的第二单
     *
     * @param bidDate 批次年月日
     * @return 新的 bidNumber
     */
    String generateNewBidId(String bidDate);
}
