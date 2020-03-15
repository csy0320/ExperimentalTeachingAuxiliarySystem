package cn.jjdcn.etas.api.test;

import cn.jjdcn.etas.api.service.mail.MailService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class SendMailTest {

    @Autowired
    private MailService mailService;

    @Test
    public void sendMailTest(){
        log.info("test");
        mailService.sendSimpleMail("shzu_csy@163.com","测试发送邮件","测试发送邮件");
    }
}
