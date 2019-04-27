package com.github.car.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * @ClassName DataSourceConfig
 * @Description TODO
 * @Author Ghr
 * @Date 2019/2/3 0003 13:51
 * @Version 1.0
 **/
@Configuration
public class DataSourceConfig {
    @Bean(name="primaryDataSource")
    @Qualifier("primaryDataSource")

    @ConfigurationProperties(prefix = "spring.datasource.primary")
    public DataSource primaryDataSource(){
        return DataSourceBuilder.create().build();
    }

    @Bean(name="secondaryDataSource")
    @Qualifier("secondaryDataSource")
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.secondary")
    public DataSource secondaryDataSource(){
        return DataSourceBuilder.create().build();
    }

    @Bean(name="primaryJdbcTemplate")
    public JdbcTemplate primaryJdbcTemplate(@Qualifier("primaryDataSource") DataSource dataSource){
        return new JdbcTemplate(dataSource);
    }

    @Bean(name="secondaryJdbcTemplate")
    public JdbcTemplate secondaryJdbcTemplate(@Qualifier("secondaryDataSource") DataSource dataSource){
        return new JdbcTemplate(dataSource);
    }
}
