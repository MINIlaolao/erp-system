package com.kintexgroup.hkpsi.information.dao;

import com.kintexgroup.hkpsi.information.entity.ContactUs;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author lmao
 * @since 2020/9/14 14:20
 */
public interface ContactUsDao extends Mapper<ContactUs> {

    /**
     * 新增一条
     *
     * @param record ContactUs
     * @return 是否成功
     */
    @Override
    int insert(ContactUs record);
}