package com.kintexgroup.hkpsi.information.controller;

import com.kintexgroup.hkpsi.common.interfaces.ResponseWrapper;
import com.kintexgroup.hkpsi.information.model.BidUtilDTO;
import com.kintexgroup.hkpsi.information.model.BidUtilVO;
import com.kintexgroup.hkpsi.information.service.BidUtilService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author LMAO
 * @since 2020/12/1 16:05
 */
@RestController
@ResponseWrapper
@RequestMapping("/api/information")
public class BidUtilController {
    @Resource
    private BidUtilService bidUtilService;

    @PostMapping("/get_bid_util")
    public BidUtilVO getBidUtil(@RequestBody BidUtilDTO dto) {
        return bidUtilService.getBidUtil(dto);
    }
}


