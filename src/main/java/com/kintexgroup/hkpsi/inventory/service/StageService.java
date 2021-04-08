package com.kintexgroup.hkpsi.inventory.service;

import com.github.pagehelper.PageInfo;
import com.kintexgroup.hkpsi.inventory.model.ImeiList;
import com.kintexgroup.hkpsi.inventory.model.StageDTO;
import com.kintexgroup.hkpsi.inventory.model.StagePageDTO;
import com.kintexgroup.hkpsi.inventory.model.StageVO;

import java.util.List;


/**
 * @author lmao             pengli
 * @since 2020/8/26 16:48   2020/10/10
 */
public interface StageService {


    /**
     * 根据 imei 删除单条信息
     *
     * @param imei imei
     * @return 成功 : true | 失败 : false
     */
    boolean deleteOne(String imei);

    /**
     * 根据 imei 批量删除
     *
     * @param imei imei
     * @return 成功 : true | 失败 : false
     */
    boolean deleteMany(List<String> imei);

    /**
     * 插入单条信息
     *
     * @param dto 插入的信息
     * @return 单条信息
     */
    StageVO insertOne(StageDTO dto);

    /**
     * 根据 id 更新单条信息
     *
     * @param dto 更新的信息
     * @param id  device_id
     * @return 单条更新过后record信息
     */
    StageVO updateOne(StageDTO dto, String id);

    /**
     * 根据 id 查询单条信息
     *
     * @param id id
     * @return 单条信息
     */
    StageVO selectOne(String id);

    /**
     * 查询所有预入库信息
     *
     * @return 所有预入库信息
     */
    List<StageVO> selectAll();

    /**
     * 查询一条或者多条
     *
     * @param dto  dto
     * @param page page
     * @param size size
     * @return 查询出来的结果集
     */
    PageInfo<StageVO> selectOneOrMany(StagePageDTO dto, int page, int size);

    /**
     * 批量向goods表中添加一条数据
     *
     * @param imeiList 带有 imei 的列表
     * @return 成功:true  / 失败：false
     */
    void insertMany(List<String> imeiList);

    /**
     * 扫码imei 后向goods表中添加一条数据
     *
     * @param imei imei
     * @return 返回信息(操作成功 / 失败)
     */
    String insert(String imei);


    /**
     * 批量添加预入库记录
     * desc 整理过,可以直接拿 tag 来用
     *
     * @param imeiLists 前端解析的 imei 文件
     */
    void batchInsertStageRecord(List<ImeiList> imeiLists);
}
