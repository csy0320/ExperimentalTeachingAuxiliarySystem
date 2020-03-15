package cn.jjdcn.etas.manage;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
@EnableFeignClients
@MapperScan(basePackages = "cn.jjdcn.etas.manage.dao")
public class ManageApplication {

    public static void main(String[] args) {
        SpringApplication.run(ManageApplication.class);
    }
}
