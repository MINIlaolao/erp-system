package com.kintexgroup.hkpsi.information.service;

import com.kintexgroup.hkpsi.information.model.ContactUsDTO;

/**
 * @author lmao
 * @since 2020/9/14 14:20
 */
public interface ContactUsService {

    /**
     * 插入一条留言信息
     *
     * @param dto 传入的数据
     * @return boolean
     */
    boolean receiptAchieve(ContactUsDTO dto);


}
