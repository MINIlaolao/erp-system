package com.kintexgroup.hkpsi.purchasing.model.wonbid;

import lombok.Data;
import lombok.EqualsAndHashCode;
import com.kintexgroup.hkpsi.common.dto.BasePageDTO;

import java.util.List;

/**
 * @author pengli
 * @since 2020/11/6 5:44 下午
 * <p>
 * 前端页面，采购下的中标列表中搜索的字段
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class WinBidPageDTO extends BasePageDTO {

    /**
     * 中标号 展示用
     */
    private String number;

    /**
     * 中标日期
     */
    private List<String> date;

    /**
     * 中标金额
     */
    private Double amount;


    /**
     * 供应商
     */
    private String vendor;

}
