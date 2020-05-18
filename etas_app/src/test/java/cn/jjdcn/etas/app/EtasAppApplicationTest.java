//package cn.jjdcn.etas.app;
//
//import cn.jjdcn.etas.app.mapper.fdfs.PictureMapper;
//import cn.jjdcn.etas.app.mapper.manage.DiseaseMapper;
//import cn.jjdcn.etas.app.pojo.Disease;
//import cn.jjdcn.etas.app.pojo.Picture;
//import cn.jjdcn.etas.common.utils.StringToListUtils;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.List;
//
//@SpringBootTest
//public class EtasAppApplicationTest {
//
//    @Autowired
//    private DiseaseMapper diseaseMapper;
//
//    @Autowired
//    private PictureMapper pictureMapper;
//
//
//    @Test
//    void testPicture(){
//        Disease disease = diseaseMapper.queryById(259);
//
//        List<Integer> ids1 = StringToListUtils.splitIdsString(disease.getPathogenPic());
//        List<Integer> ids2 = StringToListUtils.splitIdsString(disease.getSymptomsPic());
//
//
//
////        List<Integer> ids = new ArrayList<>();
//////        ids.add()
//        List<Picture> pictures1 = pictureMapper.queryByIds(ids1);
//        List<Picture> pictures2 = pictureMapper.queryByIds(ids2);
////
//        pictures1.forEach(System.out::println);
//        pictures2.forEach(System.out::println);
//
//    }
//
//}
