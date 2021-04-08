package com.kintexgroup.hkpsi.information.service;

/**
 * @author LMAO
 * @since 2020/12/14 16:16
 */
public interface KdnLogisticsService {
    /**
     * 接受kdn物流推送
     *
     * @param logisticsMessage 推送信息
     */
    void setLogisticsMessage(Object logisticsMessage);
}


