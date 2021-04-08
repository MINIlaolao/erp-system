package com.kintexgroup.hkpsi.purchasing.controller;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.kintexgroup.hkpsi.common.interfaces.ResponseWrapper;
import com.kintexgroup.hkpsi.purchasing.model.bidrecord.BidRecordPageDTO;
import com.kintexgroup.hkpsi.purchasing.model.bidrecord.BidRecordVO;
import com.kintexgroup.hkpsi.purchasing.service.BidRecordService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author pengli
 */
@ResponseWrapper
@RestController
@RequestMapping("/api/purchasing")
@Api(tags = {"清单"})
public class BidRecordController {

    @Resource
    private BidRecordService bidRecordService;

    @GetMapping("/list_bid_record")
    @ApiOperation(value = "查询投标条目", notes = "查询投标条目")
    public PageInfo<BidRecordVO> list(BidRecordPageDTO args) {
        return bidRecordService.list(args);
    }
}
