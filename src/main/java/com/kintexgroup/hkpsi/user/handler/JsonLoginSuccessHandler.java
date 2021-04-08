package com.kintexgroup.hkpsi.user.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kintexgroup.hkpsi.common.dto.ResponseDTO;
import com.kintexgroup.hkpsi.user.service.impl.JwtUserServiceImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 校验登录成功Handler
 *
 * @author gradylo
 * @since 2020/9/2 10:14 上午
 */
public class JsonLoginSuccessHandler implements AuthenticationSuccessHandler {

    @Resource
    private JwtUserServiceImpl jwtUserService;

    /**
     * 校验成功直接在header设置JWT
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException {
        String token = "Bearer " + jwtUserService
            .saveUserLoginInfo((UserDetails) authentication.getPrincipal());
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json; charset=utf-8");
        ResponseDTO dto = new ResponseDTO(0, "ok", token);
        String body = (new ObjectMapper()).writeValueAsString(dto);
        PrintWriter writer = response.getWriter();
        writer.write(body);
        writer.flush();
    }

}
