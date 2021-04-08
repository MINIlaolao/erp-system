package com.kintexgroup.hkpsi.information.model;

import lombok.Data;

/**
 * @author pengli
 * @since 2020/9/10 1:56 下午
 */
@Data
public class ClientDTO {


    /**
     * 顾客名称
     */
    private String name;

    /**
     * 联系方式
     */
    private String contact;

    /**
     * 国家区号
     */
    private String country;

    /**
     * 是否可用
     */
    private Integer disabled;

}
