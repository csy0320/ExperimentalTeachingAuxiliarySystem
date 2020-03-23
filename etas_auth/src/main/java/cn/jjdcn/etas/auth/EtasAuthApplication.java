package cn.jjdcn.etas.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableFeignClients(basePackages = "cn.jjdcn.etas.auth.feign")
@EnableSwagger2
public class EtasAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(EtasAuthApplication.class, args);
    }

}
