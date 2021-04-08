package com.kintexgroup.hkpsi.information.service.impl;

import com.github.pagehelper.PageInfo;
import com.github.pagehelper.page.PageMethod;
import com.kintexgroup.hkpsi.common.constants.ResponseCode;
import com.kintexgroup.hkpsi.common.exception.BusinessException;
import com.kintexgroup.hkpsi.common.handler.CommonPageConverter;
import com.kintexgroup.hkpsi.common.util.ContextHolderUtil;
import com.kintexgroup.hkpsi.information.dao.ExchangeRateDao;
import com.kintexgroup.hkpsi.information.entity.ExchangeRate;
import com.kintexgroup.hkpsi.information.model.ExchangeRateDTO;
import com.kintexgroup.hkpsi.information.model.ExchangeRatePageDTO;
import com.kintexgroup.hkpsi.information.model.ExchangeRateVO;
import com.kintexgroup.hkpsi.information.service.ExchangeRateService;
import com.kintexgroup.hkpsi.information.util.ExchangeRatePojoConverter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;


/**
 * @author pengli
 * @since 2020/11/13 11:22 上午
 */
@Service("ExchangeRateService")
@Transactional(rollbackFor = BusinessException.class)
public class ExchangeRateServiceImpl implements ExchangeRateService {
    @Resource
    private ExchangeRateDao exchangeRateDao;


    @Override
    public ExchangeRateVO insertOne(ExchangeRateDTO dto) {
        int creator = ContextHolderUtil.getAuthedUserId();
        // 转为插入实体类
        ExchangeRate newRecord = ExchangeRatePojoConverter.toCreateEntity(creator, dto);
        String code = dto.getCurrencyCode();
        //根据货币去查询表中是否已存在该货币的记录 不为空 说明该货币已存在，不可重复创建
        if (exchangeRateDao.selectByCode(code) != null) {
            throw new BusinessException(ResponseCode.DB_INSERT_DUPLICATE_ERROR);
        } else {

            exchangeRateDao.insertOne(newRecord);
            return selectById(newRecord.getId());
        }
    }

    @Override
    public void updateOne(int id, ExchangeRateDTO dto) {
        var inDatabaseRecord = exchangeRateDao.selectById(id);
        if (inDatabaseRecord == null) {
            throw new BusinessException(ResponseCode.DB_ID_NULL_ERROR);
        }
        boolean isDatabaseEqual = !((inDatabaseRecord.getDisabled().equals(dto.getDisabled())) && (inDatabaseRecord.getExchangeRateDouble().equals(dto.getExchangeRate())));
        if (!isDatabaseEqual) {
            int updater = ContextHolderUtil.getAuthedUserId();
            ExchangeRate entity = ExchangeRatePojoConverter.toUpdateEntity(id, updater, dto);
            exchangeRateDao.updateOne(entity);
        }
    }

    @Override
    public boolean removeOne(int id) {
        if (exchangeRateDao.removeOne(id)) {
            return true;
        } else {
            throw new BusinessException(ResponseCode.DB_ID_NULL_ERROR);
        }
    }

    @Override
    public ExchangeRateVO selectById(int id) {
        var select = exchangeRateDao.selectById(id);
        return ExchangeRatePojoConverter.fromEntity(select);
    }

    @Override
    public PageInfo<ExchangeRateVO> selectOneOrMany(ExchangeRatePageDTO dto, int current, int pageSize) {
        PageMethod.startPage(current, pageSize, true);
        var list = new ArrayList<ExchangeRateVO>();
        var records = exchangeRateDao.selectOneOrMany(dto);
        records.forEach(record -> list.add(ExchangeRatePojoConverter.fromEntity(record)));
        return CommonPageConverter.toPageInfo(records, list);
    }
}
