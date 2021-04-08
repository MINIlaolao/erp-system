package com.kintexgroup.hkpsi.information.service;

import com.kintexgroup.hkpsi.information.model.SalesListCollectVO;

/**
 * @author LMAO
 * @since 2020/11/17 11:52
 */
public interface SalesListService {

    /**
     * 根据中标号查询单条销售报表,没查到则生成新的销售报表
     *
     * @param wonbidId 中标号
     * @return 单条销售报表
     */
    SalesListCollectVO selectSalesList(String wonbidId);
}


