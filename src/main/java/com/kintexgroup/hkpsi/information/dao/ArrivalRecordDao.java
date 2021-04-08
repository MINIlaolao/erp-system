package com.kintexgroup.hkpsi.information.dao;

import com.kintexgroup.hkpsi.information.entity.ArrivalRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author LMAO
 * @since 2020/12/11 10:14
 */
@Mapper
public interface ArrivalRecordDao extends tk.mybatis.mapper.common.Mapper<ArrivalRecord> {
    int updateBatchSelective(List<ArrivalRecord> list);

    int batchInsert(@Param("list") List<ArrivalRecord> list);
}