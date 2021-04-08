package com.kintexgroup.hkpsi.information.controller;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.kintexgroup.hkpsi.common.interfaces.ResponseWrapper;
import com.kintexgroup.hkpsi.information.model.BrandDTO;
import com.kintexgroup.hkpsi.information.model.BrandPageDTO;
import com.kintexgroup.hkpsi.information.model.BrandVO;
import com.kintexgroup.hkpsi.information.service.BrandService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotBlank;

/**
 * @author pengli
 * @since 2020/9/8 7:31 下午
 */
@ResponseWrapper
@RestController
@RequestMapping("/api/information/brand")
@Api(tags = {"Brand"})
public class BrandController {

    @Resource
    private BrandService brandService;

    @PostMapping("/add_brand")
    @ApiOperation(value = "新增一个品牌")
    public BrandVO add(@RequestBody BrandDTO requestBody) {
        return brandService.addOne(requestBody);
    }

    @PostMapping("/remove_brand")
    @ApiOperation(value = "移除一个品牌")
    public String remove(@NotBlank Integer id) {
        return brandService.removeOne(id);
    }

    @PostMapping("/update_brand")
    @ApiOperation(value = "更新品牌信息")
    public boolean update(@NotBlank Integer id, @RequestBody BrandDTO dto) {
        return brandService.updateOne(id, dto);
    }

    @GetMapping("/get_one_or_many_brand")
    @ApiOperation(value = "查询单个或多个品牌信息")
    public PageInfo<BrandVO> get(BrandPageDTO dto) {
        return brandService.selectOneOrMany(dto);
    }
}
