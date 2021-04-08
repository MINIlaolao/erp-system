package com.kintexgroup.hkpsi.information.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.kintexgroup.hkpsi.common.constants.ResponseCode;
import com.kintexgroup.hkpsi.common.exception.BusinessException;
import com.kintexgroup.hkpsi.common.interfaces.ResponseWrapper;
import com.kintexgroup.hkpsi.information.model.CategoryDTO;
import com.kintexgroup.hkpsi.information.model.CategoryVO;
import com.kintexgroup.hkpsi.information.service.CategoryService;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;


/**
 * @author lmao
 * @since 2020/9/8 15:55
 */
@RestController
@ResponseWrapper
@RequestMapping("api/information")
@Api(tags = "类别")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(
        CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/add_category")
    @ApiOperation(value = "添加", notes = "添加一条类别信息")
    public Boolean addCategory(@RequestBody CategoryDTO dto) {
        if (dto.getName() == null || dto.getAttributes().size() == 0) {
            throw new BusinessException(ResponseCode.REQUEST_PARAM_INVALID);
        }
        return categoryService.addCategory(dto);
    }

    @PostMapping("/change_category")
    @ApiOperation(value = "更新", notes = "更新一条类别信息")
    public Boolean changeCategory(@NotBlank Integer id, @RequestBody CategoryDTO dto) {
        categoryService.updateOne(id, dto);
        return true;
    }

    @GetMapping("/get_many_category")
    @ApiOperation(value = "查询所有", notes = "查询所有类别信息")
    public List<CategoryVO> getAllCategory() {
        return categoryService.selectMany();
    }

    @GetMapping("/get_one_category")
    @ApiOperation(value = "查询一条", notes = "根据类别id查询类别的所有属性")
    public CategoryVO getOne(@NotNull Integer id) {
        return categoryService.selectOne(id);
    }

    @PostMapping("/delete_one_category")
    @ApiOperation(value = "删除", notes = "根据类别id删除一条类别信息及其属性")
    public boolean deleteOne(@NotNull Integer id) {
        categoryService.deleteOne(id);
        return true;
    }
}
