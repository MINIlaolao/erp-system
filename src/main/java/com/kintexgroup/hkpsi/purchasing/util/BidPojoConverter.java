package com.kintexgroup.hkpsi.purchasing.util;

import com.kintexgroup.hkpsi.purchasing.entity.BidEntity;
import com.kintexgroup.hkpsi.purchasing.model.BidDTO;
import com.kintexgroup.hkpsi.purchasing.model.BidVO;


/**
 * @author junhangs, lmao
 * @since 2020/8/26 14:48
 */
public final class BidPojoConverter {

    /**
     * 将 从接口请求传入的参数类 转换为实体类
     *
     * @param dto 参数类
     * @return 实体类
     */
    private static BidEntity toEntity(BidDTO dto) {
        BidEntity entity = new BidEntity();
        entity.setId(dto.getId());
        entity.setDate(dto.getDate());
        entity.setVendor(dto.getVendor());
        entity.setProgram(dto.getProgram());
        entity.setFileName(dto.getFileName());
        entity.setCurrency(dto.getCurrency());
        entity.setExchangeRate(dto.getExchangeRate());
        return entity;
    }

    /**
     * 用于创建的实体类
     *
     * @param creator 创建人id
     * @param dto     参数类
     * @return 实体类
     */
    public static BidEntity toCreateEntity(BidDTO dto, int creator) {
        BidEntity entity = toEntity(dto);
        entity.setCreatedBy(creator);
        entity.setUpdatedBy(creator);
        return entity;
    }

    /**
     * 用于更新的实体类
     *
     * @param id      要更新的实体类 id
     * @param updater 更新人的 ID
     * @param dto     参数类
     * @return 实体类
     */
    public static BidEntity toUpdateEntity(String id, BidDTO dto, int updater) {
        BidEntity entity = toEntity(dto);
        entity.setUpdatedBy(updater);
        return entity;
    }

    /**
     * 将从数据库获取到的实体类 转为发送给前端的视图类
     *
     * @param bid 实体类
     * @return 视图类
     */
    public static BidVO fromBid(BidEntity bid) {
        BidVO vo = new BidVO();
        vo.setId(bid.getId());
        vo.setBidDate(bid.getDate());
        return vo;
    }
}
