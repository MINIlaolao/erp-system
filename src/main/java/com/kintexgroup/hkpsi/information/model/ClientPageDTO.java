package com.kintexgroup.hkpsi.information.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import com.kintexgroup.hkpsi.common.dto.BasePageDTO;

/**
 * @author pengli
 * @since 2020/11/24 5:09 下午
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ClientPageDTO extends BasePageDTO {
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
