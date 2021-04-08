package com.kintexgroup.hkpsi.user.config;

import com.kintexgroup.hkpsi.common.handler.HttpStatusLoginFailureHandler;
import com.kintexgroup.hkpsi.common.util.RedisUtils;
import com.kintexgroup.hkpsi.user.filter.AuthenticationFilter;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.HttpSecurityBuilder;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.security.web.authentication.session.NullAuthenticatedSessionStrategy;

import javax.annotation.Resource;

/**
 * 登录校验拦截器配置
 *
 * @param <T>
 * @param <B>
 * @author gradylo
 * @since 2020/9/2 10:14 上午
 */
public class JsonLoginConfig<T extends JsonLoginConfig<T, B>, B extends HttpSecurityBuilder<B>> extends
    AbstractHttpConfigurer<T, B> {

    @Resource
    private RedisUtils redisUtils;

    private final AuthenticationFilter authFilter;

    public JsonLoginConfig() {
        this.authFilter = new AuthenticationFilter();
    }

    /**
     * 拦截器配置
     */
    @Override
    public void configure(B http) {
        authFilter.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));
        authFilter.setAuthenticationFailureHandler(new HttpStatusLoginFailureHandler());
        authFilter.setSessionAuthenticationStrategy(new NullAuthenticatedSessionStrategy());

        AuthenticationFilter filter = postProcess(authFilter);
        http.addFilterAfter(filter, LogoutFilter.class);
    }

    /**
     * 登录成功handle
     */
    public JsonLoginConfig<T, B> loginSuccessHandler(
        AuthenticationSuccessHandler authSuccessHandler) {
        authFilter.setAuthenticationSuccessHandler(authSuccessHandler);
        return this;
    }

}
