package com.kintexgroup.hkpsi.information.service;


import com.github.pagehelper.PageInfo;
import com.kintexgroup.hkpsi.information.model.ClientDTO;
import com.kintexgroup.hkpsi.information.model.ClientPageDTO;
import com.kintexgroup.hkpsi.information.model.ClientVO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author pengli
 * @author lmao
 * @since 2020/9/10 1:57 下午
 */
@Repository
public interface ClientService {

    /**
     * 添加一条client信息
     *
     * @param dto dto
     * @return 一条记录
     */
    ClientVO create(ClientDTO dto);

    /**
     * 根据ID更新一条client信息
     *
     * @param id  id
     * @param dto dto
     * @return 成功 true/失败 false
     */
    boolean update(Integer id, ClientDTO dto);

    /**
     * 根据ID删除一条client信息
     *
     * @param id id
     * @return 成功 true/失败 false
     */
    boolean delete(Integer id);

    /**
     * 根据ID查询一条client信息
     *
     * @param id id
     * @return 一条记录
     */
    ClientVO selectOne(Integer id);

    /**
     * 查询所有顾客信息
     *
     * @return 查询到的顾客信息
     */
    List<ClientVO> selectAll();


    /**
     * 查询单或多条记录
     *
     * @param dto      查询 dto
     * @param current  当前页面
     * @param pageSize 每页的数量
     */
    PageInfo<ClientVO> list(ClientPageDTO dto, int current, int pageSize);
}
