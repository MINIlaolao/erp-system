package com.kintexgroup.hkpsi.information.service;

import com.kintexgroup.hkpsi.information.model.CarrierDTO;
import com.kintexgroup.hkpsi.information.model.CarrierVO;

import java.util.List;

/**
 * @author gradylo
 * @since 2020/8/23 14:42
 */

public interface CarrierService {

    /**
     * 添加一条运营商信息
     *
     * @param dto 数据类
     * @return 视图类
     */
    CarrierVO saveOne(CarrierDTO dto);

    /**
     * 根据Id更新一条运营商信息
     *
     * @param id  运营商id
     * @param dto 数据类
     * @return 视图类
     */
    boolean changeOne(Integer id, CarrierDTO dto);

    /**
     * 根据id删除一条运营商信息
     *
     * @param id 运营商id
     * @return 是否删除成功
     */
    boolean deleteOne(Integer id);

    /**
     * 根据ID查询一条运营商信息
     *
     * @param id 运营商id
     * @return 视图类
     */
    CarrierVO getOne(Integer id);

    /**
     * 查询所有的运营商信息
     *
     * @return 视图类集合
     */
    List<CarrierVO> getMany();
}
