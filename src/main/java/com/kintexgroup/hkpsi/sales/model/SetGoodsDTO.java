package com.kintexgroup.hkpsi.sales.model;

import lombok.Data;

/**
 * @author lmao
 * @since 2020/9/27 18:08
 */
@Data
public class SetGoodsDTO {

    private Integer deviceId;

    private String oem;

    private String imei1;

    private String modelName;

    private String modelNumber;

    private String color;

    private Integer inventory;

    private Boolean isSimLocked;

    private Boolean isiCloudLocked;

    private Boolean isDeleted;

    private Integer quantity;

    private Double price;
}
