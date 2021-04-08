package com.kintexgroup.hkpsi.information.service.impl;

import lombok.extern.slf4j.Slf4j;
import com.kintexgroup.hkpsi.common.exception.BusinessException;
import com.kintexgroup.hkpsi.common.util.JsonUtil;
import com.kintexgroup.hkpsi.common.util.KdnUtil;
import com.kintexgroup.hkpsi.information.dao.LogisticsDao;
import com.kintexgroup.hkpsi.information.entity.Logistics;
import com.kintexgroup.hkpsi.information.service.KdnLogisticsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author LMAO
 * @since 2020/12/14 16:20
 */
@Service("KdnLogisticsService")
@Transactional(rollbackFor = BusinessException.class)
@Slf4j
public class KdnLogisticsServiceImpl implements KdnLogisticsService {
    @Resource
    private LogisticsDao logisticsDao;


    /**
     * TODO 待确认返回格式
     *
     * @param logisticsMessage 推送信息
     */
    @SuppressWarnings("unchecked")
    @Override
    public void setLogisticsMessage(Object logisticsMessage) {
        var logisticsResult = (Map<String, Object>) logisticsMessage;
        var allData = (List<Object>) logisticsResult.get("Data");
        var rawData = JsonUtil.toLogisticsResult(JsonUtil.toJsonString((Map<String, String>) allData.get(allData.size() - 1)));
        var traces = rawData.getTraces();
        rawData.setStateEx(rawData.getState());
        var rs = KdnUtil.wrapperResult(rawData, rawData.getShipperCode());
        Logistics logistics = new Logistics();
        logistics.setLogisticsResult(rs);
        logistics.setLogisticsNumber(rs.getShipperCode());
        logisticsDao.updateByLogisticsCode(rs.getShipperCode(), JsonUtil.stringify(rs));
        log.info(logistics.toString());
    }
}


