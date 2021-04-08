package com.kintexgroup.hkpsi.information.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.kintexgroup.hkpsi.common.interfaces.ResponseWrapper;
import com.kintexgroup.hkpsi.information.model.SkuAttributeDTO;
import com.kintexgroup.hkpsi.information.model.SkuAttributeVO;
import com.kintexgroup.hkpsi.information.service.SkuAttributeService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

/**
 * @author pengli
 * @since 2020/9/15 7:44 下午
 */

@ResponseWrapper
@RestController
@RequestMapping("/api/information")
@Api(tags = {"skuAttribute"})
public class SkuAttributeController {

    @Resource
    private SkuAttributeService skuAttributeService;

    @PostMapping("/add_sku_attribute")
    @ApiOperation(value = "添加", notes = "添加一条SkuAttribute信息")
    public boolean addSkuAttribute(Integer categoryId, @RequestBody Set<SkuAttributeDTO> dto) {
        return skuAttributeService.create(categoryId, dto);
    }

    @PostMapping("/change_sku_attribute")
    @ApiOperation(value = "批量更新", notes = "根据categoryId批量更新SkuAttribute信息")
    public boolean changeSkuAttribute(@NotNull Integer categoryId,
                                      @RequestBody List<SkuAttributeDTO> dto) {
        return skuAttributeService.update(categoryId, dto);
    }


    @GetMapping("/get_sku_attribute")
    @ApiOperation(value = "根据categoryId查询", notes = "根据categoryId查询SkuAttribute信息")
    public List<SkuAttributeVO> getSkuAttribute(@NotNull Integer id) {
        return skuAttributeService.selectOne(id);
    }

    @GetMapping("/get_sku_attribute_list")
    @ApiOperation(value = "查询所有", notes = "查询所有SkuAttribute信息")
    public List<SkuAttributeVO> getSkuAttributeList() {
        return skuAttributeService.selectAll();
    }

}
