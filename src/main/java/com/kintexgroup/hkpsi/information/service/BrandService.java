package com.kintexgroup.hkpsi.information.service;

import com.github.pagehelper.PageInfo;
import com.kintexgroup.hkpsi.information.model.BrandDTO;
import com.kintexgroup.hkpsi.information.model.BrandPageDTO;
import com.kintexgroup.hkpsi.information.model.BrandVO;

/**
 * @author pengli
 * @since 2020/9/8 6:27 下午
 */

public interface BrandService {

    /**
     * 添加一条spuBrand信息
     *
     * @param dto BrandDTO中的字段
     * @return 添加的spuBrand记录
     */
    BrandVO addOne(BrandDTO dto);

    /**
     * 根据ID更新一条spuBrand信息
     *
     * @param id  投标编号bidId
     * @param dto BrandDTO中的字段
     * @return 成功 true/失败 false
     */
    boolean updateOne(int id, BrandDTO dto);


    /**
     * 根据品牌id  联合brand表和spu表查询品牌名
     *
     * @param id 品牌id
     * @return 返回查数据出来的条数
     */
    Integer selectByName(int id);

    /**
     * 根据ID移除一条spuBrand信息
     *
     * @param id 品牌ID
     * @return 成功 true / 失败 false
     */
    String removeOne(int id);

    /**
     * 根据ID查询一条spuBrand信息
     *
     * @param id id
     * @return spuBrand的一条记录
     */
    BrandVO findOne(int id);

    /**
     * 根据品牌名字或者状态查询信息
     *
     * @param dto 传进来的dto
     * @return 查询到的brand结果集
     */
    PageInfo<BrandVO> selectOneOrMany(BrandPageDTO dto);

    /**
     * 根据品牌名称查询一条spuBrand信息
     *
     * @param name 品牌名称
     * @return spuBrand的一条记录
     */
    BrandVO findOneBrand(String name);

    /**
     * 查询所有spuBrand信息
     *
     * @param current  当前页面
     * @param pageSize 每页的数量
     * @return spuBrand的集合
     */
    BrandVO[] findMany(int current, int pageSize);

}
