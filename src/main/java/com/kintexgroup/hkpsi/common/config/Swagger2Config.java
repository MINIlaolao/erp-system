package com.kintexgroup.hkpsi.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * swagger 配置
 *
 * @author lmao
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {

    public String getLocalHostAddress()
        throws UnknownHostException {
        return InetAddress.getLocalHost()
            .toString();
    }

    @Bean
    public Docket createRestApi()
        throws UnknownHostException {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo())
            .select()
            .apis(RequestHandlerSelectors.any())
            .paths(PathSelectors.any())
            .build();
    }

    private ApiInfo apiInfo()
        throws UnknownHostException {
        return new ApiInfoBuilder().title("API document")
            .description("API document")
            .termsOfServiceUrl("http://" + getLocalHostAddress() + ":8080/")
            .version("0.1")
            .build();
    }

}