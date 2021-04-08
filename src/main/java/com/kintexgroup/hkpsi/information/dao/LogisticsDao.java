package com.kintexgroup.hkpsi.information.dao;

import com.kintexgroup.hkpsi.information.entity.Logistics;
import com.kintexgroup.hkpsi.information.model.KdnLogisticsIdName;
import com.kintexgroup.hkpsi.information.model.LogisticsMap;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author LMAO
 * @since 2020/12/8 13:44
 */
@Mapper
public interface LogisticsDao extends tk.mybatis.mapper.common.Mapper<Logistics> {
    /**
     * 根据中标单号获取物流信息
     *
     * @param wonNumber 中标号
     * @return 物流信息
     */
    LogisticsMap getKdnLogisticsByWonNumber(String wonNumber);

    /**
     * 根据物流公司编码查询物流公司名称
     *
     * @param shipperCode 物流公司编码
     * @return 物流公司
     */
    String selectShipperNameByCode(String shipperCode);

    /**
     * 查询KDN物流表id和物流公司名称
     *
     * @return list
     */
    List<KdnLogisticsIdName> getLogisticsIdCodeList();

    int updateByLogisticsCode(@Param("shipperCode") String shipperCode, @Param("rs") String rs);
}