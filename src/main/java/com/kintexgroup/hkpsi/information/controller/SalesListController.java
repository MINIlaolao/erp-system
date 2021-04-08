package com.kintexgroup.hkpsi.information.controller;

import io.swagger.annotations.Api;
import com.kintexgroup.hkpsi.common.interfaces.ResponseWrapper;
import com.kintexgroup.hkpsi.information.model.SalesListCollectVO;
import com.kintexgroup.hkpsi.information.service.SalesListService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author LMAO
 * @since 2020/11/18 18:00
 */
@ResponseWrapper
@RestController
@RequestMapping("/api/information")
@Api(tags = {"salesList"})
public class SalesListController {
    @Resource
    private SalesListService salesListService;

    @GetMapping("/get_sales_list")
    public SalesListCollectVO getSalesList(@RequestParam String wonbidId) {
        return salesListService.selectSalesList(wonbidId);
    }

}


