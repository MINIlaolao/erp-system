package com.kintexgroup.hkpsi.information.util;

import com.kintexgroup.hkpsi.information.model.ClientDTO;
import com.kintexgroup.hkpsi.information.model.ClientVO;
import com.kintexgroup.hkpsi.information.entity.ClientEntity;

/**
 * @author pengli
 * @since 2020/9/10 1:58 下午
 */
public final class ClientPojoConverter {

    private ClientPojoConverter() {
    }

    /**
     * 将从接口请求传入的参数类 转换为 实体类
     *
     * @param dto 参数类
     * @return 实体类
     */
    private static ClientEntity toEntity(ClientDTO dto) {
        ClientEntity entity = new ClientEntity();
        entity.setName(dto.getName());
        entity.setContact(dto.getContact());
        entity.setDisabled(dto.getDisabled());
        return entity;
    }


    /**
     * 将 从数据库获取到的实体类 转为发送给前端的 视图类
     *
     * @param client 实体类
     * @return 视图类
     */
    public static ClientVO fromClient(ClientEntity client) {
        ClientVO vo = new ClientVO();
        vo.setId(client.getId());
        vo.setName(client.getName());
        vo.setContact(client.getContact());
        vo.setDisabled(client.getDisabled());
        return vo;
    }

    /**
     * 用于创建的实体类
     *
     * @param creator 创建人id
     * @param dto     参数类
     * @return 实体类
     */
    public static ClientEntity toCreateEntity(Integer creator, ClientDTO dto) {
        ClientEntity entity = toEntity(dto);
        entity.setCreatedBy(creator);
        entity.setUpdatedBy(creator);
        return entity;
    }

    /**
     * 用于更新的实体类
     *
     * @param id      要更新的实体类id
     * @param updater 更新人的id
     * @param dto     参数类
     * @return 实体类
     */
    public static ClientEntity toUpdateEntity(Integer id, Integer updater, ClientDTO dto) {
        ClientEntity entity = toEntity(dto);
        entity.setId(id);
        entity.setUpdatedBy(updater);
        return entity;
    }
}
