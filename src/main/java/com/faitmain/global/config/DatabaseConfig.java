package com.faitmain.global.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;


@Configuration
@MapperScan(basePackages = "com.faitmain.domain")
public class DatabaseConfig {

    @Autowired
    private ApplicationContext applicationContext;

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public HikariConfig hikariConfig() {
        return new HikariConfig();
    }

    @Bean(name = "dataSource")
    public DataSource dataSource() throws Exception {
        return new HikariDataSource(hikariConfig());
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean Bean = new SqlSessionFactoryBean();
        
        Bean.setDataSource(dataSource);
        
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Bean.setMapperLocations(resolver.getResources("classpath:mapper/*.xml"));
       
        return Bean.getObject();
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        sqlSessionFactory.getConfiguration().setMapUnderscoreToCamelCase(true);
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
