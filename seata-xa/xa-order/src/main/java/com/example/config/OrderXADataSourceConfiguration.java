package com.example.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.zaxxer.hikari.HikariDataSource;
import io.seata.rm.datasource.xa.DataSourceProxyXA;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;

@Configuration
public class OrderXADataSourceConfiguration {
    @Bean("dataSourceProxy")
    public DataSource dataSource(DataSourceProperties properties) {
        DruidDataSource dataSource = createDataSource(properties, DruidDataSource.class);
        // DataSourceProxy for AT mode
        // return new DataSourceProxy(druidDataSource);
        // DataSourceProxyXA for XA mode
        return new DataSourceProxyXA(dataSource);
    }

    protected static <T> T createDataSource(DataSourceProperties properties, Class<? extends DataSource> type) {
        return (T) properties.initializeDataSourceBuilder().type(type).build();
    }

    @Bean("jdbcTemplate")
    public JdbcTemplate jdbcTemplate(DataSource dataSourceProxy) {
        return new JdbcTemplate(dataSourceProxy);
    }
}
