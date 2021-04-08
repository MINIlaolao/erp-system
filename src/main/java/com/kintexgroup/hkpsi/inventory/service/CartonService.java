package com.kintexgroup.hkpsi.inventory.service;

import com.kintexgroup.hkpsi.inventory.dto.AddCartonDTO;
import com.kintexgroup.hkpsi.inventory.model.CartonInfoVO;
import com.kintexgroup.hkpsi.inventory.model.CartonVO;

/**
 * @author LMAO
 * @since 2021/1/25 11:41
 */
public interface CartonService {

    CartonVO createNewCarton(AddCartonDTO dto);

    /**
     * 根据编号查询箱子信息
     *
     * @param args 参数
     * @return 打包信息
     */
    CartonInfoVO queryCartonInfo(String id, String date, String number);

    /**
     * 生成新的箱号
     * @return 新箱号
     */
    int generateNewCartonNumber();
    
}
