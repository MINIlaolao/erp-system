package com.kintexgroup.hkpsi.user.service.impl;

import com.kintexgroup.hkpsi.common.constants.JwtType;
import com.kintexgroup.hkpsi.common.util.ContextHolderUtil;
import com.kintexgroup.hkpsi.common.util.JsonUtil;
import com.kintexgroup.hkpsi.common.util.JwtUtil;
import com.kintexgroup.hkpsi.common.util.RedisUtils;
import com.kintexgroup.hkpsi.user.dao.UserDao;
import com.kintexgroup.hkpsi.user.entity.UserEntity;
import com.kintexgroup.hkpsi.user.model.UserInfo;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 身份认证
 *
 * @author gradylo
 * @since 2020/9/2 10:14 上午
 */

public class JwtUserServiceImpl implements UserDetailsService {

    private final PasswordEncoder passwordEncoder;

    private final UserDao userDao;

    @Resource
    private RedisUtils redisUtils;

    public JwtUserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
        //默认使用 bcrypt， strength=10
        this.passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    /**
     * 获取用户信息
     */
    public UserDetails getUserLoginInfo(String username) {
        String salt = "123456ef";
        UserDetails user = loadUserByUsername(username);
        //将salt放到password字段返回
        return User.builder().username(user.getUsername()).password(salt)
            .authorities(user.getAuthorities()).build();
    }

    /**
     * 保存用户登录信息
     */
    public String saveUserLoginInfo(UserDetails userDetails) {
        //BCrypt.gensalt();  正式开发时可以调用该方法实时生成加密的salt
//        String salt = "123456ef";
//        Algorithm algorithm = Algorithm.HMAC256(salt);

        //设置10分钟后过期
        Map<String, Object> chaim = new HashMap<>(16);
        UserEntity user = userDao.selectOneByEmployeeNo(userDetails.getUsername());
        chaim.put("id", user.getId());
        chaim.put("access", user.getAccess());
        var token = JwtUtil
            .encode(userDetails.getUsername(), JwtType.TIMEOUT_MILLS.getTtlMillis(), chaim);
        var userInfo = new UserInfo();
        userInfo.setId(user.getId());
        userInfo.setEmployeeNo(user.getEmployeeNo());
        userInfo.setEmployeeName(user.getEmployeeName());
        userInfo.setAccess(user.getAccess());
        var userInfoJson = JsonUtil.stringify(userInfo);
        redisUtils.set("user:accessToken:" + user.getId(), token, 600L, TimeUnit.SECONDS);
        redisUtils.set("user:userInfo:" + user.getId(), userInfoJson, 8L, TimeUnit.HOURS);
        return token;
    }

    /**
     * 获取数据库用户信息进行登录验证
     */
    @Override
    public UserDetails loadUserByUsername(String account) {
        UserEntity user = userDao.selectOneByEmployeeNo(account);
        if (user == null) {
            throw new UsernameNotFoundException("user not exist");
        }
        return User.builder().username(user.getEmployeeNo())
            .password(passwordEncoder.encode(user.getPassword())).roles("USER").build();
    }

}
