package com.kintexgroup.hkpsi.common.constants;

import lombok.Getter;
import lombok.Setter;

/**
 * JWT 常量
 *
 * @author gradylo
 */
public enum JwtType {

    /**
     * Token 超时时间，毫秒，默认8小时
     */
    TIMEOUT_MILLS((long) 8 * 60 * 60 * 1000);

    @Getter
    @Setter
    private long ttlMillis;

    JwtType(long ttlMillis) {
        this.ttlMillis = ttlMillis;
    }

}
