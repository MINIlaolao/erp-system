package com.kintexgroup.hkpsi.information.service.impl;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import com.kintexgroup.hkpsi.common.exception.BusinessException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.mail.*;
import java.util.Properties;

/**
 * @author lmao
 * @since 2020/9/15 11:02
 */
@Transactional(rollbackFor = BusinessException.class)
@Service("EmailService")
@Slf4j
public class EmailServiceImpl {

    @Resource
    private JavaMailSender javaMailSender;


    /**
     * 发件人。这里发件人一般是同使用的发件邮箱一致
     */
    @Value("${spring.mail.username}")
    private String from;

    @Value("${spring.mail.password}")
    private String password;


    /**
     * 发送文本邮件
     *
     * @param to      收件人邮箱地址
     * @param subject 主题
     * @param content 内容
     */
    public void sendTextMail(String to,
                             String subject,
                             String content) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(content);
        simpleMailMessage.setFrom(from);
        javaMailSender.send(simpleMailMessage);
    }

    /**
     * 定时查询收件箱的未读信息,并转发
     */
    private void receiveMailAndForward() {
        throw new UnsupportedOperationException("定时查询收件箱的未读信息,并转发");
    }


    /**
     * 转发客户的留言给客服, 根据收件箱的未读邮件进行转发给客户,发送的主题
     *
     * @throws MessagingException 消息传递类引发的所有异常
     */
    private void forward(@NonNull Message[] unreadList) throws MessagingException {
        for (Message message : unreadList) {
            message.getSubject();
        }
    }

    /**
     * 从business 邮箱文件夹中获取所有Message对象
     *
     * @throws MessagingException 消息传递类引发的所有异常
     */
    private void receive() throws MessagingException {
        Properties props = new Properties();
        //指定邮件接收协议
        props.put("mail.store.protocol", "imap");
        //指定支持SMTP协议的Transport具体类，允许由第三方提供
        props.put("mail.smtp.class",
            "com.sun.mail.smtp.SMTPTransport");
        //指定支持IMAP协议的Store具体类，允许由第三方提供
        props.put("mail.imap.class",
            "com.sun.mail.imap.IMAPStore");
        // 设置环境信息 
        Session session = Session.getInstance(props);

        Store store = session.getStore("pop3");

        store.connect("pop.exmail.qq.com", from, password);

        //获得名为默认"inbox"的邮件夹当你自己有定义其他的邮件夹也可以写上去
        Folder folder = store.getFolder("inbox");

        //打开邮件夹
        folder.open(Folder.READ_WRITE);

        //test
        log.info("你一共有:" + folder.getMessageCount() + " 封邮件");
        log.info("你一共有:" + folder.getUnreadMessageCount() + " 未读邮件");
        log.info("你一共删除了 " + folder.getDeletedMessageCount() + " 封邮件");

        Message[] messages;
        //判断是否有未读消息
        if (folder.getUnreadMessageCount() > 0) {
            messages = folder.getMessages();
            folder.close();
            store.close();
            int size = messages.length;
            int index = 0;
            Message[] unreadList = new Message[size];
            for (Message msg : messages) {
                //筛选未读消息
                if (!msg.isSet(Flags.Flag.SEEN)) {
                    unreadList[index] = msg;
                    index++;
                }
            }
            forward(unreadList);
        }

    }

}
