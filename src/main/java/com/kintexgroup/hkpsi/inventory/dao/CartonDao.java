package com.kintexgroup.hkpsi.inventory.dao;

import com.kintexgroup.hkpsi.inventory.entity.CartonEntity;
import tk.mybatis.mapper.common.Mapper;

public interface CartonDao extends Mapper<CartonEntity> {

    boolean checkCartonNumberExistedById(String cartonNumber);

    CartonEntity selectOneByNo(String cartonNumber);

    CartonEntity selectOneById(String id);

    Integer selectLastOneOfType();

    boolean checkCartonIsExist(String id, String date, String number);

    String selectCartonById(String id);
}