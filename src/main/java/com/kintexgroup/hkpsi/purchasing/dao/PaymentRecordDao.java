package com.kintexgroup.hkpsi.purchasing.dao;

import com.github.pagehelper.Page;
import com.kintexgroup.hkpsi.purchasing.entity.PaymentRecord;
import com.kintexgroup.hkpsi.purchasing.model.PaymentRecordModel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author LMAO
 * @since 2020/12/4 10:28
 */
@Mapper
public interface PaymentRecordDao extends tk.mybatis.mapper.common.Mapper<PaymentRecord> {
    /**
     * 批量可选更新
     *
     * @param list list
     * @return 是否成功
     */
    int updateBatchSelective(List<PaymentRecord> list);

    /**
     * 按条件查询单条或批量查询
     *
     * @param paymentRecord 查询条件
     * @return list
     */
    Page<PaymentRecordModel> selectOneOrMany(PaymentRecord paymentRecord);
}