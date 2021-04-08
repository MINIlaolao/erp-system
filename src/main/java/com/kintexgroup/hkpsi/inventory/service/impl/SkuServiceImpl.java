package com.kintexgroup.hkpsi.inventory.service.impl;

import com.github.pagehelper.PageInfo;
import com.github.pagehelper.page.PageMethod;
import com.kintexgroup.hkpsi.common.constants.ResponseCode;
import com.kintexgroup.hkpsi.common.exception.BusinessException;
import com.kintexgroup.hkpsi.common.handler.CommonPageConverter;
import com.kintexgroup.hkpsi.common.util.ContextHolderUtil;
import com.kintexgroup.hkpsi.inventory.dao.SkuDao;
import com.kintexgroup.hkpsi.inventory.entity.Sku;
import com.kintexgroup.hkpsi.inventory.model.SkuDTO;
import com.kintexgroup.hkpsi.inventory.model.SkuPageDTO;
import com.kintexgroup.hkpsi.inventory.model.SkuVO;
import com.kintexgroup.hkpsi.inventory.service.SkuService;
import com.kintexgroup.hkpsi.inventory.util.SkuPojoConverter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;

/**
 * @author lmao
 * @since 2020/9/11 13:58
 */
@Service("SkuService")
@Transactional(rollbackFor = BusinessException.class)
public class SkuServiceImpl implements SkuService {

    @Resource
    private SkuDao skuDao;


    @Override
    public boolean delete(Integer id) {
        if (skuDao.deleteOne(id)) {
            return true;
        }
        throw new BusinessException(ResponseCode.DB_ID_NULL_ERROR);
    }

    @Override
    public PageInfo<SkuVO> selectAll(SkuPageDTO dto, int page, int size) {
        PageMethod.startPage(page, size, true).setOrderBy("es.`id` desc");
        var list = new ArrayList<SkuVO>();
        var records = skuDao.selectOne(dto);
        records.forEach(record -> list.add(SkuPojoConverter.fromSpu(record)));
        return CommonPageConverter.toPageInfo(records, list);
    }

    @Override
    public boolean updateOne(int id, SkuDTO dto) {
        int updater = ContextHolderUtil.getAuthedUserId();
        Sku entity = SkuPojoConverter.toUpdateEntity(id, updater, dto);
        skuDao.updateOne(entity);
        return true;
    }
}
