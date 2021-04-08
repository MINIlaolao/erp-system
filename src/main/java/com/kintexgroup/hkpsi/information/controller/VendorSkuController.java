package com.kintexgroup.hkpsi.information.controller;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import com.kintexgroup.hkpsi.common.interfaces.ResponseWrapper;
import com.kintexgroup.hkpsi.information.model.vendorsku.VendorSkuPageDTO;
import com.kintexgroup.hkpsi.information.model.VendorSkuVO;
import com.kintexgroup.hkpsi.information.service.VendorSkuService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


/**
 * @author junhangs
 * @since 2020/9/11 11:47
 */
@ResponseWrapper
@RestController
@RequestMapping("/api/information/vendor_sku")
@Api("供应商SKU")
public class VendorSkuController {

    @Resource
    private VendorSkuService vendorSkuService;

    @GetMapping("/list")
    public PageInfo<VendorSkuVO> list(VendorSkuPageDTO dto) {
        return vendorSkuService.list(dto);
    }
}
