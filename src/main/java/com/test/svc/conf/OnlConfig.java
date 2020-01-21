package com.test.svc.conf;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = {"com.test.svc.dao.onl"}, sqlSessionTemplateRef = "onlSqlSessionTemplate")
public class OnlConfig {

    @Bean(name = "onlDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.onldb")
    public DataSource onlDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "onlSqlSessionTemplate")
    public SqlSessionTemplate onlSqlSessionTemplate(@Qualifier("onlSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    @Bean(name = "onlSqlSessionFactory")
    public SqlSessionFactory onlSqlSessionFactory(@Qualifier("onlDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);

        try {
            bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:sqlmapper/onlMapper/*.xml"));
            return bean.getObject();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Bean(name = "onlTransactionManager")
    public DataSourceTransactionManager onlTransactionManager(@Qualifier("onlDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
}
