package com.kintexgroup.hkpsi.information.dao;

import com.kintexgroup.hkpsi.information.entity.WebsiteMessage;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author lmao
 * @since 2020/9/15 10:50
 */
public interface WebsiteMessageDao extends Mapper<WebsiteMessage> {

    /**
     * 新建一条官网联系我们信息
     *
     * @param websiteMessage 官网联系我们信息
     * @return 是否成功
     */
    @Override
    int insert(WebsiteMessage websiteMessage);
}