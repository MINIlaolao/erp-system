package com.kintexgroup.hkpsi.inventory.service.impl;

import com.kintexgroup.hkpsi.common.constants.ResponseCode;
import com.kintexgroup.hkpsi.common.exception.BusinessException;
import com.kintexgroup.hkpsi.common.util.JsonUtil;
import com.kintexgroup.hkpsi.inventory.dao.CartonDao;
import com.kintexgroup.hkpsi.inventory.dao.InventoryGoodsDao;
import com.kintexgroup.hkpsi.inventory.dto.AddCartonDTO;
import com.kintexgroup.hkpsi.inventory.model.CartonInfoVO;
import com.kintexgroup.hkpsi.inventory.model.CartonVO;
import com.kintexgroup.hkpsi.inventory.service.CartonService;
import com.kintexgroup.hkpsi.inventory.util.CartonPojoConverter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

@Service("CartonService")
@Transactional(rollbackFor = BusinessException.class)
public class CartonServiceImpl implements CartonService {

    @Resource
    private CartonDao cartonDao;

    @Resource
    private InventoryGoodsDao inventoryGoodsDao;

    @Override
    public CartonVO createNewCarton(AddCartonDTO dto) {
//        var user = ContextHolderUtil.getAuthedUserId();
        var entity = CartonPojoConverter.toCreateEntity(dto, 0);
        try {
            cartonDao.insertSelective(entity);
        } catch (Exception e) {
            var no = generateNewCartonNumber();
            entity.setCartonNumber(no);
            cartonDao.insertSelective(entity);
        }
        inventoryGoodsDao.batchUpdateCartonId(dto.getIds(), entity.getId());

        var cartonId = entity.getId();

        entity = cartonDao.selectByPrimaryKey(cartonId);

        var createdAt = entity.getCreatedAt();

        return new CartonVO(cartonId, entity.getCartonNumber(), String.valueOf(LocalDateTime.parse(createdAt).toInstant(ZoneOffset.of("+8")).toEpochMilli()));
    }

    @Override
    public CartonInfoVO queryCartonInfo(String id, String date, String number) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime localDate = LocalDateTime.ofEpochSecond(Long.parseLong(date) / 1000, 0, ZoneOffset.ofHours(8));
        var timeStr = localDate.format(formatter);
        boolean b = cartonDao.checkCartonIsExist(id, timeStr, number);


        if (!b) {
            throw new BusinessException(ResponseCode.BIZ_NO_MATCH_CARTON);
        }

        var lists = inventoryGoodsDao.selectListByCarton(id);

        return new CartonInfoVO(number, timeStr, JsonUtil.parse(cartonDao.selectCartonById(id)), lists);
    }

    @Override
    public int generateNewCartonNumber() {
        var cartonNumber = cartonDao.selectLastOneOfType();
        if (cartonNumber == null) {
            return 10000001;
        }
        return cartonNumber + 1;
    }
}
