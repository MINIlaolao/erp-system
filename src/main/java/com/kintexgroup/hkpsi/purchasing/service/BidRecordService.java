package com.kintexgroup.hkpsi.purchasing.service;


import com.github.pagehelper.PageInfo;
import com.kintexgroup.hkpsi.purchasing.model.bidrecord.BidRecordPageDTO;
import com.kintexgroup.hkpsi.purchasing.model.bidrecord.BidRecordVO;
import org.springframework.stereotype.Repository;

/**
 * @author pengli
 */
@Repository
public interface BidRecordService {

    /**
     * 根据id删除一条 清单信息
     *
     * @param id id
     * @return 成功 true/失败 false
     */
    boolean deleteOneById(String id);

    /**
     * 根据id查询一条清单信息
     *
     * @param id id
     * @return 一条信息
     */
    BidRecordVO selectOneById(String id);

    /**
     * 查询所有清单信息
     *
     * @return 结果集list
     */
    PageInfo<BidRecordVO> list(BidRecordPageDTO arg);
}
