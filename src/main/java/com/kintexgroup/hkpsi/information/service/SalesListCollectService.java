package com.kintexgroup.hkpsi.information.service;

import com.kintexgroup.hkpsi.information.entity.SalesListCollect;

import java.util.List;

/**
 * @author LMAO
 * @since 2020/11/17 14:02
 */
public interface SalesListCollectService {

    /**
     * 批量添加
     *
     * @param list list
     * @return 是否成功
     */
    int batchInsert(List<SalesListCollect> list);

}
