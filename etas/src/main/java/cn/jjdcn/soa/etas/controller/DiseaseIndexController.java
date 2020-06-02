package cn.jjdcn.soa.etas.controller;

import cn.jjdcn.soa.etas.pojo.DiseaseIndex;
import cn.jjdcn.soa.etas.service.DiseaseIndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("api/search/disease")
@RestController
public class DiseaseIndexController {

    @Autowired
    private DiseaseIndexService diseaseIndexService;

    @PostMapping("/update")
    DiseaseIndex updateDisease(@RequestBody DiseaseIndex disease){
        return diseaseIndexService.update(disease);
    }

    @DeleteMapping("delete/{id}")
    void deleteDiseaseById(@PathVariable Integer id){
        diseaseIndexService.delete(id);
    }
}
