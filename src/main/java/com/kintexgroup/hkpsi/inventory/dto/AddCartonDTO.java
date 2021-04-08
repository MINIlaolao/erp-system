package com.kintexgroup.hkpsi.inventory.dto;

import lombok.Data;

import javax.validation.constraints.Size;

/**
 * @author LMAO
 * @since 2021/1/25 11:46
 */
@Data
public class AddCartonDTO {
    /**
     * 包装生成的编码
     */
    private Integer cartonNumber;

    /**
     * 包装内信息json格式
     */
    private String content;


    @Size(min = 1)
    private String[] ids;
}


