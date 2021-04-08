package com.kintexgroup.hkpsi.information.service;

import com.kintexgroup.hkpsi.information.entity.VendorProgramEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VendorProgramService {
    /**
     * 获取供应商项目列表
     * @return VendorProgramEntity[]
     */
    List<VendorProgramEntity> list();
}
