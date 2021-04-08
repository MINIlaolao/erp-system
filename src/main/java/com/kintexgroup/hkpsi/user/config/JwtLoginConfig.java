package com.kintexgroup.hkpsi.user.config;

import com.kintexgroup.hkpsi.common.handler.HttpStatusLoginFailureHandler;
import com.kintexgroup.hkpsi.user.filter.JwtAuthenticationFilter;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.HttpSecurityBuilder;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutFilter;

/**
 * JWT校验拦截器配置
 *
 * @param <T>
 * @param <B>
 * @author gradylo
 * @since 2020/9/2 10:14 上午
 */
public class JwtLoginConfig<T extends JwtLoginConfig<T, B>, B extends HttpSecurityBuilder<B>> extends
    AbstractHttpConfigurer<T, B> {

    private final JwtAuthenticationFilter authFilter;

    public JwtLoginConfig() {
        this.authFilter = new JwtAuthenticationFilter();
    }

    @Override
    public void configure(B http) {
        authFilter.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));
        authFilter.setAuthenticationFailureHandler(new HttpStatusLoginFailureHandler());

        JwtAuthenticationFilter filter = postProcess(authFilter);
        http.addFilterBefore(filter, LogoutFilter.class);
    }

    public JwtLoginConfig<T, B> permissiveRequestUrls(String... urls) {
        authFilter.setPermissiveUrl(urls);
        return this;
    }

    public JwtLoginConfig<T, B> tokenValidSuccessHandler(
        AuthenticationSuccessHandler successHandler) {
        authFilter.setAuthenticationSuccessHandler(successHandler);
        return this;
    }

}
