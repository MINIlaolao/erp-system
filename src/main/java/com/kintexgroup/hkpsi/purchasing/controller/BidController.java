package com.kintexgroup.hkpsi.purchasing.controller;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.kintexgroup.hkpsi.common.constants.ResponseCode;
import com.kintexgroup.hkpsi.common.interfaces.ResponseWrapper;
import com.kintexgroup.hkpsi.common.util.ExceptionUtil;
import com.kintexgroup.hkpsi.purchasing.model.BidDTO;
import com.kintexgroup.hkpsi.purchasing.model.BidDetailVO;
import com.kintexgroup.hkpsi.purchasing.model.BidPageDTO;
import com.kintexgroup.hkpsi.purchasing.model.BidVO;
import com.kintexgroup.hkpsi.purchasing.service.BidService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author junhangs            pengli
 * @since 2020/8/23 22:27      2020/11/5
 */
@ResponseWrapper
@RestController
@RequestMapping("/api/purchasing")
@Api(tags = {"投标批次"})
public class BidController {

    @Resource
    private BidService bidService;

    @GetMapping("/get_new_bid_id")
    @ApiOperation(value = "生成新的批次编号", notes = "生成新的批次编号")
    public String getNewBidId(String date) {
        return bidService.generateNewBidId(date);
    }

    @PostMapping("/add_bid")
    @ApiOperation(value = "添加", notes = "添加一条批次信息")
    public String addBid(@RequestBody BidDTO dto) {
        return bidService.create(dto);
    }

    @PostMapping("/change_bid")
    @ApiOperation(value = "更新", notes = "根据id更新一条批次信息")
    public BidVO changeBid(String id, @RequestBody BidDTO dto) {
        return bidService.update(id, dto);
    }

    @PostMapping("/delete_bid")
    @ApiOperation(value = "删除", notes = "根据id删除一条批次信息")
    public boolean deleteBid(@NotNull String id) {
        return bidService.delete(id);
    }

    @GetMapping("/get_bid_detail")
    public BidDetailVO getBidDetail(String id) {
        return bidService.getBidDetailById(id);
    }

    @GetMapping("/get_bid_detail_list")
    public List<BidDetailVO> getBidDetailList() {
        return bidService.getBidDetailList();
    }

    @GetMapping("/get_bid")
    @ApiOperation(value = "查询一条", notes = "根据id查询一条批次信息")
    public BidVO getBid(String id) {
        ExceptionUtil.checkNotNull(id, ResponseCode.CRUD_INPUT_ID_NOT_EXIST.getMessage());
        return bidService.selectOne(id);
    }

    @GetMapping("/get_all_bid")
    @ApiOperation(value = "查询所有", notes = "查询所有批次信息")
    public List<BidVO> getAllBid() {
        return bidService.selectAll();
    }

    @GetMapping("/list")
    @ApiOperation(value = "查询一条或多条投标信息", notes = "根据不同查询条件查询一条或多条投标信息")
    public PageInfo<BidVO> list(BidPageDTO dto) {
        return bidService.list(dto);
    }
}
