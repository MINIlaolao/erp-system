package com.kintexgroup.hkpsi.information.service.impl;

import com.kintexgroup.hkpsi.common.exception.BusinessException;
import com.kintexgroup.hkpsi.information.dao.VendorProgramDao;
import com.kintexgroup.hkpsi.information.entity.VendorProgramEntity;
import com.kintexgroup.hkpsi.information.model.VendorProgramBaseVO;
import com.kintexgroup.hkpsi.information.service.VendorProgramService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service("VendorProgramService")
@Transactional(rollbackFor = BusinessException.class)
public class VendorProgramServiceImpl implements VendorProgramService {
    
    @Resource
    private VendorProgramDao vendorProgramDao;
    
    @Override
    public List<VendorProgramEntity> list() {
        return vendorProgramDao.selectAll();
    }
}
