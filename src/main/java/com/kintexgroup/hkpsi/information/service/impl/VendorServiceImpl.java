package com.kintexgroup.hkpsi.information.service.impl;

import com.github.pagehelper.PageInfo;
import com.github.pagehelper.page.PageMethod;
import com.kintexgroup.hkpsi.common.constants.ResponseCode;
import com.kintexgroup.hkpsi.common.exception.BusinessException;
import com.kintexgroup.hkpsi.common.handler.CommonPageConverter;
import com.kintexgroup.hkpsi.common.util.ContextHolderUtil;
import com.kintexgroup.hkpsi.common.util.ExceptionUtil;
import com.kintexgroup.hkpsi.information.dao.VendorDao;
import com.kintexgroup.hkpsi.information.entity.Vendor;
import com.kintexgroup.hkpsi.information.model.VendorDTO;
import com.kintexgroup.hkpsi.information.model.VendorPageDTO;
import com.kintexgroup.hkpsi.information.model.VendorVO;
import com.kintexgroup.hkpsi.information.service.VendorService;
import com.kintexgroup.hkpsi.information.util.VendorPojoConverter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;


/**
 * @author junhangs
 * @author pengli
 * @since 2020/8/23 21:50
 * @since 2020/10/29
 */

@Service("VendorService")
@Transactional(rollbackFor = BusinessException.class)
public class VendorServiceImpl implements VendorService {

    private final VendorDao vendorDao;

    public VendorServiceImpl(VendorDao vendorDao) {
        this.vendorDao = vendorDao;
    }

    @Override
    public VendorVO create(VendorDTO dto) {
        Vendor vendor = VendorPojoConverter
            .toCreateEntity(ContextHolderUtil.getAuthedUserId(), dto);
        if (vendorDao.check(vendor.getCode(), vendor.getName())) {
            throw new BusinessException(ResponseCode.DB_INSERT_DUPLICATE_ERROR);
        }
        if (vendorDao.insertOne(vendor)) {
            return selectOne(vendor.getId());
        }
        return null;
    }

    @Override
    public boolean update(Integer id, VendorDTO dto) {
        Vendor vendor = VendorPojoConverter
            .toUpdateEntity(id, ContextHolderUtil.getAuthedUserId(), dto);
        //vCode:数据库中这条记录的原本code值    gCode:前端传来的code值
        String vCode = vendorDao.selectOne(id).getCode();
        String gCode = vendor.getCode();
        //判断传进来的code和数据库这条记录原本的code是否相等，并且判断是否传来disabled的值进来
        if (vCode.equals(gCode) && (vendor.getDisabled() != 0) && (vendor.getDisabled() != 1)) {
            return true;
        }
        if (vendorDao.checkCode(vendor.getCode()) && !vCode.equals(gCode)) {
            throw new BusinessException(ResponseCode.DB_UPDATE_DUPLICATE_ERROR);
        }
        return ExceptionUtil.isUpdateSuccess(vendorDao.updateOne(vendor));
    }


    @Override
    public boolean delete(Integer id) {
        if (vendorDao.selectByVendorId(id) > 0) {
            throw new BusinessException(ResponseCode.BIZ_HAVE_VENDOR_SPU);
        } else if (vendorDao.deleteOne(id)) {
            return true;
        } else {
            throw new BusinessException(ResponseCode.DB_ID_NULL_ERROR);
        }
    }

    @Override
    public VendorVO selectOne(Integer id) {
        if (vendorDao.selectOne(id) == null) {
            throw new BusinessException(ResponseCode.DB_SELECT_ERROR);
        }
        return VendorPojoConverter.fromVendor(vendorDao.selectOne(id));
    }

    @Override
    public PageInfo<VendorVO> selectOneOrManyVendor(VendorPageDTO dto, int current, int pageSize) {
        PageMethod.startPage(current, pageSize, true).setOrderBy("vendor_code asc");
        var records = vendorDao.selectOneOrManyVendor(dto);
        //定义列表用于存放每条记录
        var list = new ArrayList<VendorVO>(records.size());

        //判断records是否为空  如果为空  返回空列表
        if (records.isEmpty()) {
            return new PageInfo<>();
        }

        //records 不为空 用for遍历  把每一条信息 添加到list中 最后返回list
        for (var record : records) {
            list.add(VendorPojoConverter.fromVendor(record));
        }
        return CommonPageConverter.toPageInfo(records, list);
    }
}
