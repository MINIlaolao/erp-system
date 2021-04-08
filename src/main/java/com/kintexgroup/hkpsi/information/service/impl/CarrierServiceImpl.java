package com.kintexgroup.hkpsi.information.service.impl;

import com.kintexgroup.hkpsi.common.constants.ResponseCode;
import com.kintexgroup.hkpsi.common.exception.BusinessException;
import com.kintexgroup.hkpsi.common.util.ContextHolderUtil;
import com.kintexgroup.hkpsi.information.dao.CarrierDao;
import com.kintexgroup.hkpsi.information.entity.Carrier;
import com.kintexgroup.hkpsi.information.model.CarrierDTO;
import com.kintexgroup.hkpsi.information.model.CarrierVO;
import com.kintexgroup.hkpsi.information.service.CarrierService;
import com.kintexgroup.hkpsi.information.util.CarrierPojoConverter;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author gradylo
 * @Transactional : 事务回滚
 * @Transactional(rollbackFor = BusinessException.class) rollbackFor : 用于指定能够触发事务回滚的异常类型
 * @since 2020/8/23 14:42
 */
@Service("CarrierService")
@Transactional(rollbackFor = BusinessException.class)
public class CarrierServiceImpl implements CarrierService {

    private final CarrierDao carrierDao;

    public CarrierServiceImpl(CarrierDao carrierDao) {
        this.carrierDao = carrierDao;
    }

    @Override
    public CarrierVO saveOne(CarrierDTO dto) {
        Carrier carrier = CarrierPojoConverter
            .toCreateEntity(ContextHolderUtil.getAuthedUserId(), dto);
        if (carrierDao.check(carrier.getName(), carrier.getRegion())) {
            throw new BusinessException(ResponseCode.DB_INSERT_DUPLICATE_ERROR);
        }
        if (carrierDao.insertOne(carrier)) {
            return getOne(carrier.getId());
        }
        return null;
    }

    @Override
    public boolean changeOne(Integer id, CarrierDTO dto) {
        Carrier carrier = CarrierPojoConverter
            .toUpdateEntity(id, ContextHolderUtil.getAuthedUserId(), dto);
        if (carrierDao.check(carrier.getName(), carrier.getRegion())) {
            throw new BusinessException(ResponseCode.DB_UPDATE_DUPLICATE_ERROR);
        }
        try {
            return carrierDao.updateOne(carrier);
        } catch (DataIntegrityViolationException e) {
            throw new BusinessException(e.getCause(), ResponseCode.DB_UPDATE_DUPLICATE_ERROR);
        }
    }

    @Override
    public boolean deleteOne(Integer id) {
        carrierDao.deleteOne(id);
        return true;
    }

    @Override
    public CarrierVO getOne(Integer id) {
        Carrier carrier = carrierDao.selectOne(id);
        if (carrier == null) {
            return null;
        }
        return CarrierPojoConverter.fromEntity(carrier);
    }

    @Override
    public List<CarrierVO> getMany() {
        var records = carrierDao.selectMany();
        //定义列表用于存放每条记录
        var list = new ArrayList<CarrierVO>(records.size());

        //判断records是否为空  如果为空  返回空列表
        if (records.isEmpty()) {
            return list;
        }

        //records 不为空 用for遍历  把每一条信息 添加到list中 最后返回list
        for (var record : records) {
            list.add(CarrierPojoConverter.fromEntity(record));
        }
        return list;
    }

}
