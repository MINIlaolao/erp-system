package com.kintexgroup.hkpsi.purchasing.controller;

import com.github.pagehelper.PageInfo;
import com.kintexgroup.hkpsi.common.interfaces.ResponseWrapper;
import com.kintexgroup.hkpsi.purchasing.entity.PaymentRecord;
import com.kintexgroup.hkpsi.purchasing.model.PaymentRecordDTO;
import com.kintexgroup.hkpsi.purchasing.model.PaymentRecordVO;
import com.kintexgroup.hkpsi.purchasing.service.PaymentRecordService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Nullable;
import javax.annotation.Resource;

/**
 * @author LMAO
 * @author pegnli
 * @since 2020/12/5 16:14
 * @since 2020/12/7
 */
@ResponseWrapper
@RestController
@RequestMapping("/api/purchasing")
public class PaymentRecordController {

    @Resource
    private PaymentRecordService paymentRecordService;

    @GetMapping("/get_one_or_many_payment_record")
    public PageInfo<PaymentRecordVO> getOneOrManyPaymentRecord(@Nullable PaymentRecord paymentRecord) {
        return paymentRecordService.selectOneOrMany(paymentRecord);
    }

    @PostMapping("/pay_won")
    public Boolean payWon(@RequestBody PaymentRecordDTO dto) {
        return paymentRecordService.updatePaymentRecord(dto);
    }
}


