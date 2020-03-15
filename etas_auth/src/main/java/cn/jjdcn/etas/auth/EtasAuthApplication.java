package cn.jjdcn.etas.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class EtasAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(EtasAuthApplication.class, args);
    }

}
