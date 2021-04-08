package com.kintexgroup.hkpsi.common.dto;

import lombok.Data;

/**
 * @author pengli
 * @since 2020/11/24 2:50 下午
 */
@Data
public class BasePageDTO {

    /**
     * 当前页数
     */
    private Integer current;

    /**
     * 每页数量
     */
    private Integer pageSize;

}
