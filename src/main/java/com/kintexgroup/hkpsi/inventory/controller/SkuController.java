package com.kintexgroup.hkpsi.inventory.controller;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.kintexgroup.hkpsi.common.constants.ResponseCode;
import com.kintexgroup.hkpsi.common.interfaces.ResponseWrapper;
import com.kintexgroup.hkpsi.inventory.model.SkuDTO;
import com.kintexgroup.hkpsi.inventory.model.SkuPageDTO;
import com.kintexgroup.hkpsi.inventory.model.SkuVO;
import com.kintexgroup.hkpsi.inventory.service.SkuService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotBlank;

import static com.kintexgroup.hkpsi.common.util.ExceptionUtil.checkNotNull;


/**
 * @author lmao  pengli  2020/11/7
 */
@ResponseWrapper
@RestController
@RequestMapping("/api/inventory")
@Api(tags = {"SKU"})
public class SkuController {

    @Resource
    private SkuService skuService;


    @PostMapping("/delete_sku")
    @ApiOperation(value = "删除", notes = "删除一条Sku信息")
    public boolean deleteSku(Integer id) {
        checkNotNull(id, ResponseCode.CRUD_INPUT_ID_NOT_EXIST.getMessage());
        return skuService.delete(id);
    }


    @GetMapping("/get_one_or_many_sku")
    @ApiOperation(value = "获取多条或者一条Sku记录", notes = "获取多条或者一条Sku记录")
    public PageInfo<SkuVO> getAllSku(SkuPageDTO dto) {
        return skuService.selectAll(dto, dto.getCurrent(), dto.getPageSize());
    }

    @PostMapping("/update_sku")
    @ApiOperation(value = "更新Sku信息")
    public boolean update(@NotBlank Integer id, @RequestBody SkuDTO dto) {
        return skuService.updateOne(id, dto);
    }
}
