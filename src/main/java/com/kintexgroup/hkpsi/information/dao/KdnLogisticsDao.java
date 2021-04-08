package com.kintexgroup.hkpsi.information.dao;

import com.kintexgroup.hkpsi.information.entity.KdnLogistics;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author LMAO
 * @since 2020/12/8 13:45
 */
@Mapper
public interface KdnLogisticsDao extends tk.mybatis.mapper.common.Mapper<KdnLogistics> {
    /**
     * 批量可选更新
     *
     * @param list list
     * @return 是否成功
     */
    int updateBatchSelective(List<KdnLogistics> list);

    /**
     * 批量插入
     *
     * @param list list
     * @return 结果集
     */
    int batchInsert(@Param("list") List<KdnLogistics> list);
}