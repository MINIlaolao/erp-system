package com.kintexgroup.hkpsi.information.service;

import cn.hutool.db.PageResult;
import com.kintexgroup.hkpsi.information.entity.ArrivalRecord;
import com.kintexgroup.hkpsi.information.model.ArrivalRecordDTO;
import com.kintexgroup.hkpsi.information.model.ArrivalRecordVO;

import java.util.List;

/**
 * @author LMAO
 * @since 2020/12/11 10:14
 */
public interface ArrivalRecordService {


    int updateBatchSelective(List<ArrivalRecord> list);

    int batchInsert(List<ArrivalRecordDTO> list);

    PageResult<ArrivalRecordVO> getOneOrManyArrivalRecords(ArrivalRecordDTO dto);


}

