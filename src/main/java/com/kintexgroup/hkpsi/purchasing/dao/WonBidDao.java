package com.kintexgroup.hkpsi.purchasing.dao;

import com.github.pagehelper.Page;
import com.kintexgroup.hkpsi.purchasing.entity.WonBidEntity;
import com.kintexgroup.hkpsi.purchasing.model.wonbid.BidRecordVO;
import com.kintexgroup.hkpsi.purchasing.model.wonbid.WinBidPageDTO;
import com.kintexgroup.hkpsi.purchasing.model.wonbid.WonBidRecordVO;
import com.kintexgroup.hkpsi.purchasing.model.wonbid.WonBidVendorVO;
import org.apache.ibatis.annotations.Param;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author junhangs           pengli
 * @since 2020/9/8 23:48     2020/11/6
 */
@Resource
public interface WonBidDao {

    /**
     * 插入一条新的记录
     *
     * @param entity 实体
     * @return ID
     */
    boolean insertOne(WonBidEntity entity);

    /**
     * 根据 ID 获取一条记录
     *
     * @param id 中标 ID
     * @return 实体
     */
    WonBidRecordVO selectOneById(String id);

    /**
     * 获取所有中标记录和对应的投标记录
     *
     * @return 实体集合
     */
    List<WonBidVendorVO> selectMany();

    /**
     * 更新一条记录
     *
     * @param entity 实体
     * @return 更新是否成功
     */
    boolean updateOne(WonBidEntity entity);

    /**
     * 根据 ID 删除一条记录
     *
     * @param id 中标 ID
     * @return 删除是否成功
     */
    boolean deleteOneById(String id);

    /**
     * 根据中标单号查询投标记录
     *
     * @param id 中标 ID
     * @return 投标记录列表
     */
    List<BidRecordVO> selectOneByIdRecord(@Param("id") String id);

    /**
     * 根据不同的查询条件，查询一条或多条中标记录。查询条件为空时 查所有
     *
     * @param dto dto
     * @return 查询出来的结果集
     */
    Page<WonBidEntity> selectOneOrMany(WinBidPageDTO dto);

    /**
     * 根据中标单号更新中标里的发货状态
     *
     * @param wonNumber 中标单号
     * @return 是否成功
     */
    int updateShip(String wonNumber);

    /**
     * 检查中标批次是否发货
     *
     * @param wonNumber 中标号
     * @return 0 未发货 | 1 已发货
     */
    int checkIsShipped(String wonNumber);

    String selectWonBidCountByYearVendorId(@Param("year") String year, @Param("vendorId") String vendorId);
}
