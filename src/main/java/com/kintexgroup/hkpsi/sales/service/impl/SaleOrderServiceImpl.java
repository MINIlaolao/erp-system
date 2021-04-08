package com.kintexgroup.hkpsi.sales.service.impl;

import com.github.pagehelper.PageInfo;
import com.github.pagehelper.page.PageMethod;
import com.kintexgroup.hkpsi.common.constants.ResponseCode;
import com.kintexgroup.hkpsi.common.exception.BusinessException;
import com.kintexgroup.hkpsi.common.handler.CommonPageConverter;
import com.kintexgroup.hkpsi.common.util.ContextHolderUtil;
import com.kintexgroup.hkpsi.sales.model.SaleOrderSearchPageDTO;
import com.kintexgroup.hkpsi.sales.model.SaleOrderVO;
import com.kintexgroup.hkpsi.sales.model.SalesDTO;
import com.kintexgroup.hkpsi.sales.util.SaleOrderPojoConverter;
import com.kintexgroup.hkpsi.sales.dao.SaleOrderDao;
import com.kintexgroup.hkpsi.sales.entity.SaleOrder;
import com.kintexgroup.hkpsi.sales.service.SaleOrderService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author lmao and pengli
 * @since 2020/9/27 16:06
 */
@Service("SaleOrderService")
@Transactional(rollbackFor = BusinessException.class)
public class SaleOrderServiceImpl implements SaleOrderService {

    @Resource
    private SaleOrderDao saleOrderDao;

    @Override
    public String insert(SalesDTO dto) {
        SaleOrder saleOrder = SaleOrderPojoConverter
            .toCreateEntity(ContextHolderUtil.getAuthedUserId(), dto);
        saleOrderDao.create(saleOrder);
        return saleOrder.getId();
    }

    @Override
    public boolean delete(String id) {
        if (saleOrderDao.delete(id)) {
            return true;
        }
        throw new BusinessException(ResponseCode.DB_ID_NULL_ERROR);
    }

    @Override
    public boolean update(String id, SalesDTO record) {
        SaleOrder saleOrder = SaleOrderPojoConverter
            .toUpdateEntity(id, ContextHolderUtil.getAuthedUserId(), record);
        var number = saleOrderDao.checkId(id);
        if (number < 1) {
            throw new BusinessException(ResponseCode.DB_ID_NULL_ERROR);
        }
        saleOrderDao.update(saleOrder);
        return true;
    }

    @Override
    public SaleOrderVO selectOne(String id) {
        var record = saleOrderDao.selectOne(id);
        if (record == null) {
            throw new BusinessException(ResponseCode.DB_ID_NULL_ERROR);
        }
        return SaleOrderPojoConverter.fromSaleOrder(record);
    }

    @Override
    public List<SaleOrderVO> selectAll() {
        return saleOrderDao.selectAll();
    }

    @Override
    public PageInfo<SaleOrderVO> selectOneOrMany(SaleOrderSearchPageDTO dto, int current,
                                                 int pageSize) {
        PageMethod.startPage(current, pageSize, true);
        var records = saleOrderDao.selectOneOrMany(dto);
        // 对价格进行汇率转换   String.format("%.2f",计算后的价格-0.005)：保留两位小数，-0.005是因为会四舍五入
        records.forEach(
            record -> record.setCountPrice(
                Double.parseDouble(String.format("%.2f", record.getCountPrice() * record.getExchangeRate() - 0.005)))
        );
        var list = new ArrayList<>(records);
        return CommonPageConverter.toPageInfo(records, list);
    }

    @Override
    public void pay(Map<String, String> saleOrderIdDate) {
        var saleOrderId = saleOrderIdDate.get("saleOrderId");
        if (!saleOrderDao.isPayTimeDefault(saleOrderId)) {
            throw new BusinessException(ResponseCode.BIZ_SALES_ORDER_PADDED);
        }
        var date = saleOrderIdDate.get("payDateTime");
        saleOrderDao.updatePayTime(saleOrderId, date);
    }

}
