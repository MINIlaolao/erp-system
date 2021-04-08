package com.kintexgroup.hkpsi.inventory.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import com.kintexgroup.hkpsi.common.dto.BasePageDTO;

import java.util.List;

/**
 * @author pengli
 * @since 2020/11/20 6:07 下午
 * <p>
 * 前端可以搜索的字段
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class InventoryPageDTO extends BasePageDTO {

    /**
     * 品牌
     */
    private List<String> brand;

    /**
     * 型号名称
     */
    private List<String> modelName;

    /**
     * 颜色
     */
    private List<String> color;

    /**
     * 容量
     */
    private List<String> capacity;

    /**
     * 型号
     */
    private List<String> model;

    /**
     * 运营商
     */
    private List<String> carrier;
}
