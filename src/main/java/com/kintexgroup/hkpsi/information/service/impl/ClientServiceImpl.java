package com.kintexgroup.hkpsi.information.service.impl;

import com.github.pagehelper.PageInfo;
import com.github.pagehelper.page.PageMethod;
import com.kintexgroup.hkpsi.common.constants.ResponseCode;
import com.kintexgroup.hkpsi.common.exception.BusinessException;
import com.kintexgroup.hkpsi.common.handler.CommonPageConverter;
import com.kintexgroup.hkpsi.common.util.ContextHolderUtil;
import com.kintexgroup.hkpsi.information.dao.ClientDao;
import com.kintexgroup.hkpsi.information.model.ClientDTO;
import com.kintexgroup.hkpsi.information.model.ClientPageDTO;
import com.kintexgroup.hkpsi.information.model.ClientVO;
import com.kintexgroup.hkpsi.information.entity.ClientEntity;
import com.kintexgroup.hkpsi.information.service.ClientService;
import com.kintexgroup.hkpsi.information.util.ClientPojoConverter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author pengli
 * @since 2020/9/10 1:57 下午
 */
@Service("ClientService")
@Transactional(rollbackFor = BusinessException.class)
public class ClientServiceImpl implements ClientService {


    /**
     * 用正则表达式判断输入的联系方式是否合法
     * 区号是以 0 开头的，后面是 2~3 位数，因此在匹配区号的时候可以使用正则表达式0\d{2,3}。
     * 固定电话号码由 7~8 位数字组成，因此可以使用表达式\d{7,8}来进行匹配。
     * 固定电话的组合方式可能是“区号-号码”或者是“区号号码”，因此匹配固定电话号码时，可以使用“0\\d{2,3}[-]?\\d{7,8}|0\\d{2,3}\\s?\\d{7,8}”表达式。
     */
    private static final String REGEX = "0\\d{2,3}[-]?\\d{7,8}|0\\d{2,3}\\s?\\d{7,8}|(\\+\\d{2,3}-)?([1][3-9][0-9]\\d{8})|1[3-9]\\d{9}";


    @Resource
    private ClientDao clientDao;


    @Override
    public ClientVO create(ClientDTO dto) {
        ClientEntity client = ClientPojoConverter
            .toCreateEntity(ContextHolderUtil.getAuthedUserId(), dto);
        if (clientDao.check(client.getName())) {
            throw new BusinessException(ResponseCode.DB_INSERT_DUPLICATE_ERROR);
        }
        //国家区号码
        String country = dto.getCountry();
        //手机号码
        String contact = client.getContact();
        // 国家区号码+手机号码
        String phone = country + "-" + contact;
        // 编译正则表达式
        Pattern pattern = Pattern.compile(REGEX);
        Matcher matcher = pattern.matcher(phone);
        if (matcher.matches()) {
            client.setContact(phone);
            clientDao.insert(client);
            return selectOne(client.getId());
        } else {
            throw new BusinessException(ResponseCode.DB_PHONE_NOT_CONFORM);
        }
    }

    @Override
    public boolean update(Integer id, ClientDTO dto) {
        ClientEntity client = ClientPojoConverter
            .toUpdateEntity(id, ContextHolderUtil.getAuthedUserId(), dto);
        if (clientDao.check(client.getName())) {
            throw new BusinessException(ResponseCode.DB_UPDATE_DUPLICATE_ERROR);
        }
        //用正则表达式判断输入的联系方式是否合法
        // 编译正则表达式
        Pattern pattern = Pattern.compile(REGEX);
        Matcher matcher = pattern.matcher(client.getContact());
        if (matcher.matches()) {
            clientDao.updateByPrimaryKey(client);
            return true;
        } else {
            throw new BusinessException(ResponseCode.DB_PHONE_NOT_CONFORM);
        }
    }

    @Override
    public boolean delete(Integer id) {
        if (clientDao.deleteById(id)) {
            return true;
        }
        throw new BusinessException(ResponseCode.DB_DELETE_ERROR);
    }

    @Override
    public ClientVO selectOne(Integer id) {
        var l = clientDao.selectOneById(id);
        if (l == null) {
            throw new BusinessException(ResponseCode.DB_ID_NULL_ERROR);
        }
        return ClientPojoConverter.fromClient(l);
    }

    @Override
    public List<ClientVO> selectAll() {
        var records = clientDao.selectAll();
        var list = new ArrayList<ClientVO>(records.size());
        if (records.isEmpty()) {
            return list;
        }
        for (var record : records) {
            list.add(ClientPojoConverter.fromClient(record));
        }
        return list;
    }

    @Override
    public PageInfo<ClientVO> list(ClientPageDTO dto, int current, int pageSize) {
        PageMethod.startPage(current, pageSize, true);
        var records = clientDao.selectBatch(dto);
        if (records == null) {
            return new PageInfo<>(new ArrayList<>());
        }
        var list = new ArrayList<ClientVO>(records.size());

        for (var record : records) {
            list.add(ClientPojoConverter.fromClient(record));
        }
        return CommonPageConverter.toPageInfo(records, list);
    }
}
