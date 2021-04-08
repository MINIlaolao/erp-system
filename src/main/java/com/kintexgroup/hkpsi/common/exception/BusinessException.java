package com.kintexgroup.hkpsi.common.exception;

import com.kintexgroup.hkpsi.common.constants.ResponseCode;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 业务异常类，继承运行时异常，确保事务正常回滚
 *
 * @author gradylo
 * @since 2020/8/23 22:12
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class BusinessException extends RuntimeException {

    private final ResponseCode code;

    public BusinessException(ResponseCode code) {
        this.code = code;
    }

    public BusinessException(Throwable cause, ResponseCode code) {
        super(cause);
        this.code = code;
    }

}
