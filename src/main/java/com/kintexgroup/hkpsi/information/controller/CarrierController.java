package com.kintexgroup.hkpsi.information.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.kintexgroup.hkpsi.common.interfaces.ResponseWrapper;
import com.kintexgroup.hkpsi.information.model.CarrierDTO;
import com.kintexgroup.hkpsi.information.model.CarrierVO;
import com.kintexgroup.hkpsi.information.service.CarrierService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author developer04
 */
@ResponseWrapper
@RestController
@RequestMapping("/api/information")
@Api(tags = {"运营商"})
public class CarrierController {

    private final CarrierService carrierService;

    public CarrierController(CarrierService carrierService) {
        this.carrierService = carrierService;
    }

    @PostMapping("/add_carrier")
    @ApiOperation(value = "添加", notes = "添加一条运营商信息")
    public CarrierVO addCarrier(@RequestBody CarrierDTO dto) {
        return carrierService.saveOne(dto);
    }

    @PostMapping("/change_carrier")
    @ApiOperation(value = "更新", notes = "根据id更新一条运营商信息")
    public boolean changeCarrier(Integer id, @RequestBody CarrierDTO dto) {
        return carrierService.changeOne(id, dto);
    }

    @PostMapping("/delete_carrier")
    @ApiOperation(value = "删除", notes = "根据id 删除一条供应商信息")
    public boolean deleteCarrier(int id) {
        return carrierService.deleteOne(id);
    }

    @GetMapping("/get_carrier")
    @ApiOperation(value = "查询一条", notes = "根据id 查询一条供应商信息")
    public CarrierVO getCarrier(int id) {
        return carrierService.getOne(id);
    }

    @GetMapping("/get_many_carrier_list")
    @ApiOperation(value = "查询所有", notes = "查询所有供应商信息")
    public List<CarrierVO> getCarrierList() {
        return carrierService.getMany();
    }

}
