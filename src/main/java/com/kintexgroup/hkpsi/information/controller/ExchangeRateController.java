package com.kintexgroup.hkpsi.information.controller;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.kintexgroup.hkpsi.common.interfaces.ResponseWrapper;
import com.kintexgroup.hkpsi.information.model.ExchangeRateDTO;
import com.kintexgroup.hkpsi.information.model.ExchangeRatePageDTO;
import com.kintexgroup.hkpsi.information.model.ExchangeRateVO;
import com.kintexgroup.hkpsi.information.service.ExchangeRateService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotBlank;


/**
 * @author pengli
 * @since 2020/11/13 11:44 上午
 */

@ResponseWrapper
@RestController
@RequestMapping("/api/information/exchange_rate")
@Api(tags = {"汇率"})
public class ExchangeRateController {
    @Resource
    private ExchangeRateService exchangeRateService;

    @PostMapping("/add_exchange_rate")
    @ApiOperation(value = "新增一个汇率")
    public ExchangeRateVO add(@RequestBody ExchangeRateDTO requestBody) {
        return exchangeRateService.insertOne(requestBody);
    }

    @PostMapping("/remove_exchange_rate")
    @ApiOperation(value = "移除一个汇率")
    public Boolean remove(@NotBlank Integer id) {
        return exchangeRateService.removeOne(id);
    }

    @PostMapping("/update_exchange_rate")
    @ApiOperation(value = "更新一个汇率")
    public boolean update(@NotBlank Integer id, @RequestBody ExchangeRateDTO dto) {
        exchangeRateService.updateOne(id, dto);
        return true;
    }

    @GetMapping("/get_one_or_many_exchange_rate")
    @ApiOperation(value = "查询单个或多个汇率信息")
    public PageInfo<ExchangeRateVO> getOneOrManyExchangeRate(ExchangeRatePageDTO dto) {
        return exchangeRateService.selectOneOrMany(dto, dto.getCurrent(), dto.getPageSize());
    }

}
