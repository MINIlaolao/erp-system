package com.kintexgroup.hkpsi.purchasing.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.page.PageMethod;
import com.kintexgroup.hkpsi.common.constants.ResponseCode;
import com.kintexgroup.hkpsi.common.exception.BusinessException;
import com.kintexgroup.hkpsi.common.handler.CommonPageConverter;
import com.kintexgroup.hkpsi.common.util.ContextHolderUtil;
import com.kintexgroup.hkpsi.common.util.ExceptionUtil;
import com.kintexgroup.hkpsi.purchasing.dao.PaymentRecordDao;
import com.kintexgroup.hkpsi.purchasing.entity.PaymentRecord;
import com.kintexgroup.hkpsi.purchasing.model.PaymentRecordDTO;
import com.kintexgroup.hkpsi.purchasing.model.PaymentRecordVO;
import com.kintexgroup.hkpsi.purchasing.service.PaymentRecordService;
import com.kintexgroup.hkpsi.purchasing.util.PaymentRecordConverter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author LMAO
 * @since 2020/12/4 11:53
 */
@Service("PaymentRecordService")
@Transactional(rollbackFor = BusinessException.class)
public class PaymentRecordServiceImpl implements PaymentRecordService {


    @Resource
    private PaymentRecordDao paymentRecordDao;

    @Override
    public boolean updatePaymentRecord(PaymentRecordDTO dto) {
        var entity = PaymentRecordConverter.toUpdateEntity(dto.getWonNumber(), ContextHolderUtil.getAuthedUserId(), dto);
        if (paymentRecordDao.updateByPrimaryKeySelective(entity) != 0) {
            return true;
        } else {
            throw new BusinessException(ResponseCode.DB_UPDATE_ERROR);
        }
    }

    @Override
    public void batchUpdatePaymentRecord(List<PaymentRecordDTO> dtos) {
        var entities = new ArrayList<PaymentRecord>(dtos.size());
        dtos.forEach(dto -> entities.add(PaymentRecordConverter.toUpdateEntity(dto.getWonNumber(), ContextHolderUtil.getAuthedUserId(), dto)));
        ExceptionUtil.isUpdateSuccess(paymentRecordDao.updateBatchSelective(entities));
    }

    @Override
    public PageInfo<PaymentRecordVO> selectOneOrMany(PaymentRecord paymentRecord) {
        var list = new ArrayList<PaymentRecordVO>();
        PageMethod.startPage(paymentRecord.getCurrent(), paymentRecord.getPageSize(), true);
        var records = paymentRecordDao.selectOneOrMany(paymentRecord);
        records.forEach(entity -> list.add(PaymentRecordConverter.fromEntity(entity)));
        return CommonPageConverter.toPageInfo(new Page<PaymentRecord>(), list);
    }
}


