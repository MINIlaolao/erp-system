package com.kintexgroup.hkpsi.information.controller;

import com.kintexgroup.hkpsi.common.interfaces.ResponseWrapper;
import com.kintexgroup.hkpsi.information.entity.VendorProgramEntity;
import com.kintexgroup.hkpsi.information.service.VendorProgramService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@ResponseWrapper
@RestController
@RequestMapping("/api/information/vendor_program")
@Api(tags = {"投标项目"})
public class VendorProgramController {
    
    @Resource
    private VendorProgramService vendorProgramService;
    
    @GetMapping("/list")
    public List<VendorProgramEntity> list() {
        return vendorProgramService.list();
    }
}
