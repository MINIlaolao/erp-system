package com.kintexgroup.hkpsi.information.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * hkpsi_vendor
 *
 * @author junhangs
 * <p>
 * Serializable ：实现序列化接口。 序列化：把对象转换为字节序列的过程
 */
@Data
public class Vendor
    implements Serializable {

    /**
     * 供应商 id 内部使用
     */
    private Integer id;

    /**
     * 供应商代号
     */
    private String code;

    /**
     * 供应商名称
     */
    private String name;

    /**
     * 是否可用
     */
    private Integer disabled;

    /**
     * 创建时间 数据表自动生成
     */
    private Date createdAt;

    /**
     * 创建人ID
     */
    private Integer createdBy;

    /**
     * 修改时间 数据表自动生成
     */
    private Date updatedAt;

    /**
     * 修改人ID
     */
    private Integer updatedBy;

    private static final long serialVersionUID = 1L;
}