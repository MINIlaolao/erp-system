package com.kintexgroup.hkpsi.information.dao;

import com.kintexgroup.hkpsi.information.entity.SalesListCollect;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author LMAO
 * @since 2020/11/17 14:02
 */
@Mapper
public interface SalesListCollectDao extends tk.mybatis.mapper.common.Mapper<SalesListCollect> {

    /**
     * 批量插入
     *
     * @param list list
     * @return 是否成功
     */
    int batchInsert(@Param("list") List<SalesListCollect> list);
}