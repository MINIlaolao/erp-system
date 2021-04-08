package com.kintexgroup.hkpsi.purchasing.util;

import com.kintexgroup.hkpsi.purchasing.entity.WonBidEntity;
import com.kintexgroup.hkpsi.purchasing.model.wonbid.WonBidDTO;
import com.kintexgroup.hkpsi.purchasing.model.wonbid.WonBidVO;

/**
 * @author lmao
 * @since 2020/9/9 09:39
 */
public final class WonBidPojoConverter {

    private WonBidPojoConverter() {
    }

    /**
     * 将 从接口请求传入的参数类 转换为实体类
     *
     * @param dto 参数类
     * @return 实体类
     */
    private static WonBidEntity toEntity(WonBidDTO dto) {
        WonBidEntity entity = new WonBidEntity();
        entity.setNumber(dto.getNumber());
        entity.setDate(dto.getDate());
        entity.setAmount(dto.getAmount());
        entity.setBidId(dto.getBidId());
        return entity;
    }

    /**
     * 用于创建的实体类
     *
     * @param dto     参数类
     * @param creator 创建人id
     * @return 实体类
     */
    public static WonBidEntity toCreateEntity(WonBidDTO dto, int creator) {
        WonBidEntity entity = toEntity(dto);
        entity.setExchangeRate(dto.getExchangeRate());
        entity.setCreatedBy(creator);
        entity.setUpdatedBy(creator);
        return entity;
    }

    /**
     * 用于更新的实体类
     *
     * @param id      要更新的实体类 id
     * @param dto     参数类
     * @param updater 更新人的 ID
     * @return 实体类
     */
    public static WonBidEntity toUpdateEntity(String id, WonBidDTO dto, int updater) {
        WonBidEntity entity = toEntity(dto);
        entity.setId(id);
        entity.setUpdatedBy(updater);
        return entity;
    }

    /**
     * 将从数据库获取到的实体类 转为发送给前端的视图类
     *
     * @param entity 实体类
     * @return 视图类
     */
    public static WonBidVO fromEntity(WonBidEntity entity) {
        WonBidVO vo = new WonBidVO();
        vo.setNumber(entity.getNumber());
        vo.setDate(entity.getDate());
        vo.setAmount(entity.getAmount());
        vo.setBidId(entity.getBidId());
        vo.setVendor(entity.getVendor());
        vo.setExchangeRate(entity.getExchangeRate());
        vo.setIsShipped(entity.getIsShipped());
        return vo;
    }
}
