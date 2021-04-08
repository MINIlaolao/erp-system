package com.kintexgroup.hkpsi.information.service.impl;

import com.github.pagehelper.PageInfo;
import com.github.pagehelper.page.PageMethod;
import com.kintexgroup.hkpsi.common.constants.ResponseCode;
import com.kintexgroup.hkpsi.common.exception.BusinessException;
import com.kintexgroup.hkpsi.common.handler.CommonPageConverter;
import com.kintexgroup.hkpsi.common.util.ContextHolderUtil;
import com.kintexgroup.hkpsi.information.dao.BrandDao;
import com.kintexgroup.hkpsi.information.entity.Brand;
import com.kintexgroup.hkpsi.information.model.BrandDTO;
import com.kintexgroup.hkpsi.information.model.BrandPageDTO;
import com.kintexgroup.hkpsi.information.model.BrandVO;
import com.kintexgroup.hkpsi.information.service.BrandService;
import com.kintexgroup.hkpsi.information.util.BrandPojoConverter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;

/**
 * @author pengli
 * @since 2020/9/8 6:27 下午
 */
@Service("BrandService")
@Transactional(rollbackFor = BusinessException.class)
public class BrandServiceImpl implements BrandService {

    @Resource
    private BrandDao brandDao;

    @Override
    public BrandVO addOne(BrandDTO brand) {

        // 为了防止重复而导致的主键自增，会先检查需要唯一的值是否已经存在
        if (brandDao.nameIsAlreadyExists(brand.getName(), 0)) {
            throw new BusinessException(ResponseCode.DB_INSERT_DUPLICATE_ERROR);
        }

        int creator = ContextHolderUtil.getAuthedUserId();

        // 转为插入实体类
        Brand newRecord = BrandPojoConverter.toCreateEntity(creator, brand);

        brandDao.insertOne(newRecord);

        return findOne(newRecord.getId());
    }

    @Override
    public boolean updateOne(int id, BrandDTO dto) {

        var inDatabaseRecord = brandDao.selectOne(id, false);
        if (inDatabaseRecord == null) {
            throw new BusinessException(ResponseCode.DB_ID_NULL_ERROR);
        } else {
            int updater = ContextHolderUtil.getAuthedUserId();
            Brand entity = BrandPojoConverter.toUpdateEntity(id, updater, dto);
            brandDao.updateOne(entity);
            return true;
        }
    }

    @Override
    public Integer selectByName(int id) {
        return brandDao.selectByName(id);
    }

    @Override
    public String removeOne(int id) {
        int removeBy = ContextHolderUtil.getAuthedUserId();
        if (selectByName(id) > 0) {
            throw new BusinessException(ResponseCode.BIZ_HAVE_BRAND_SPU);
        } else if (brandDao.removeOne(id, removeBy)) {
            return "删除成功";
        } else {
            throw new BusinessException(ResponseCode.DB_ID_NULL_ERROR);
        }
    }

    @Override
    public BrandVO findOne(int id) {

        var inDatabaseRecord = brandDao.selectOne(id, true);

        if (inDatabaseRecord == null) {
            throw new BusinessException(ResponseCode.REQUEST_PARAM_INVALID);
        }

        return BrandPojoConverter.fromEntity(inDatabaseRecord);
    }

    @Override
    public PageInfo<BrandVO> selectOneOrMany(BrandPageDTO dto) {
        PageMethod.startPage(dto.getCurrent(), dto.getPageSize(), true);
        var list = new ArrayList<BrandVO>();
        var records = brandDao.selectOneOrMany(dto);
        records.forEach(record -> list.add(BrandPojoConverter.fromEntity(record)));
        return CommonPageConverter.toPageInfo(records, list);
    }

    @Override
    public BrandVO findOneBrand(String name) {
        var inDatabaseRecord = brandDao.selectOneBrand(name, true);

        if (inDatabaseRecord == null) {
            throw new BusinessException(ResponseCode.REQUEST_PARAM_INVALID);
        }

        return BrandPojoConverter.fromEntity(inDatabaseRecord);
    }

    @Override
    public BrandVO[] findMany(int page, int size) {

        var records = brandDao.selectAll(true);

        return records.stream().map(BrandPojoConverter::fromEntity).toArray(BrandVO[]::new);
    }


}
