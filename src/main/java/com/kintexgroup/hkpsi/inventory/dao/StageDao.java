package com.kintexgroup.hkpsi.inventory.dao;

import com.github.pagehelper.Page;
import com.kintexgroup.hkpsi.inventory.entity.Stage;
import com.kintexgroup.hkpsi.inventory.model.StagePageDTO;
import com.kintexgroup.hkpsi.inventory.model.StageVO;
import com.kintexgroup.hkpsi.sales.entity.Goods;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lmao             pengli
 * @since 2020/8/26 16:56   2020/10/10
 */
@Repository
public interface StageDao {

    /**
     * 根据 imei 删除单条信息
     *
     * @param imei imei
     * @return 成功 : true | 失败 : false
     */
    boolean deleteOne(@Param("imei") String imei);

    /**
     * 根据 imei 批量删除
     *
     * @param imei imei
     * @return 成功 : true | 失败 : false
     */
    boolean deleteMany(List<String> imei);

    /**
     * 插入单条虚拟库存信息
     *
     * @param stage 插入的信息
     * @return 单条虚拟库存信息
     */
    boolean insertOne(Stage stage);

    /**
     * 根据 id 更新单条信息
     *
     * @param stage 更新的信息
     * @return 单条更新过后虚拟库存信息
     */
    boolean updateOne(Stage stage);

    /**
     * 根据 id 查询单条信息
     *
     * @param id device_id
     * @return 单条虚拟库存信息
     */
    StageVO selectOne(String id);

    /**
     * 根据不同的查询条件查询多条或一条库存信息
     *
     * @param dto dto
     * @return 查询出来的结果集
     */
    Page<Stage> selectOneOrMany(StagePageDTO dto);


    /**
     * 判断插入或更新时数据库中的 imei  是否重复
     *
     * @param imei 唯一标识码
     * @return false : 查询到重复的值 || true  : 没有查询到重复的值
     */
    boolean checkDuplicate(@Param("imei") String imei);

    /**
     * 批量插入记录
     *
     * @param list 插入的 list 集
     * @return 成功：true /失败：false
     */
    boolean insertMany(@Param("list") List<Goods> list);

    /**
     * 向库存表中添加数据
     *
     * @param skuId skuId
     * @return 成功：true /失败：false
     */
    boolean insertInventory(String skuId);

    /**
     * 修改库存表中的库存数
     *
     * @param skuId skuId
     * @return 成功：true /失败：false
     */
    boolean updateInventory(String skuId);

    /**
     * 获取skuId
     * 根据imei向goods表中查询skuid
     *
     * @param imei1 imei1
     * @return skuId
     */
    String selectSkuId(String imei1);

    /**
     * 检查库存表中是否存在skuId
     *
     * @param skuId skuId
     * @return 数量=1 代表存在
     */
    int check(String skuId);

    /**
     * 插入一条信息
     *
     * @param imei imei
     * @return 成功：true / 失败：false
     */
    boolean insert(String imei);

    /**
     * 查询虚拟仓库中所有记录
     *
     * @return 所有记录
     */
    List<Stage> selectAll();

    /**
     * 根据imei查询库存信息
     *
     * @param imeis imei集合
     * @return
     */
    List<Stage> selectById(List<String> imeis);

    /**
     * 批量添加
     *
     * @param stages stages
     * @return 是否成功
     */
    boolean batchInsertStageRecord(@Param("stages") ArrayList<Stage> stages);
}
