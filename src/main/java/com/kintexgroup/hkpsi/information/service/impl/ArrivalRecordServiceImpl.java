package com.kintexgroup.hkpsi.information.service.impl;

import cn.hutool.db.PageResult;
import com.github.pagehelper.page.PageMethod;
import com.kintexgroup.hkpsi.common.exception.BusinessException;
import com.kintexgroup.hkpsi.information.dao.ArrivalRecordDao;
import com.kintexgroup.hkpsi.information.entity.ArrivalRecord;
import com.kintexgroup.hkpsi.information.model.ArrivalRecordDTO;
import com.kintexgroup.hkpsi.information.model.ArrivalRecordVO;
import com.kintexgroup.hkpsi.information.service.ArrivalRecordService;
import com.kintexgroup.hkpsi.information.util.ArrivalRecordPojoConverter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author LMAO
 * @since 2020/12/11 10:14
 */
@Service("ArrivalRecordService")
@Transactional(rollbackFor = BusinessException.class)
public class ArrivalRecordServiceImpl implements ArrivalRecordService {

    @Resource
    private ArrivalRecordDao arrivalRecordDao;

    @Override
    public int updateBatchSelective(List<ArrivalRecord> list) {
        return arrivalRecordDao.updateBatchSelective(list);
    }

    @Override
    public int batchInsert(List<ArrivalRecordDTO> list) {
        var entities = new ArrayList<ArrivalRecord>();
        list.forEach(dto -> entities.add(ArrivalRecordPojoConverter.toCreateEntity(dto)));
        return arrivalRecordDao.batchInsert(entities);
    }


    @Override
    public PageResult<ArrivalRecordVO> getOneOrManyArrivalRecords(ArrivalRecordDTO dto) {
        PageMethod.startPage(dto.getCurrent(), dto.getPageSize(), true).setOrderBy("updated_at desc");
        var records = arrivalRecordDao.select(ArrivalRecordPojoConverter.entity(dto));
        var list = new ArrayList<ArrivalRecordVO>();
        records.forEach(entity -> list.add(ArrivalRecordPojoConverter.fromEntity(entity)));
        PageResult<ArrivalRecordVO> vos = new PageResult<>();
        vos.addAll(list);
        return vos;
    }

}

