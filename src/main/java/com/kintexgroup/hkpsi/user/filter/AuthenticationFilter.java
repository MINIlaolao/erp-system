package com.kintexgroup.hkpsi.user.filter;

import com.fasterxml.jackson.databind.JsonNode;
import com.kintexgroup.hkpsi.common.util.JsonMapper;
import com.kintexgroup.hkpsi.common.util.Md5Util;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.StreamUtils;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * 验证登录拦截器
 *
 * @author gradylo
 * @since 2020/9/2 10:14 上午
 */
public class AuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    /**
     * 拦截url为 "/login" 的POST请求
     */
    public AuthenticationFilter() {
        super(new AntPathRequestMatcher("/api/login", "POST"));
    }

    /**
     * 验证登录判断
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response)
        throws AuthenticationException, IOException {
        //从json中获取account和password
        String body = StreamUtils.copyToString(request.getInputStream(), StandardCharsets.UTF_8);
        String account = null;
        String password = null;
        if (StringUtils.hasText(body)) {
            JsonNode node = JsonMapper.nonEmptyMapper().toJsonNode(body);
            account = node.get("account").asText();
            password = node.get("password").asText();
            // 进行盐值加密
            password = Md5Util.getSaltMd5AndSha(password);
        }
        if (account == null) {
            account = "";
        }
        if (password == null) {
            password = "";
        }
        account = account.trim();
        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(
            account, password);
        return this.getAuthenticationManager().authenticate(authRequest);
    }

}
