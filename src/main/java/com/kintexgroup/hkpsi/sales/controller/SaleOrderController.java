package com.kintexgroup.hkpsi.sales.controller;

import com.github.pagehelper.PageInfo;
import com.kintexgroup.hkpsi.common.interfaces.ResponseWrapper;
import com.kintexgroup.hkpsi.sales.model.SaleOrderSearchPageDTO;
import com.kintexgroup.hkpsi.sales.model.SaleOrderVO;
import com.kintexgroup.hkpsi.sales.model.SalesDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.kintexgroup.hkpsi.sales.service.SaleOrderService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author pengli
 * @since 2020/9/27 6:48 下午
 */
@ResponseWrapper
@RestController
@RequestMapping("/api/sales")
@Api(tags = {"sale_order"})
public class SaleOrderController {

    @Resource
    private SaleOrderService saleOrderService;

    @PostMapping("/add_sale_order")
    @ApiOperation(value = "添加", notes = "添加一条订单信息")
    public String addSaleOrderVO(@RequestBody SalesDTO dto) {
        return saleOrderService.insert(dto);
    }

    @PostMapping("/change_sale_order")
    @ApiOperation(value = "更新", notes = "更新一条订单信息")
    public boolean changeSaleOrder(@RequestParam String id, @RequestBody SalesDTO dto) {
        return saleOrderService.update(id, dto);
    }

    @PostMapping("/pay")
    @ApiOperation(value = "付款", notes = "更新付款时间,设备售出时间")
    public void payment(@RequestBody Map<String, String> saleOrderIdDate) {
        saleOrderService.pay(saleOrderIdDate);
    }


    @PostMapping("/delete_sale_order")
    @ApiOperation(value = "删除", notes = "删除一条订单信息")
    public boolean deleteSaleOrderVO(@RequestParam String id) {
        return saleOrderService.delete(id);
    }

    @GetMapping("/get_one_sale_order")
    @ApiOperation(value = "查询一条", notes = "查询一条订单信息")
    public SaleOrderVO getOneSaleOrderVO(@RequestParam String id) {
        return saleOrderService.selectOne(id);
    }

    @GetMapping("/get_many_sale_order")
    @ApiOperation(value = "查询所有", notes = "添加所有订单信息")
    public List<SaleOrderVO> getAllSaleOrder() {
        return saleOrderService.selectAll();
    }

    @GetMapping("/get_one_or_many_sale_order")
    @ApiOperation(value = "查询一条或多条订单信息", notes = "根据不同查询条件查询一条或多条订单信息")
    public PageInfo<SaleOrderVO> getOneOrManySaleOrder(SaleOrderSearchPageDTO dto) {
        return saleOrderService.selectOneOrMany(dto, dto.getCurrent(), dto.getPageSize());
    }
}
