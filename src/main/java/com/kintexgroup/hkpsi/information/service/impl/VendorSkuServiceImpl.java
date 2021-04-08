package com.kintexgroup.hkpsi.information.service.impl;

import com.github.pagehelper.PageInfo;
import com.github.pagehelper.page.PageMethod;
import com.kintexgroup.hkpsi.common.exception.BusinessException;
import com.kintexgroup.hkpsi.common.handler.CommonPageConverter;
import com.kintexgroup.hkpsi.common.util.ContextHolderUtil;
import com.kintexgroup.hkpsi.information.dao.VendorSkuDao;
import com.kintexgroup.hkpsi.information.entity.VendorSkuEntity;
import com.kintexgroup.hkpsi.information.model.vendorsku.IdTagPair;
import com.kintexgroup.hkpsi.information.model.vendorsku.VendorSkuPageDTO;
import com.kintexgroup.hkpsi.information.model.VendorSkuVO;
import com.kintexgroup.hkpsi.information.service.VendorSkuService;
import com.kintexgroup.hkpsi.information.util.VendorSkuPojoConverter;
import com.kintexgroup.hkpsi.purchasing.model.bidrecord.BidRecordDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author junhangs
 * @author lmao
 * @since 2020/9/11 11:26
 */
@Service("VendorSkuService")
@Transactional(rollbackFor = BusinessException.class)
public class VendorSkuServiceImpl implements VendorSkuService {

    @Resource
    private VendorSkuDao vendorSkuDao;

    @Override
    public List<VendorSkuEntity> obtainNewVendorSkuList(int id, List<BidRecordDTO> list) {
        // 转为 tag list
//        var tagsFromArg = list.stream()
//            .map(BidRecordDTO::getTag)
//            .distinct()
//            .collect(Collectors.toList());
//
//        // 拿着本次的 tag list 去查找数据库
//        var existingTags = vendorSkuDao.queryForExistingTags(tagsFromArg);
//
//        // 过滤出没查到的 tag 的 dto
//        var except = list.stream()
//            .filter(item -> !existingTags.contains(item.getTag()))
//            .collect(Collectors.toList());
//        
//        if (except.isEmpty()) {
//            return null;
//        }
//        
//        var user = ContextHolderUtil.getAuthedUserId();
//        return except.stream()
//            .map(item -> {
//                var dto = VendorSkuDTO.builder()
//                    .vendor(id)
//                    .description(item.getDescription())
//                    .grade(item.getGrade())
//                    .tag(item.getTag())
//                    .kSku("0")
//                    .disabled(0)
//                    .build();
//                return VendorSkuPojoConverter.toCreateEntity(dto, user);
//            })
//            .distinct()
//            .collect(Collectors.toList());
        return null;
    }

    @Override
    public boolean deleteOneById(String id) {
        return vendorSkuDao.deleteOneById(id);
    }

    @Override
    public PageInfo<VendorSkuVO> list(VendorSkuPageDTO args) {
        PageMethod.startPage(args.getCurrent(), args.getPageSize(), true);
        var entities = vendorSkuDao.selectBatch(args);
        var records = entities.stream()
            .map(VendorSkuPojoConverter::fromEntity).collect(Collectors.toList());
        return CommonPageConverter.toPageInfo(entities, records);
    }

    @Override
    public Map<String, String> obtainMapOfIdTag(List<String> tags) {
        return vendorSkuDao.selectIdTagKV(tags).stream()
            .collect(Collectors.toMap(IdTagPair::getId, IdTagPair::getTag));
    }

}
