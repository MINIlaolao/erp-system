package com.kintexgroup.hkpsi.information.dao;

import com.kintexgroup.hkpsi.information.entity.SalesList;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author LMAO
 * @since 2020/11/17 13:56
 */
@Mapper
public interface SalesListDao extends tk.mybatis.mapper.common.Mapper<SalesList> {
    /**
     * 批量添加
     *
     * @param list list
     * @return 是否成功
     */
    int batchInsert(@Param("list") List<SalesList> list);

}