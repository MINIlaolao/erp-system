package com.kintexgroup.hkpsi.user.handler;

import com.kintexgroup.hkpsi.user.service.impl.JwtUserServiceImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登出清除TokenHandler
 *
 * @author gradylo
 * @since 2020/9/2 10:14 上午
 */
public class TokenClearLogoutHandler implements LogoutHandler {

    private final JwtUserServiceImpl jwtUserServiceImpl;

    public TokenClearLogoutHandler(JwtUserServiceImpl jwtUserServiceImpl) {
        this.jwtUserServiceImpl = jwtUserServiceImpl;
    }

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response,
                       Authentication authentication) {
        clearToken(authentication);
    }

    protected void clearToken(Authentication authentication) {
        if (authentication == null) {
            return;
        }
        UserDetails user = (UserDetails) authentication.getPrincipal();
        if (user != null && user.getUsername() != null) {
//            jwtUserServiceImpl.deleteUserLoginInfo(user.getUsername());
        }
    }

}
