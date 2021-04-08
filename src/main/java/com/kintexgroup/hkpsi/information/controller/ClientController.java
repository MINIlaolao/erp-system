package com.kintexgroup.hkpsi.information.controller;

import com.github.pagehelper.PageInfo;
import com.kintexgroup.hkpsi.common.constants.ResponseCode;
import com.kintexgroup.hkpsi.common.exception.BusinessException;
import com.kintexgroup.hkpsi.common.interfaces.ResponseWrapper;
import com.kintexgroup.hkpsi.information.model.ClientDTO;
import com.kintexgroup.hkpsi.information.model.ClientPageDTO;
import com.kintexgroup.hkpsi.information.model.ClientVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.kintexgroup.hkpsi.information.service.ClientService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author pengli
 * @since 2020/9/10 1:55 下午
 */
@ResponseWrapper
@RestController
@RequestMapping("/api/information")
@Api(tags = {"client"})
public class ClientController {

    @Resource
    private ClientService clientService;

    @PostMapping("/add_client")
    @ApiOperation(value = "添加", notes = "添加一条客户信息")
    public ClientVO addClient(@RequestBody ClientDTO dto) {
        if (dto.getName() == null || dto.getContact() == null) {
            throw new BusinessException(ResponseCode.REQUEST_PARAM_INVALID);
        }
        return clientService.create(dto);
    }

    @PostMapping("/delete_client")
    @ApiOperation(value = "删除", notes = "删除一条客户信息")
    public boolean deleteClient(@NotNull Integer id) {

        return clientService.delete(id);
    }

    @PostMapping("/change_client")
    @ApiOperation(value = "更新", notes = "更新一条客户信息")
    public boolean changeClient(@NotNull Integer id, @Validated @RequestBody ClientDTO dto) {
        return clientService.update(id, dto);
    }

    @GetMapping("/get_one_or_many_client")
    @ApiOperation(value = "查询一条或多条", notes = "查询一条或多条客户信息")
    public PageInfo<ClientVO> getOneOrManyClient(ClientPageDTO dto) {
        return clientService.list(dto, dto.getCurrent(), dto.getPageSize());
    }

    @GetMapping("/get_many_client")
    @ApiOperation(value = "查询多条", notes = "查询多条客户信息")
    public List<ClientVO> getManyClient() {
        return clientService.selectAll();
    }
}
