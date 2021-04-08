package com.kintexgroup.hkpsi.information.service.impl;

import com.kintexgroup.hkpsi.information.dao.SalesListCollectDao;
import com.kintexgroup.hkpsi.information.entity.SalesListCollect;
import com.kintexgroup.hkpsi.information.service.SalesListCollectService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author LMAO
 * @since 2020/11/17 14:02
 */
@Service
public class SalesListCollectServiceImpl implements SalesListCollectService {

    @Resource
    private SalesListCollectDao salesListCollectDao;

    @Override
    public int batchInsert(List<SalesListCollect> list) {
        return salesListCollectDao.batchInsert(list);
    }

}
