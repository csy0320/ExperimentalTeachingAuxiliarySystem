package cn.jjdcn.etas.auth;

import cn.jjdcn.etas.auth.feign.UserClient;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
class EtasAuthApplicationTests {

    @Autowired
    private UserClient userClient;
    @Test
    void contextLoads() {

        log.info("user: {}",userClient.doQueryUser("admin", "admin"));
    }

}
