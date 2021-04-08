package com.kintexgroup.hkpsi.information.service.impl;

import com.kintexgroup.hkpsi.common.exception.BusinessException;
import com.kintexgroup.hkpsi.common.util.ExceptionUtil;
import com.kintexgroup.hkpsi.information.dao.ContactUsDao;
import com.kintexgroup.hkpsi.information.dao.WebsiteMessageDao;
import com.kintexgroup.hkpsi.information.entity.WebsiteMessage;
import com.kintexgroup.hkpsi.information.model.ContactUsDTO;
import com.kintexgroup.hkpsi.information.service.ContactUsService;
import com.kintexgroup.hkpsi.information.util.ContactUsPojoConverter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author lmao
 * @since 2020/9/14 14:20
 */
@Service("ContactUsService")
@Transactional(rollbackFor = BusinessException.class)
public class ContactUsServiceImpl implements ContactUsService {

    @Resource
    private ContactUsDao contactUsDao;

    @Resource
    private WebsiteMessageDao websiteMessageDao;

    @Resource
    private EmailServiceImpl emailService;

    static final String BACK_MSG = "We have received your message.Have a good day!";

    static final String BACK_SERVICE_MSG = "You have new pending customer email by : ";

    /**
     * 插入一条留言信息 - websiteMessageDao.insert(websiteMessage) 每当发送邮件,都要向数据库插入一条记录,除了收信回执
     *
     * @param dto 传入的数据
     * @return boolean
     */
    @Override
    public boolean receiptAchieve(ContactUsDTO dto) {

        //生成 sessionId
        var entity = ContactUsPojoConverter.toEntity(dto);
        var sessionId = entity.getId();
        var customerName = entity.getName();
        var customerEmail = entity.getEmail();
        //给客户发送收信回执
        emailService
            .sendTextMail(customerEmail, "Response information by kintex.etc: " + sessionId,
                BACK_MSG);

        //记录客户信息
        ExceptionUtil
            .isInsertSuccess(contactUsDao.insert(entity));

        //TODO service email address,pending to ask bank president
        var serviceEmail = "lmaol@kintex.net";

        WebsiteMessage websiteMessage = new WebsiteMessage();
        websiteMessage.setSessionId(sessionId);
        websiteMessage.setFrom(customerEmail);
        websiteMessage.setTo(serviceEmail);
        websiteMessage.setMessage(BACK_MSG);

        //给客服发送客户的留言
        emailService
            .sendTextMail("lmaol@kintex.net",
                BACK_SERVICE_MSG + customerName + " ,session_id: " + sessionId,
                dto.getMessage());

        ExceptionUtil
            .isInsertSuccess(websiteMessageDao.insert(websiteMessage));

        return true;
    }

}