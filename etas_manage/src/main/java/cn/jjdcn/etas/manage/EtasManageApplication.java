package cn.jjdcn.etas.manage;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
@EnableFeignClients
@MapperScan(basePackages = "cn.jjdcn.etas.manage.dao")
@EnableCaching
public class EtasManageApplication {

    public static void main(String[] args) {
        SpringApplication.run(EtasManageApplication.class);
    }
}
