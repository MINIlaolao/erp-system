package com.kintexgroup.hkpsi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import tk.mybatis.spring.annotation.MapperScan;


/**
 * EnableTransactionManagement 开启事务支持
 * <a href="https://blog.csdn.net/u010963948/article/details/79208328"/>
 *
 * @author lmao
 */
@SpringBootApplication
@EnableTransactionManagement
@MapperScan(basePackages = "com.kintexgroup.hkpsi.*.dao")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
