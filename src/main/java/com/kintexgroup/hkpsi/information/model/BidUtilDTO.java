package com.kintexgroup.hkpsi.information.model;

import lombok.Data;

import javax.annotation.Nullable;
import java.util.List;

/**
 * @author LMAO
 * @since 2020/12/1 15:33
 */
@Data
public class BidUtilDTO {
    private Integer vendor;

    @Nullable
    private Integer program;
    
    private List<String> labels;

    private List<ConditionsAboutQueryTheBidPriceDTO> conditions;
}


