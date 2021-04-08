package com.kintexgroup.hkpsi.purchasing.model.bidrecord;

import lombok.Data;
import lombok.EqualsAndHashCode;
import com.kintexgroup.hkpsi.common.dto.BasePageDTO;

/**
 * @author pengli
 * @since 2020/11/5 6:39 下午
 * <p>
 * 前端页面投标管理的投标详情中，搜索条件的字段
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class BidRecordPageDTO extends BasePageDTO {

    /**
     * 投标 ID
     */
    private String bidId;

    /**
     * 中标 ID
     */
    private String wonId;

    /**
     * 品牌
     */
    private String brand;

    /**
     * 型号名称
     */
    private String modelName;

    /**
     * 颜色
     */
    private String color;

    /**
     * 容量
     */
    private Integer capacity;

}
