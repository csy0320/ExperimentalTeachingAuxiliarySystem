package cn.jjdcn.soa.etas.config;

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
@MapperScan(basePackages = {"cn.jjdcn.soa.etas.mapper.fdfs"}, sqlSessionFactoryRef = "fdfsSqlSessionFactory")
public class FdfsDBConfiguration {
    @Primary
    @Bean(name = "fdfsDataSource")
    @ConfigurationProperties(prefix="spring.datasource.fdfs")
    public DataSource fdfsDataSource(){
        return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean(name = "fdfsSqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("fdfsDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource);
        sessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources("classpath*:mapper/soa/fdfs/*.xml"));
        return sessionFactoryBean.getObject();
    }
}
