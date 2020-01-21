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
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = {"com.test.svc.dao.mgm"}, sqlSessionTemplateRef = "mgmSqlSessionTemplate")
public class MgmConfig {

    @Bean(name = "mgmDataSource")
    @Primary //必须加此注解，不然报错，下一个类则不需要添加
    @ConfigurationProperties(prefix = "spring.datasource.mgmdb") // prefix值必须是application.properteis中对应属性的前缀
    public DataSource mgmDataSource() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        return dataSourceBuilder.build();
    }

    @Bean(name = "mgmSqlSessionFactory")
    @Primary
    public SqlSessionFactory mgmSqlSessionFactory(@Qualifier("mgmDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        //添加XML目录
        try {
            bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:sqlmapper/mgmMapper/*.xml"));
            return bean.getObject();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Bean(name = "mgmSqlSessionTemplate")
    @Primary
    public SqlSessionTemplate mgmSqlSessionTemplate(@Qualifier("mgmSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    @Bean(name = "mgmTransactionManager")
    @Primary
    public DataSourceTransactionManager mgmTransactionManager(@Qualifier("mgmDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
}
