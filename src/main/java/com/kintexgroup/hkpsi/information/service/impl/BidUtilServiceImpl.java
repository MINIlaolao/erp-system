package com.kintexgroup.hkpsi.information.service.impl;

import com.kintexgroup.hkpsi.common.exception.BusinessException;
import com.kintexgroup.hkpsi.common.util.ClassPropertyUtil;
import com.kintexgroup.hkpsi.information.dao.BidUtilDao;
import com.kintexgroup.hkpsi.information.model.*;
import com.kintexgroup.hkpsi.information.service.BidUtilService;
import com.kintexgroup.hkpsi.information.util.BidConditionsUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author LMAO
 * @since 2020/12/1 16:53
 */
@Service("BidUtilService")
@Transactional(rollbackFor = BusinessException.class)
public class BidUtilServiceImpl implements BidUtilService {

    private static final int MAX_BID = 3;

    @Resource
    private BidUtilDao bidUtilDao;

    @Override
    public BidUtilVO getBidUtil(BidUtilDTO dto) {
        dto.setLabels(BidConditionsUtil.getFieldByNames(dto.getLabels()));
        // 获取用户传入的筛选条件
        var conditions = dto.getConditions();
        
        // 通过条件查询数据库
        var resultFromDatabase = bidUtilDao.queryBidPriceHistory(dto);

        BidUtilVO bidUtilVO = new BidUtilVO();
        // 如果查询结果为空，直接返回空数组
        if (resultFromDatabase == null || resultFromDatabase.isEmpty()) {
            bidUtilVO.setList(new ArrayList<>());
            return bidUtilVO;
        }
        // 以上传的数组为顺序填充查询的价格
        var list = conditions.stream().map(condition -> {
            var record = new ResultItemAboutQueryTheBidPrice();
            var result = resultFromDatabase.stream()
                .filter(x -> x.getConditions().customEquals(condition)).collect(Collectors.toList());
            if (result.size() > 0) {
                record.setConditions(condition);
                record.setHistories(new ArrayList<>()); 
                for (var i = 0; i < MAX_BID; i++) {
                    if (i == result.size()) {
                        break;
                    }
                    var item = result.get(i);
                    var history = new HistoryAboutTheBid();
                    history.setPrice(item.getPrice());
                    history.setHighPrice(item.getHighPrice());
                    record.getHistories().add(history);
                }
                return record;
            }
            return null;
        }).collect(Collectors.toList());
        
        bidUtilVO.setList(list);
        
        return bidUtilVO;
    }


    private Price toPrice(RecordAboutQueryTheBidPrice priceModel) {
        Price price = new Price();
        price.setPrice(priceModel.getPrice());
        price.setQuantity(priceModel.getQuantity());
        var bidPrice = priceModel.getPrice();
        if (bidPrice == null) {
            bidPrice = 0.0;
        }
        price.setPrice(bidPrice);
        price.setExchangeRate(priceModel.getExchangeRate());
        return price;
    }


}


