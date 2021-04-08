package com.kintexgroup.hkpsi.purchasing.controller;

import com.github.pagehelper.PageInfo;
import com.kintexgroup.hkpsi.common.interfaces.ResponseWrapper;
import com.kintexgroup.hkpsi.information.model.KdnLogisticsIdName;
import com.kintexgroup.hkpsi.information.model.LogisticsResultVO;
import com.kintexgroup.hkpsi.purchasing.model.ShipDTO;
import com.kintexgroup.hkpsi.purchasing.model.wonbid.*;
import com.kintexgroup.hkpsi.purchasing.service.WonBidService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

/**
 * @author lmao             pengli
 * @since 2020/9/9 10:10   2020/11/4
 */
@RestController
@ResponseWrapper
@RequestMapping("/api/purchasing")
@Api(tags = "中标")
public class WonBidController {

    private final WonBidService wonBidService;

    public WonBidController(WonBidService wonBidService) {
        this.wonBidService = wonBidService;
    }

    @GetMapping("/get_won_bid")
    public WonBidRecordVO getWonBid(String id) {
        return wonBidService.selectOneById(id);
    }

    @GetMapping("/get_all_won_bid")
    public List<WonBidVendorVO> getAllWonBid() {
        return wonBidService.selectMany();
    }

    @PostMapping("/create_won_bid")
    public boolean createWonBid(@Validated @RequestBody WonBidDTO dto) throws ParseException {
        wonBidService.create(dto);
        return true;
    }

    @PostMapping("/change_won_bid")
    public Boolean changeWonBid(String id, @RequestBody WonBidDTO dto) {
        return wonBidService.updateOne(id, dto);
    }

    @PostMapping("/delete_won_bid")
    public Boolean deleteWonBid(String id) {
        return wonBidService.deleteOneById(id);
    }


    @GetMapping("/get_one_or_many_won_bid")
    @ApiOperation(value = "查询一条或多条中标信息", notes = "查询一条或多条中标信息")
    public PageInfo<WonBidVO> getAllWonBid(WinBidPageDTO dto) {
        return wonBidService.selectOneOrMany(dto, dto.getCurrent(), dto.getPageSize());
    }

    @GetMapping("/get_logistics_by_won_number")
    public LogisticsResultVO getLogisticsByWonNumber(@RequestParam String wonNumber) throws Exception {
        return wonBidService.getLogisticsByWonNumber(wonNumber);
    }

    @PostMapping("/ship")
    public void ship(@RequestBody ShipDTO dto) {
        wonBidService.setShipperCode(dto);
    }

    @GetMapping("/get_logistics_list")
    public List<KdnLogisticsIdName> getLogisticsList() {
        return wonBidService.getLogisticsList();
    }

    @GetMapping("/get_won_bid_number")
    public String getWonBidNumber(@RequestParam String yearMonth, @RequestParam String vendorId) {
        return wonBidService.getWonBidNumber(yearMonth, vendorId);
    }
}
