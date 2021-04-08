package com.kintexgroup.hkpsi.purchasing.dao;

import com.github.pagehelper.Page;
import com.kintexgroup.hkpsi.purchasing.entity.BidRecordEntity;
import com.kintexgroup.hkpsi.purchasing.model.bidrecord.BidRecordSalesList;
import com.kintexgroup.hkpsi.purchasing.model.bidrecord.BidRecordPageDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author pengli
 */
@Repository
public interface BidRecordDao {
    
    /**
     * 批量插入 清单信息
     *
     * @param list BidRecord
     * @return 成功:true || 失败  :false
     */
    boolean insertBatch(List<BidRecordEntity> list);

    /**
     * 根据 tag 查询投标记录
     */
    List<BidRecordEntity> selectBatchByTag(String id, List<String> tagList);

    /**
     * 根据不同的查询条件，查询一条或多条投标信息
     *
     * @param args dto
     * @return 查询出来的结果集
     */
    Page<BidRecordEntity> list(BidRecordPageDTO args);

    /**
     * 根据 中标批次 批量查询 bidRecord
     *
     * @param id 中标批次
     * @return bidRecordList
     */
    List<BidRecordSalesList> selectBatchByWonBidId(String id);

    /**
     * 根据清单id(record_id)删除一条清单信息
     *
     * @param id record_id
     * @return 成功:true || 失败  :false
     */
    boolean deleteOneById(String id);

    /**
     * 根据清单id(record_id)查询一条清单信息
     *
     * @param id record_id
     * @return 查询的清单信息
     */
    BidRecordEntity selectOneById(String id);

    /**
     * 更新多条记录
     *
     * @param bidId   bidId
     * @param updater 更新人
     * @param list    BidRecord
     * @return 成功:true || 失败  :false
     */
    boolean updateMany(
        @Param("bidId") String bidId,
        @Param("updater") Integer updater,
        @Param("list") List<BidRecordEntity> list
    );

    /**
     * 用 tag 更新多条记录
     *
     * @param tag     tag
     * @param updater 更新人
     * @param list    BidRecord
     * @return 成功:true || 失败  :false
     */
    boolean updateManyByTag(
        @Param("tag") List<String> tag,
        @Param("updater") Integer updater,
        @Param("list") List<BidRecordEntity> list
    );

    /**
     * 检查 是否重复
     *
     * @param tag tag
     * @return 成功:true || 失败  :false
     */
    boolean checkDuplicate(@Param("tag") String tag);
}
