package com.kintexgroup.hkpsi.inventory.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import com.kintexgroup.hkpsi.common.dto.BasePageDTO;

/**
 * @author pengli
 * @since 2020/11/4 11:56 上午
 * <p>
 * <p>
 * 前端页面，销售下的预入库管理中的 搜索字段
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class StagePageDTO extends BasePageDTO {

    /**
     * 投标编号
     */
    private String bidId;

    /**
     * imei唯一标识码
     */
    private String imei;

    /**
     * 描述
     */
    private String text;

    /**
     * 供应商成色
     */
    private String grade;

    /**
     * 品牌
     */
    private String brand;

    /**
     * 型号名称
     */
    private String modelName;

    /**
     * 型号
     */
    private String model;

    /**
     * 颜色
     */
    private String color;

    /**
     * 容量
     */
    private Integer capacity;

}
