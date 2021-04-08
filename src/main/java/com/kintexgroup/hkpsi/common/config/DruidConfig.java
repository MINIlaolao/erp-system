package com.kintexgroup.hkpsi.common.config;


import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.wall.WallConfig;
import com.alibaba.druid.wall.WallFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @author LMAO
 * @since 2020/11/7 15:16
 */
@Configuration
public class DruidConfig {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource druidDataSource() {
        DruidDataSource dataSource = new DruidDataSource();

        // 设置数据源允许执行多条语句
        WallConfig wallConfig = new WallConfig();
        wallConfig.setMultiStatementAllow(true);

        WallFilter wallFilter = new WallFilter();
        wallFilter.setConfig(wallConfig);
        dataSource.getProxyFilters().add(wallFilter);

        return dataSource;
    }

}