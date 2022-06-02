//package com.faitmain.global.config;
//
//import com.zaxxer.hikari.HikariConfig;
//import com.zaxxer.hikari.HikariDataSource;
//import org.apache.ibatis.session.SqlSessionFactory;
//import org.mybatis.spring.SqlSessionFactoryBean;
//import org.mybatis.spring.SqlSessionTemplate;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
//
//import javax.sql.DataSource;
//
//
//@Configuration
//public class DatabaseConfig{
//
//
//    @Bean
//    @ConfigurationProperties( prefix = "spring.datasource" )
//    public HikariConfig hikariConfig(){
//        return new HikariConfig();
//    }
//
//    @Bean
//    public DataSource dataSource(){
//        return new HikariDataSource( hikariConfig() );
//    }

//    @Bean
//    public SqlSessionFactory sqlSessionFactory( DataSource dataSource ) throws Exception{
//        SqlSessionFactoryBean Bean = new SqlSessionFactoryBean();
//
//        Bean.setDataSource( dataSource );
//
//        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
//        Bean.setMapperLocations( resolver.getResources( "classpath:mapper/*.xml" ) );
//
//        return Bean.getObject();
//    }

//    @Bean
//    public SqlSessionTemplate sqlSessionTemplate( SqlSessionFactory sqlSessionFactory ){
//        sqlSessionFactory.getConfiguration().setMapUnderscoreToCamelCase( true );
//        return new SqlSessionTemplate( sqlSessionFactory );
//    }
//}
