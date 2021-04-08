package com.kintexgroup.hkpsi.information.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * @author lmao
 * @since 2020/9/15 10:50
 */
@Data
@Table(name = "website_wireless_message")
public class WebsiteMessage implements Serializable {

    /**
     * 自增id
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private Long id;

    /**
     * 工单号
     */
    @Column(name = "session_id")
    private Long sessionId;

    /**
     * 发件人
     */
    @Column(name = "from")
    private String from;

    /**
     * 收件人
     */
    @Column(name = "to")
    private String to;

    /**
     * 信息
     */
    @Column(name = "message")
    private String message;

    @Column(name = "created_time")
    private Date createdTime;

    private static final long serialVersionUID = 1L;
}