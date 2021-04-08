package com.kintexgroup.hkpsi.user.handler;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.kintexgroup.hkpsi.common.util.ContextHolderUtil;
import com.kintexgroup.hkpsi.common.util.RedisUtils;
import com.kintexgroup.hkpsi.user.service.impl.JwtUserServiceImpl;
import com.kintexgroup.hkpsi.user.util.JwtToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * JWT校验成功Handler
 *
 * @author gradylo
 * @since 2020/9/2 10:14 上午
 */
public class JwtRefreshSuccessHandler implements AuthenticationSuccessHandler {

    /**
     * 刷新间隔5分钟
     */
    private static final int TOKEN_REFRESH_INTERVAL = 300;

    @Resource
    private final JwtUserServiceImpl jwtUserService;

    @Resource
    private RedisUtils redisUtils;

    public JwtRefreshSuccessHandler(JwtUserServiceImpl jwtUserService) {
        this.jwtUserService = jwtUserService;
    }

    /**
     * token认证
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) {
        DecodedJWT jwt = ((JwtToken) authentication).getToken();
        boolean shouldRefresh = shouldTokenRefresh(jwt.getIssuedAt());
        if (shouldRefresh) {
            String newToken = jwtUserService
                .saveUserLoginInfo((UserDetails) authentication.getPrincipal());
            var id = ContextHolderUtil.getAuthedUserId();;
            redisUtils.set("user:accessToken:" + id, newToken, 600L, TimeUnit.SECONDS);
            response.setHeader("Authorization", "Bearer " + newToken);
        }
    }

    /**
     * 是否需要刷新Token
     */
    protected boolean shouldTokenRefresh(Date issueAt) {
        LocalDateTime issueTime = LocalDateTime
            .ofInstant(issueAt.toInstant(), ZoneId.systemDefault());
        return LocalDateTime.now().minusSeconds(TOKEN_REFRESH_INTERVAL).isAfter(issueTime);
    }

}
