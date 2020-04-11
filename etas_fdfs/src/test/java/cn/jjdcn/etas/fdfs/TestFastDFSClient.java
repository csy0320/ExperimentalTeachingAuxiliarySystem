package cn.jjdcn.etas.fdfs;


import cn.jjdcn.etas.fdfs.utils.MyFastDFSClient;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestFastDFSClient {

    @Test
    public void Upload() {
        String fileUrl = this.getClass().getResource("test.txt").getPath();
        File file = new File(fileUrl);
        String str = MyFastDFSClient.uploadFile(file);
        MyFastDFSClient.getResAccessUrl(str);
    }

    @Test
    public void Delete() {
        MyFastDFSClient.deleteFile("group1/M00/00/00/CpBI415lH7OASANVAAGVnnIpWBw295.jpg");
    }
}
