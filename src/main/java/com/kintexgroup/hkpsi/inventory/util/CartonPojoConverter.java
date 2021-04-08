package com.kintexgroup.hkpsi.inventory.util;

import com.kintexgroup.hkpsi.inventory.dto.AddCartonDTO;
import com.kintexgroup.hkpsi.inventory.entity.CartonEntity;

public class CartonPojoConverter {
    private static CartonEntity toEntity(AddCartonDTO dto) {
        var entity = new CartonEntity();
        entity.setCartonNumber(dto.getCartonNumber());
        entity.setContent(dto.getContent());
        return entity;
    }
    public static CartonEntity toCreateEntity(AddCartonDTO dto, int creator) {
        var entity = toEntity(dto);
        entity.setCreatedBy(creator);
        entity.setUpdatedBy(creator);
        return entity;
    }
}
