package com.kintexgroup.hkpsi.user.service;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.kintexgroup.hkpsi.common.util.JwtUtil;
import com.kintexgroup.hkpsi.user.service.impl.JwtUserServiceImpl;
import com.kintexgroup.hkpsi.user.util.JwtToken;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.www.NonceExpiredException;

import javax.annotation.Resource;
import java.util.Calendar;

/**
 * JWT校验Provider
 *
 * @author gradylo
 * @since 2020/9/2 10:14 上午
 */
public class JwtAuthenticationProvider implements AuthenticationProvider {

    @Resource
    private final JwtUserServiceImpl userService;

    public JwtAuthenticationProvider(JwtUserServiceImpl userService) {
        this.userService = userService;
    }

    @Override
    public Authentication authenticate(Authentication authentication)
        throws AuthenticationException {
        DecodedJWT jwt = ((JwtToken) authentication).getToken();
        if (jwt.getExpiresAt().before(Calendar.getInstance().getTime())) {
            throw new NonceExpiredException("Token expires");
        }
        String account = jwt.getSubject();
        UserDetails user = userService.getUserLoginInfo(account);
        if (user == null || user.getPassword() == null) {
            throw new NonceExpiredException("Token expires");
        }
        String encryptSalt = user.getPassword();
        try {
            // 判断jwtToken是否合法
            JwtUtil.isVerify(jwt.getToken());
        } catch (Exception e) {
            throw new BadCredentialsException("JWT token verify fail", e);
        }
        return new JwtToken(user, jwt, user.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.isAssignableFrom(JwtToken.class);
    }

}
