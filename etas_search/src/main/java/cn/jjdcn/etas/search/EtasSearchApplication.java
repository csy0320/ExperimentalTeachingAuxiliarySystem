package cn.jjdcn.etas.search;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class EtasSearchApplication {

    public static void main(String[] args) {
        SpringApplication.run(EtasSearchApplication.class, args);
    }

}
