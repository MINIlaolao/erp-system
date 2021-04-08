package com.kintexgroup.hkpsi.common.dto;


import lombok.Data;

import java.io.Serializable;

/**
 * 统一的公共响应体
 *
 * @author gradylo
 */
@Data
public class ResponseDTO implements Serializable {

    private static final long serialVersionUID = 5552988681102155609L;
    /**
     * == 0: 成功 != 0: 失败
     */
    private Integer code;

    /**
     * 描述信息
     */
    private String message;

    /**
     * 数据
     */
    private Object data;

    /**
     * 是否成功
     */
    private Boolean success;

    public ResponseDTO(Integer code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
        this.success = code == 0;
    }
}
