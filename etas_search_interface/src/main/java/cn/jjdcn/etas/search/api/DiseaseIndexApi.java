package cn.jjdcn.etas.search.api;

import cn.jjdcn.etas.search.pojo.Disease;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface DiseaseIndexApi {

    @PostMapping("search/disease/update")
    Disease updateDisease(@RequestBody Disease disease);

    @DeleteMapping("search/disease/delete/{id}")
    void deleteDiseaseById(@PathVariable Integer id);
}
