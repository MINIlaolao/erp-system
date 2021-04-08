package com.kintexgroup.hkpsi.inventory.controller;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.kintexgroup.hkpsi.common.interfaces.ResponseWrapper;
import com.kintexgroup.hkpsi.inventory.model.ImeiList;
import com.kintexgroup.hkpsi.inventory.model.StagePageDTO;
import com.kintexgroup.hkpsi.inventory.model.StageVO;
import com.kintexgroup.hkpsi.inventory.service.StageService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.Pattern;
import java.util.List;

/**
 * @author lmao                pengli
 * @since 2020/8/26 18:08     2020/11/04
 */
@ResponseWrapper
@RestController
@RequestMapping("/api/stage")
@Api(tags = {"虚拟仓库"})
public class StageController {

    @Resource
    private StageService stageService;

    @PostMapping("/add_many_goods")
    @ApiOperation(value = "批量到货", notes = "向goods表添加多条信息")
    public boolean addManyGoods(@RequestBody List<String> imei) {
        stageService.insertMany(imei);
        return true;
    }

    @PostMapping("/batch_add_stage_record")
    @ApiOperation(value = "批量添加预入库记录", notes = "向stage表添加多条信息")
    public void batchAddStageRecord(@Validated @RequestBody List<ImeiList> imeiLists) {
        stageService.batchInsertStageRecord(imeiLists);
    }


    @GetMapping("/get_stage")
    @ApiOperation(value = "查询一条", notes = "根据id查询一条预入库批次详情")
    public StageVO getOneStage(
        @Pattern(regexp = "^-?\\d+(\\.\\d+)?$") String id) {
        return stageService.selectOne(id);
    }

//    @GetMapping("/get_many_stage")
//    @ApiOperation(value = "查询多条", notes = "查询多条预入库批次")
//    public List<StageVO> getAllStage() {
//        return stageService.selectAll();
//    }

    @GetMapping("/get_one_or_many_stage")
    @ApiOperation(value = "查询一条或多条", notes = "查询一条或多条预入库批次")
    public PageInfo<StageVO> getAllStage(StagePageDTO dto) {
        return stageService.selectOneOrMany(dto, dto.getCurrent(), dto.getPageSize());
    }

}
