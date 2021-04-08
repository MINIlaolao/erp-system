package com.kintexgroup.hkpsi.common.handler;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * http登录失败状态handler
 *
 * @author gradylo
 * @since 2020/9/2 10:14 上午
 */
public class HttpStatusLoginFailureHandler implements AuthenticationFailureHandler {

    /**
     * 身份验证失败,直接返回401
     *
     * @param request
     * @param response
     * @param exception
     */
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) {
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
    }

}
