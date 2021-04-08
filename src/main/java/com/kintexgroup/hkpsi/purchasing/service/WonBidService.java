package com.kintexgroup.hkpsi.purchasing.service;

import com.github.pagehelper.PageInfo;
import com.kintexgroup.hkpsi.information.model.KdnLogisticsIdName;
import com.kintexgroup.hkpsi.information.model.LogisticsResultVO;
import com.kintexgroup.hkpsi.purchasing.model.ShipDTO;
import com.kintexgroup.hkpsi.purchasing.model.wonbid.*;

import java.text.ParseException;
import java.util.List;

/**
 * @author lmao
 * @since 2020/9/9 09:51
 */
public interface WonBidService {

    /**
     * 中标
     * 插入一条新的记录
     *
     * @param dto dto
     * @return ID
     * @throws ParseException 时间转换异常
     */
    void create(WonBidDTO dto) throws ParseException;

    /**
     * 根据 ID 获取一条记录
     *
     * @param id 中标 ID
     * @return 视图
     */
    WonBidRecordVO selectOneById(String id);

    /**
     * 获取所有记录
     *
     * @return 视图集合
     */
    List<WonBidVendorVO> selectMany();

    /**
     * 更新一条记录
     *
     * @param dto dto
     * @param id  won_id
     * @return 更新是否成功
     */
    boolean updateOne(String id, WonBidDTO dto);

    /**
     * 根据 ID 删除一条记录
     *
     * @param id 中标 ID
     * @return 删除是否成功
     */
    boolean deleteOneById(String id);

    /**
     * 根据不同的查询条件，查询一条或多条中标信息
     *
     * @param dto  dto
     * @param page page
     * @param size size
     * @return 查询出来的结果集
     */
    PageInfo<WonBidVO> selectOneOrMany(WinBidPageDTO dto, int page, int size);

    /**
     * KDN物流查询
     *
     * @param wonNumber 中标单号
     * @return 物流结果
     * @throws Exception 解密错误
     */
    LogisticsResultVO getLogisticsByWonNumber(String wonNumber) throws Exception;

    /**
     * 标记该中标单发货
     *
     * @param dto 物流单号和物流公司代码
     */
    void setShipperCode(ShipDTO dto);

    /**
     * 查询KDN的物流代码
     *
     * @return 物流公司名称和代码
     */
    List<KdnLogisticsIdName> getLogisticsList();

    /**
     * 生成中标号
     * <p>
     * T055220NHI
     * T0552 - 第552单
     * 20 - 2020年
     * N - November
     * H1 - Vendor Code
     *
     * @param yearAndMonth 年月
     * @param vendorId     供应商id
     * @return 中标号
     */
    String getWonBidNumber(String yearAndMonth, String vendorId);
}
