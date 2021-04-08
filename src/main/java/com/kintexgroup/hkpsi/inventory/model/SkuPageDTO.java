package com.kintexgroup.hkpsi.inventory.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import com.kintexgroup.hkpsi.common.dto.BasePageDTO;

/**
 * 根据不同的查询条件的 bean
 *
 * @author pengli
 * @since 2020/10/26 5:23 下午
 * <p>
 * 前端页面，设置下的sku中搜索字段
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SkuPageDTO extends BasePageDTO {

    private Integer id;

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

    /**
     * 等级
     */
    private String grade;

    /**
     * 供应商
     */
    private String carrier;

    /**
     * 是否可用
     */
    private Integer disabled;
}
