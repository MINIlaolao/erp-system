package com.kintexgroup.hkpsi.inventory.controller;

import com.kintexgroup.hkpsi.common.interfaces.ResponseWrapper;
import com.kintexgroup.hkpsi.inventory.dto.AddCartonDTO;
import com.kintexgroup.hkpsi.inventory.model.CartonInfoVO;
import com.kintexgroup.hkpsi.inventory.model.CartonVO;
import com.kintexgroup.hkpsi.inventory.service.CartonService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author LMAO
 * @since 2021/1/25 11:38
 */
@RestController
@ResponseWrapper
@RequestMapping("/api/carton")
public class CartonController {
    @Resource
    private CartonService cartonService;

    @PostMapping("/new_carton")
    public CartonVO newCarton(@RequestBody @Validated AddCartonDTO dto) {
        return cartonService.createNewCarton(dto);
    }

    @GetMapping("/generate_new_carton_number")
    public int generateCartonNumber() {
        return cartonService.generateNewCartonNumber();
    }

    @GetMapping("/info")
    public CartonInfoVO getCartonInfo(@RequestParam("i") String id,
                                      @RequestParam("d") String date,
                                      @RequestParam("n") String number) {
        return cartonService.queryCartonInfo(id, date, number);
    }

}


