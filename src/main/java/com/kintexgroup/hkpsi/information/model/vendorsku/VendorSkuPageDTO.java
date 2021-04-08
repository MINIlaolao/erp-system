package com.kintexgroup.hkpsi.information.model.vendorsku;

import lombok.Data;
import lombok.EqualsAndHashCode;
import com.kintexgroup.hkpsi.common.dto.BasePageDTO;

/**
 * @author pengli
 * @since 2020/11/24 5:17 下午
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class VendorSkuPageDTO extends BasePageDTO {
    
    /**
     * tag description|grade
     */
    private String tag;

    /**
     * vendor id
     */
    private Integer vendorId;

    /**
     * 对应的 kintex sku id
     */
    private String kSkuId;

    /**
     * 禁用
     */
    private Integer disabled;
}
