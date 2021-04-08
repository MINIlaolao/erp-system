package com.kintexgroup.hkpsi.common.config;

import com.kintexgroup.hkpsi.user.config.JsonLoginConfig;
import com.kintexgroup.hkpsi.user.config.JwtLoginConfig;
import com.kintexgroup.hkpsi.user.dao.UserDao;
import com.kintexgroup.hkpsi.user.handler.JsonLoginSuccessHandler;
import com.kintexgroup.hkpsi.user.handler.JwtRefreshSuccessHandler;
import com.kintexgroup.hkpsi.user.handler.TokenClearLogoutHandler;
import com.kintexgroup.hkpsi.user.service.JwtAuthenticationProvider;
import com.kintexgroup.hkpsi.user.service.impl.JwtUserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import org.springframework.security.web.header.Header;
import org.springframework.security.web.header.writers.StaticHeadersWriter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Collections;

/**
 * Security配置
 *
 * @author gradylo
 * @since 2020/9/2 10:14 上午
 */
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    private UserDao userDao;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
//				.antMatchers("/image/**").permitAll() 
            // 设置对应接口或静态资源访问无需认证
            .antMatchers(HttpMethod.POST, "/api/login", "/api/common/kdn/set_logistics_message", "/api/carton/**").permitAll()
            .antMatchers(HttpMethod.GET, "/api/carton/**", "/api/merchandise/info", "/api/merchandise/lot_id").permitAll()
            //默认除了登录的请求都需要认证    
            .anyRequest().authenticated()
            .and()
            //CSRF禁用，因为不使用session
            .csrf().disable()
            //禁用form登录
            .formLogin().disable()
            //禁用session
            .sessionManagement().disable()
            //支持跨域
            .cors()
            .and()
            //添加header设置，支持跨域和ajax请求
            .headers().addHeaderWriter(new StaticHeadersWriter(Arrays.asList(
            new Header("Access-control-Allow-Origin", "*"),
            new Header("Access-Control-Expose-Headers", "Authorization"))))
            .and()
            //添加校验登录filter
            .apply(new JsonLoginConfig<>()).loginSuccessHandler(jsonLoginSuccessHandler())
            .and()
            //添加校验token的filter
            .apply(new JwtLoginConfig<>()).tokenValidSuccessHandler(jwtRefreshSuccessHandler())
            //许可请求URL
            .permissiveRequestUrls("/api/login", "/api/logout", "/api/common/kdn/set_logistics_message", "/api/carton/generate_new_carton_number", "/api/merchandise/info", "/api/merchandise/lot_id", "/api/carton/**")
            .and()
            .logout()
            //logout url
            .logoutUrl("/api/logout")
            .addLogoutHandler(tokenClearLogoutHandler())
            .logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler());
    }


    /**
     * 忽略拦截url或静态资源文件夹 - web.ignoring(): 会直接过滤该url - 将不会经过Spring Security过滤器链 http.permitAll():
     * 不会绕开spring security验证，相当于是允许该路径通过
     *
     * @param web
     * @throws Exception
     * @author lmao
     */
    @Override
    public void configure(WebSecurity web) {
        //放行swagger
        web.ignoring().antMatchers(HttpMethod.GET,
            "/v2/api-docs",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui.html/**",
            "/webjars/**");
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(daoAuthenticationProvider())
            .authenticationProvider(jwtAuthenticationProvider());
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean("jwtAuthenticationProvider")
    protected AuthenticationProvider jwtAuthenticationProvider() {
        return new JwtAuthenticationProvider(jwtUserService());
    }

    @Bean("daoAuthenticationProvider")
    protected AuthenticationProvider daoAuthenticationProvider() {
        //这里会默认使用BCryptPasswordEncoder比对加密后的密码，注意要跟createUser时保持一致
        DaoAuthenticationProvider daoProvider = new DaoAuthenticationProvider();
        daoProvider.setUserDetailsService(userDetailsService());
        return daoProvider;
    }

    @Override
    protected UserDetailsService userDetailsService() {
        return new JwtUserServiceImpl(userDao);
    }

    @Bean("jwtUserService")
    protected JwtUserServiceImpl jwtUserService() {
        return new JwtUserServiceImpl(userDao);
    }

    /**
     * 校验登录成功Handler
     *
     * @return
     */
    @Bean
    protected JsonLoginSuccessHandler jsonLoginSuccessHandler() {
        return new JsonLoginSuccessHandler();
    }

    /**
     * JWT校验成功Handler
     *
     * @return
     */
    @Bean
    protected JwtRefreshSuccessHandler jwtRefreshSuccessHandler() {
        return new JwtRefreshSuccessHandler(jwtUserService());
    }

    /**
     * 登出清除TokenHandler
     *
     * @return
     */
    @Bean
    protected TokenClearLogoutHandler tokenClearLogoutHandler() {
        return new TokenClearLogoutHandler(jwtUserService());
    }

    @Bean
    protected CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Collections.singletonList("*"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "HEAD", "OPTION"));
        configuration.setAllowedHeaders(Collections.singletonList("*"));
        configuration.addExposedHeader("Authorization");
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

}
