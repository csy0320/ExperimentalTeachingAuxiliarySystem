package cn.jjdcn.etas.fdfs;

import com.github.tobato.fastdfs.FdfsClientConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.context.annotation.Import;
import org.springframework.jmx.support.RegistrationPolicy;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@Import(FdfsClientConfig.class)
@EnableMBeanExport(registration = RegistrationPolicy.IGNORE_EXISTING)
@EnableSwagger2
@SpringBootApplication
@MapperScan(basePackages = "cn.jjdcn.etas.fdfs.dao")
public class EtasFdfsApplication {

    public static void main(String[] args) {
        SpringApplication.run(EtasFdfsApplication.class);
    }
}
