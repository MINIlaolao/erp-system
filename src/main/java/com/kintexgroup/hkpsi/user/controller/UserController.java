package com.kintexgroup.hkpsi.user.controller;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.kintexgroup.hkpsi.common.dto.BasePageDTO;
import com.kintexgroup.hkpsi.common.interfaces.ResponseWrapper;
import com.kintexgroup.hkpsi.user.model.AccessVO;
import com.kintexgroup.hkpsi.user.model.UserDTO;
import com.kintexgroup.hkpsi.user.model.UserVO;
import com.kintexgroup.hkpsi.user.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


/**
 * @author gradylo
 * @since 2020/9/23 10:30 上午
 */
@ResponseWrapper
@RestController
@RequestMapping("/api/user")
@Api(tags = {"user"})
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping("/add_user")
    @ApiOperation(value = "添加", notes = "添加一个用户信息")
    public boolean add(@RequestBody UserDTO dto) {
        return userService.addOne(dto);
    }

    @PostMapping("/update_user")
    @ApiOperation(value = "修改", notes = "更新用户信息")
    public boolean changeUser(@RequestBody UserDTO dto) {
        return userService.update(dto);
    }

    @GetMapping("/get_all_user")
    @ApiOperation(value = "查询所有", notes = "查询所有用户")
    public List<UserVO> getAllUser() {
        return userService.selectAll();
    }

    @GetMapping("/get_page_user")
    @ApiOperation(value = "分页查询", notes = "分页查询用户")
    public PageInfo<UserVO> selectPage(BasePageDTO dto) {
        return userService.selectPage(dto);
    }

    @GetMapping("/info")
    @ApiOperation(value = "查询", notes = "查询当前用户信息")
    public UserVO getUser() {
        return userService.selectOne();
    }

    @PostMapping("/delete_user")
    @ApiOperation(value = "删除", notes = "删除一个用户信息")
    public boolean deleteOne(@RequestParam Integer id) {
        return userService.deleteOne(id);
    }

    @GetMapping("/get_all_access")
    @ApiOperation(value = "查询", notes = "查询所有权限列表")
    public List<AccessVO> getAllAccess() {
        return userService.getAllAccess();
    }

}
