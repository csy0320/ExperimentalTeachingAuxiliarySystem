//package cn.jjdcn.etas.manage.test;
//
//import cn.jjdcn.etas.manage.feign.DiseaseIndexClient;
//import cn.jjdcn.etas.manage.feign.PictureClient;
//import cn.jjdcn.etas.search.pojo.Disease;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//@SpringBootTest
//@Slf4j
//public class EtasManageApplicationTest {
//
//    @Autowired
//    private PictureClient pictureClient;
//
//    @Autowired
//    private DiseaseIndexClient diseaseClient;
//
//
//    @Test
//    public void testPicture(){
//        log.info("pic 1;2 :{}",pictureClient.doQueryPictureByIds("1;2"));
//    }
//
//    @Test
//    public void testDiseaseClient(){
//        diseaseClient.updateDisease(Disease.builder().id(1).name("ceshi2222222222222").build());
//    }
//}
