package com.kintexgroup.hkpsi.information.dao;

import com.kintexgroup.hkpsi.information.model.BidUtilDTO;
import com.kintexgroup.hkpsi.information.model.RecordAboutQueryTheBidPrice;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * @author LMAO
 * @since 2020/12/1 17:05
 */
@Mapper
public interface BidUtilDao {


    /**
     * 根据供应商代码和tags 查询供应商
     *
     * @return PriceModelList
     */
    List<RecordAboutQueryTheBidPrice> getBidUtil(BidUtilDTO dto);
    
    List<RecordAboutQueryTheBidPrice> queryBidPriceHistory(BidUtilDTO dto);
}
