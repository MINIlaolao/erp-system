package com.kintexgroup.hkpsi.sales.controller;

import com.github.pagehelper.PageInfo;
import com.kintexgroup.hkpsi.common.interfaces.ResponseWrapper;
import com.kintexgroup.hkpsi.sales.model.GoodsPageDTO;
import com.kintexgroup.hkpsi.sales.model.GoodsVO;
import com.kintexgroup.hkpsi.sales.model.SalesDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.kintexgroup.hkpsi.sales.model.GoodsSkuModel;
import com.kintexgroup.hkpsi.sales.service.GoodsService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotBlank;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 * @author lmao
 * @since 2020/9/27 18:30
 */
@RestController
@ResponseWrapper
@RequestMapping("/api/sales")
@Api(tags = {"goods"})
public class GoodsController {

    @Resource
    private GoodsService goodsService;


    @GetMapping("/get_goods")
    public Map<String, Object> getGoods(@NotBlank Long imei) {
        return goodsService.getGoods(imei);
    }

    @GetMapping("/get_goods_sku")
    public List<GoodsSkuModel> getGoodsSku(@RequestParam String skuId, @RequestParam Integer clientId) {
        return goodsService.getGoodsSku(skuId, clientId);
    }


    @PostMapping("/get_bill")
    public String getBill(@Validated @RequestBody SalesDTO dto) throws ParseException {
        return goodsService.getBill(dto);
    }

    @GetMapping("/list")
    public GoodsVO[] getAllGoods() {
        return goodsService.getAllGoods();
    }

    @GetMapping("/get_goods_by_order")
    public Map<String, Object> getGoodsByOrder(@NotBlank String saleOrderId) {
        return goodsService.getOneGoodsBySaleOrderId(saleOrderId);
    }

    @GetMapping("/get_one_or_many_goods")
    @ApiOperation(value = "查询一条或多条商品信息", notes = "查询一条或多条商品信息")
    public PageInfo<GoodsVO> getOneOrManyGoods(GoodsPageDTO dto) {
        return goodsService.selectOneOrManyGoods(dto, dto.getCurrent(), dto.getPageSize());
    }
}