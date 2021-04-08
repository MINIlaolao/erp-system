package com.kintexgroup.hkpsi.information.service.impl;

import com.kintexgroup.hkpsi.common.constants.ResponseCode;
import com.kintexgroup.hkpsi.common.exception.BusinessException;
import com.kintexgroup.hkpsi.information.dao.SkuAttributeDao;
import com.kintexgroup.hkpsi.information.entity.SkuAttribute;
import com.kintexgroup.hkpsi.information.model.SkuAttributeDTO;
import com.kintexgroup.hkpsi.information.model.SkuAttributeVO;
import com.kintexgroup.hkpsi.information.service.SkuAttributeService;
import com.kintexgroup.hkpsi.information.util.SkuAttributePojoConverter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author pengli
 * @since 2020/9/15 7:08 下午
 */
@Service("SkuAttrKeyService")
@Transactional(rollbackFor = BusinessException.class)
public class SkuAttributeServiceImpl implements SkuAttributeService {

    @Resource
    private SkuAttributeDao skuAttributeDao;

    @Override
    public boolean create(Integer categoryId, Set<SkuAttributeDTO> list) {
        var entities = new HashSet<SkuAttribute>();
        for (var record : list) {
            var entity = SkuAttributePojoConverter.toCreateEntity(categoryId, record);
            entities.add(entity);
        }
        skuAttributeDao.insert(new ArrayList<>(entities));
        return true;
    }

    @Override
    public Boolean update(Integer categoryId, List<SkuAttributeDTO> dto) {
        var skuAttrKey = new ArrayList<SkuAttribute>(dto.size());
        for (var record : dto) {
            var entity = SkuAttributePojoConverter.toUpdateEntity(record);
            skuAttrKey.add(entity);
        }
        return skuAttributeDao.updateOne(categoryId, skuAttrKey);
    }

    @Override
    public boolean delete(List<Integer> ids) {
        if (skuAttributeDao.delete(ids)) {
            return true;
        }
        throw new BusinessException(ResponseCode.DB_ID_NULL_ERROR);
    }

    @Override
    public List<SkuAttributeVO> selectAll() {
        var records = skuAttributeDao.selectAll();
        var list = new ArrayList<SkuAttributeVO>(records.size());
        if (records.isEmpty()) {
            return list;
        }
        records.forEach(record -> list.add(SkuAttributePojoConverter.fromSku(record)));
        return list;
    }

    @Override
    public List<SkuAttributeVO> selectOne(Integer id) {
        var records = skuAttributeDao.selectOne(id);
        var list = new ArrayList<SkuAttributeVO>(records.size());
        records.forEach(record -> list.add(SkuAttributePojoConverter.fromSku(record)));
        return list;
    }
}
