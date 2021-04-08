package com.kintexgroup.hkpsi.information.controller;

import cn.hutool.db.PageResult;
import com.kintexgroup.hkpsi.common.interfaces.ResponseWrapper;
import com.kintexgroup.hkpsi.information.model.ArrivalRecordDTO;
import com.kintexgroup.hkpsi.information.model.ArrivalRecordVO;
import com.kintexgroup.hkpsi.information.service.ArrivalRecordService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author LMAO
 * @since 2020/12/11 10:19
 */
@RestController
@ResponseWrapper
@RequestMapping("/api/information")
public class ArrivalRecordController {
    @Resource
    private ArrivalRecordService arrivalRecordService;

    @PostMapping("/create_records")
    public int createRecords(@RequestBody List<ArrivalRecordDTO> dtos) {
        return arrivalRecordService.batchInsert(dtos);
    }


    @GetMapping("/get_one_or_many_arrival_records")
    public PageResult<ArrivalRecordVO> getOneOrManyArrivalRecords(ArrivalRecordDTO dto) {
        return arrivalRecordService.getOneOrManyArrivalRecords(dto);
    }
}


