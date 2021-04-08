package com.kintexgroup.hkpsi.user.util;

import com.kintexgroup.hkpsi.common.util.Md5Util;
import com.kintexgroup.hkpsi.user.entity.UserEntity;
import com.kintexgroup.hkpsi.user.model.UserDTO;
import com.kintexgroup.hkpsi.user.model.UserVO;

/**
 * @author gradylo
 * @since 2020/9/23 4:47 下午
 */
public final class UserPojoConverter {

    /**
     * 将从接口请求传入的参数类 转换为 实体类
     *
     * @param dto 参数类
     * @return 实体类
     */
    private static UserEntity toEntity(UserDTO dto) {
        UserEntity entity = new UserEntity();
        entity.setId(dto.getId());
        entity.setEmployeeNo(dto.getEmployeeNo());
        entity.setCompanyName(dto.getCompanyName());
        entity.setEmployeeEmail(dto.getEmployeeEmail());
        entity.setAccess(dto.getAccess());
        entity.setDisabled(dto.getDisabled());
        // 进行盐值加密
        if (dto.getPassword() != null) {
            entity.setPassword(Md5Util.getSaltMd5AndSha(dto.getPassword()));
        }
        return entity;
    }

    /**
     * 用于创建的实体类
     *
     * @param dto 参数类
     * @return 实体类
     */
    public static UserEntity toCreateEntity(UserDTO dto, int creator) {
        UserEntity entity = toEntity(dto);
        entity.setCreatedBy(creator);
        entity.setUpdatedBy(creator);
        return entity;
    }

    /**
     * 用于更新的实体类
     *
     * @param dto 参数类
     * @return 实体类
     */
    public static UserEntity toUpdateEntity(UserDTO dto, int updater) {
        UserEntity entity = toEntity(dto);
        entity.setUpdatedBy(updater);
        entity.setId(updater);
        return entity;
    }

    /**
     * 将 从数据库获取到的实体类 转为发送给前端的 视图类
     */
    public static UserVO fromUserEntity(UserEntity entity) {
        UserVO vo = new UserVO();
        vo.setId(entity.getId());
        vo.setEmployeeNo(entity.getEmployeeNo());
        vo.setEmployeeName(entity.getEmployeeName());
        vo.setEmployeeEmail(entity.getEmployeeEmail());
        vo.setCompanyName(entity.getCompanyName());
        vo.setAccess(entity.getAccess());
        vo.setDisabled(entity.getDisabled());
        vo.setCreatedAt(entity.getCreatedAt());
        vo.setCreatedBy(entity.getCreatedBy());
        vo.setUpdatedAt(entity.getUpdatedAt());
        vo.setUpdatedBy(entity.getUpdatedBy());
        return vo;
    }

}
