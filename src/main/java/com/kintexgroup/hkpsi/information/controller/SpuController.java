package com.kintexgroup.hkpsi.information.controller;


import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.kintexgroup.hkpsi.common.constants.ResponseCode;
import com.kintexgroup.hkpsi.common.exception.BusinessException;
import com.kintexgroup.hkpsi.common.interfaces.ResponseWrapper;
import com.kintexgroup.hkpsi.information.model.SpuDTO;
import com.kintexgroup.hkpsi.information.model.SpuPageDTO;
import com.kintexgroup.hkpsi.information.model.SpuVO;
import com.kintexgroup.hkpsi.information.service.SpuService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;

/**
 * @author pengli
 * @since 2020/9/8 3:09 下午
 */
@ResponseWrapper
@RestController
@RequestMapping("/api/information")
@Api(tags = {"spu"})
public class SpuController {

    @Resource
    private SpuService spuService;

    @PostMapping("/add_spu")
    @ApiOperation(value = "添加", notes = "添加一条Spu信息")
    public void addSpu(@RequestBody SpuDTO dto) {
        if (dto.getName() == null || dto.getBrand() == null) {
            throw new BusinessException(ResponseCode.REQUEST_PARAM_INVALID);
        }
        spuService.create(dto);
    }

    @PostMapping("/batch_add_spu")
    public void batchAddSpu(@RequestBody SpuDTO[] dtoList) {
        if (dtoList.length == 0) {
            throw new BusinessException(ResponseCode.REQUEST_PARAM_INVALID);
        }
        spuService.batchAddSpuAndGenerateSku(dtoList);
    }

    @PostMapping("/change_spu")
    @ApiOperation(value = "更新", notes = "更新一条Spu信息")
    public SpuVO changeSpu(@NotNull Integer id, @RequestBody SpuDTO dto) {
        if (dto.getName() == null && dto.getBrand() == null) {
            throw new BusinessException(ResponseCode.REQUEST_PARAM_INVALID);
        }
        return spuService.update(id, dto);
    }

    @PostMapping("/delete_spu")
    @ApiOperation(value = "删除", notes = "根据id删除一条Spu信息")
    public boolean deleteSpu(@NotNull Integer id) {
        return spuService.delete(id);
    }

    @GetMapping("/get_one_or_many_spu")
    @ApiOperation(value = "获取单条或多条记录", notes = "获取单条或多条SPU记录")
    public PageInfo<SpuVO> getOneOrManySpu(SpuPageDTO dto) {
        return spuService.selectOneOrMany(dto, dto.getCurrent(), dto.getPageSize());
    }

}

