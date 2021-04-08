package com.kintexgroup.hkpsi.sales.model;

import lombok.Data;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author lmao
 * @since 2020/9/30 14:52
 */
@Data
public class InvoiceRecord {

    private String skuId;
    @NotNull
    @Min(0)
    private int quantity;

    @NotNull
    @DecimalMin("0.0001")
    private Double price;

    private String grade;

    private List<String> imeis;
}
