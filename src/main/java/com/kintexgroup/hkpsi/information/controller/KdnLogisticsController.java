package com.kintexgroup.hkpsi.information.controller;

import com.kintexgroup.hkpsi.common.interfaces.ResponseWrapper;
import com.kintexgroup.hkpsi.information.service.KdnLogisticsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author LMAO
 * @since 2020/12/14 16:14
 */
@ResponseWrapper
@RestController
@RequestMapping("/api/common/kdn")
public class KdnLogisticsController {
    @Resource
    private KdnLogisticsService kdnLogisticsService;

    @PostMapping("/set_logistics_message")
    public void setLogisticsMessage(@RequestBody Object logisticsMessage) {
        kdnLogisticsService.setLogisticsMessage(logisticsMessage);
    }
}


