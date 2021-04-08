package com.kintexgroup.hkpsi.user.service.impl;

import com.github.pagehelper.PageInfo;
import com.github.pagehelper.page.PageMethod;
import com.kintexgroup.hkpsi.common.constants.ResponseCode;
import com.kintexgroup.hkpsi.common.dto.BasePageDTO;
import com.kintexgroup.hkpsi.common.exception.BusinessException;
import com.kintexgroup.hkpsi.common.handler.CommonPageConverter;
import com.kintexgroup.hkpsi.common.util.CommonUtil;
import com.kintexgroup.hkpsi.common.util.ContextHolderUtil;
import com.kintexgroup.hkpsi.common.util.ExceptionUtil;
import com.kintexgroup.hkpsi.user.dao.UserDao;
import com.kintexgroup.hkpsi.user.entity.UserEntity;
import com.kintexgroup.hkpsi.user.model.AccessVO;
import com.kintexgroup.hkpsi.user.model.UserDTO;
import com.kintexgroup.hkpsi.user.model.UserVO;
import com.kintexgroup.hkpsi.user.service.UserService;
import com.kintexgroup.hkpsi.user.util.UserPojoConverter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author gradylo
 * @since 2020/9/23 10:32 上午
 */
@Service("UserService")
@Transactional(rollbackFor = BusinessException.class)
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

    @Override
    public boolean addOne(UserDTO dto) {
        // 判断参数是否有问题
        var c1 = CommonUtil.isValidString(dto.getEmployeeNo());
        var c2 = CommonUtil.isValidString(dto.getPassword());
        var c3 = CommonUtil.isValidString(dto.getEmployeeName());
        if (c1 || c2 || c3) {
            throw new BusinessException(ResponseCode.REQUEST_PARAM_INVALID);
        }
        // TODO 更新失败的原因
        
        UserEntity entity = UserPojoConverter.toCreateEntity(dto, ContextHolderUtil.getAuthedUserId());
        return ExceptionUtil.isInsertSuccess(userDao.insertOne(entity));
    }

    @Override
    public boolean update(UserDTO dto) {
        var userId = ContextHolderUtil.getAuthedUserId();
        UserEntity entity = UserPojoConverter.toUpdateEntity(dto, userId);
        // TODO 更新失败的原因
        return ExceptionUtil.isUpdateSuccess(userDao.updateOneById(entity));
    }

    @Override
    public List<UserVO> selectAll() {
        var records = userDao.selectAll();
        var list = new ArrayList<UserVO>(records.size());
        if (records.isEmpty()) {
            return list;
        }
        for (var record : records) {
            list.add(UserPojoConverter.fromUserEntity(record));
        }
        return list;
    }

    @Override
    public PageInfo<UserVO> selectPage(BasePageDTO dto) {
        PageMethod.startPage(dto.getCurrent(), dto.getPageSize(), true);
        var records = userDao.selectPage();
        var list = new ArrayList<UserVO>(records.size());
        for (var record : records) {
            list.add(UserPojoConverter.fromUserEntity(record));
        }
        return CommonPageConverter.toPageInfo(records, list);
    }

    @Override
    public UserVO selectOne() {
        var record = userDao.selectOneById(ContextHolderUtil.getAuthedUserId());
        return UserPojoConverter.fromUserEntity(record);
    }

    @Override
    public boolean deleteOne(int id) {
        return userDao.deleteOne(id);
    }

    @Override
    public List<AccessVO> getAllAccess() {
        return userDao.getAllAccess();
    }


}
