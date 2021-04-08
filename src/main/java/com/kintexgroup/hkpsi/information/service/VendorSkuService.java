package com.kintexgroup.hkpsi.information.service;

import com.github.pagehelper.PageInfo;
import com.kintexgroup.hkpsi.information.entity.VendorSkuEntity;
import com.kintexgroup.hkpsi.information.model.VendorSkuVO;
import com.kintexgroup.hkpsi.information.model.vendorsku.VendorSkuPageDTO;
import com.kintexgroup.hkpsi.purchasing.model.bidrecord.BidRecordDTO;

import java.util.List;
import java.util.Map;

/**
 * @author junhangs
 * @author lmao
 * @since 2020/9/11 11:26
 */
public interface VendorSkuService {


    /**
     * @param id vendor id
     * @param list list
     */
    List<VendorSkuEntity> obtainNewVendorSkuList(int id, List<BidRecordDTO> list);

    /**
     * 删除一条
     *
     * @param id id
     * @return 是否删除
     */
    boolean deleteOneById(String id);


    /**
     * 查询一条或多条记录
     *
     * @param dto      查询 dto
     * @return 分页结果
     */
    PageInfo<VendorSkuVO> list(VendorSkuPageDTO dto);

    /**
     * 获取 id -> tag 的 map
     * @param tags tags
     */
    Map<String, String> obtainMapOfIdTag(List<String> tags);
}
