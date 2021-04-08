package com.kintexgroup.hkpsi.information.controller;


import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.kintexgroup.hkpsi.common.constants.ResponseCode;
import com.kintexgroup.hkpsi.common.exception.BusinessException;
import com.kintexgroup.hkpsi.common.interfaces.ResponseWrapper;
import com.kintexgroup.hkpsi.information.model.VendorDTO;
import com.kintexgroup.hkpsi.information.model.VendorPageDTO;
import com.kintexgroup.hkpsi.information.model.VendorVO;
import com.kintexgroup.hkpsi.information.service.VendorService;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

/**
 * @author junhangs           pengli
 * @since 2020/8/23 21:49     2020/11//7
 */
@ResponseWrapper
@RestController
@RequestMapping("/api/information")
@Api(tags = {"供应商"})
public class VendorController {

    private final VendorService vendorService;

    public VendorController(VendorService vendorService) {
        this.vendorService = vendorService;
    }

    @PostMapping("/add_vendor")
    @ApiOperation(value = "添加", notes = "添加一条供应商信息")
    public VendorVO addVendor(@RequestBody VendorDTO dto) {
        if (dto.getCode() == null || dto.getName() == null) {
            throw new BusinessException(ResponseCode.REQUEST_PARAM_INVALID);
        }
        return vendorService.create(dto);
    }

    @PostMapping("/change_vendor")
    @ApiOperation(value = "更新", notes = "根据id更新一条供应商信息")
    public boolean changeVendor(@NotNull Integer id, @RequestBody VendorDTO dto) {
        if (dto.getCode() == null && dto.getName() == null && dto.getDisabled() == null) {
            throw new BusinessException(ResponseCode.REQUEST_PARAM_INVALID);
        }
        return vendorService.update(id, dto);
    }

    @PostMapping("/delete_vendor")
    @ApiOperation(value = "删除", notes = "根据id删除一条供应商信息")
    public boolean deleteVendor(@NotNull Integer id) {
        return vendorService.delete(id);
    }

    @GetMapping("/get_one_or_many_vendor")
    @ApiOperation(value = "查询单条或多条", notes = "查询单条或多条供应商信息")
    public PageInfo<VendorVO> getOneOrManyVendor(VendorPageDTO dto) {
        return vendorService.selectOneOrManyVendor(dto, dto.getCurrent(), dto.getPageSize());
    }
}
