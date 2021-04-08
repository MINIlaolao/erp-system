package com.kintexgroup.hkpsi.information.model;

import com.kintexgroup.hkpsi.information.entity.SalesList;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @author LMAO
 * @since 2020/11/20 12:08
 */
@Data
public class SalesListCollectVO {
    private List<SalesList> salesLists;
    private Map<String, Object> salesListCollect;
}


