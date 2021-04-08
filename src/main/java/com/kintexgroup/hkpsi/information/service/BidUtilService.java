package com.kintexgroup.hkpsi.information.service;

import com.kintexgroup.hkpsi.information.model.BidUtilDTO;
import com.kintexgroup.hkpsi.information.model.BidUtilVO;

/**
 * @author LMAO
 * @since 2020/12/1 16:36
 */
public interface BidUtilService {

    /**
     * 查询历史售价
     *
     * @param dto 供应商代码和tags
     * @return 历史售价vo
     */
    BidUtilVO getBidUtil(BidUtilDTO dto);
}
