package cn.jjdcn.etas.manage.api;

import cn.jjdcn.etas.manage.pojo.Disease;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

public interface DiseaseApi {

    @GetMapping("manage/disease/api/get")
    List<Disease> getAllDisease();

}
