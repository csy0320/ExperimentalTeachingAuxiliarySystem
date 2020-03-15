package cn.jjdcn.etas.manage.test;

import cn.jjdcn.etas.manage.feign.PictureClient;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class ManageApplicationTest {

    @Autowired
    private PictureClient pictureClient;

    @Test
    public void testPicture(){
        log.info("pic 1;2 :{}",pictureClient.doQueryPictureByIds("1;2"));
    }
}
