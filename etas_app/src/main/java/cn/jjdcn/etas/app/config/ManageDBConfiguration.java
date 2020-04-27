package cn.jjdcn.etas.app.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = {"cn.jjdcn.etas.app.mapper.manage"}, sqlSessionFactoryRef = "manageSqlSessionFactory")
public class ManageDBConfiguration {
    @Primary
    @Bean(name = "manageDataSource")
    @ConfigurationProperties(prefix="spring.datasource.manage")
    public DataSource manageDataSource(){
        return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean(name = "manageSqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("manageDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource);
        sessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources("classpath*:mapper/app/manage/*.xml"));
        return sessionFactoryBean.getObject();
    }
}
