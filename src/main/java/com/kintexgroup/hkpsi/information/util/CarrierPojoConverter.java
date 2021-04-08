package com.kintexgroup.hkpsi.information.util;

import com.kintexgroup.hkpsi.information.entity.Carrier;
import com.kintexgroup.hkpsi.information.model.CarrierVO;
import com.kintexgroup.hkpsi.information.model.CarrierDTO;

/**
 * @author developer04
 */
public class CarrierPojoConverter {

    private CarrierPojoConverter() {
    }

    /**
     * 将 从接口请求传入的参数类 转换为 实体类
     *
     * @param dto 参数类
     * @return 实体类
     */
    private static Carrier toEntity(CarrierDTO dto) {
        Carrier entity = new Carrier();
        entity.setName(dto.getName());
        entity.setRegion(dto.getRegion());
        entity.setDisabled(dto.getDisabled());
        return entity;
    }

    /**
     * 用于创建的实体类
     *
     * @param creator 创建人id
     * @param dto     参数类
     * @return 实体类
     */
    public static Carrier toCreateEntity(Integer creator, CarrierDTO dto) {
        Carrier entity = toEntity(dto);
        entity.setCreatedBy(creator);
        entity.setUpdatedBy(creator);
        return entity;
    }

    /**
     * 用于更新的实体类
     *
     * @param id      要更新的实体类 id
     * @param updater 更新人的id
     * @param dto     参数类
     * @return 实体类
     */
    public static Carrier toUpdateEntity(Integer id, Integer updater, CarrierDTO dto) {
        Carrier entity = toEntity(dto);
        entity.setId(id);
        entity.setUpdatedBy(updater);
        return entity;
    }

    /**
     * 将 从数据库获取到的实体类 转为发送给前端的 视图类
     *
     * @param carrier 实体类
     * @return 视图类
     */
    public static CarrierVO fromEntity(Carrier carrier) {
        CarrierVO vo = new CarrierVO();
        vo.setId(carrier.getId());
        vo.setName(carrier.getName());
        vo.setRegion(carrier.getRegion());
        vo.setEditable(carrier.getEditable());
        vo.setDisabled(carrier.getDisabled());
        return vo;
    }
}
