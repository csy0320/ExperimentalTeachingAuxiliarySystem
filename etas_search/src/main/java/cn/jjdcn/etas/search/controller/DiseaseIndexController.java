package cn.jjdcn.etas.search.controller;

import cn.jjdcn.etas.search.pojo.Disease;
import cn.jjdcn.etas.search.service.DiseaseIndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class DiseaseIndexController {

    @Autowired
    private DiseaseIndexService diseaseIndexService;

    @PostMapping("search/disease/update")
    Disease updateDisease(@RequestBody Disease disease){
        return diseaseIndexService.update(disease);
    }

    @DeleteMapping("search/disease/delete/{id}")
    void deleteDiseaseById(@PathVariable Integer id){
        diseaseIndexService.delete(id);
    }
}
