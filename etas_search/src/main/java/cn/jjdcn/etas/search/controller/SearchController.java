package cn.jjdcn.etas.search.controller;

import cn.jjdcn.etas.common.bean.Result;
import cn.jjdcn.etas.search.feign.DiseaseClient;
import cn.jjdcn.etas.search.pojo.Disease;
import cn.jjdcn.etas.search.service.DiseaseIndexService;
import cn.jjdcn.etas.search.service.EtasSearchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("search")
@Slf4j
public class SearchController {

    @Autowired
    private DiseaseClient diseaseClient;

    @Autowired
    private EtasSearchService etasSearchService;


    @Autowired
    private DiseaseIndexService diseaseIndexService;

    @GetMapping("disease")
    public Result doSearch(@RequestParam("keyword") String keyword){
        log.info("keyword:{}",keyword);
        List<Disease> diseases = etasSearchService.searchBykeyword(keyword);
        Map<String, Object> map = new HashMap<>();
        map.put("total", diseases.size());
        map.put("results", diseases);
        return Result.ok().data(map);
    }

    @GetMapping("refresh")
    public Result doRefresh(){
        log.info("刷新搜索引擎");
        diseaseIndexService.deleteAll();
        List<cn.jjdcn.etas.manage.pojo.Disease> allDisease = diseaseClient.getAllDisease();

        List<Disease> diseases = allDisease.stream().map(temp -> {
            Disease disease = new Disease();
            disease.setId(temp.getId());
            disease.setClassSpecies(temp.getClassSpecies());
            disease.setSymptomsDesc(temp.getSymptomsDesc());
            disease.setPathogenDesc(temp.getPathogenDesc());
            disease.setPathogenPic(temp.getPathogenPic());
            disease.setPrevention(temp.getPrevention());
            disease.setClassDomain(temp.getClassDomain());
            disease.setClassPhylum(temp.getClassPhylum());
            disease.setClassClass(temp.getClassClass());
            disease.setClassFamily(temp.getClassFamily());
            disease.setClassOrder(temp.getClassOrder());
            disease.setClassGenus(temp.getClassGenus());
            disease.setVirusType(temp.getVirusType());
            disease.setPathogenType(temp.getPathogenType());
            disease.setName(temp.getName());

            return disease;
        }).collect(Collectors.toList());

        diseaseIndexService.updateAll(diseases);

        return Result.ok();
    }


}
