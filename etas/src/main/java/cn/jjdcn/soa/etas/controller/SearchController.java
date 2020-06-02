package cn.jjdcn.soa.etas.controller;

import cn.jjdcn.soa.etas.bean.Result;
import cn.jjdcn.soa.etas.entity.Disease;
import cn.jjdcn.soa.etas.pojo.DiseaseIndex;
import cn.jjdcn.soa.etas.service.DiseaseIndexService;
import cn.jjdcn.soa.etas.service.DiseaseService;
import cn.jjdcn.soa.etas.service.EtasSearchService;
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
@RequestMapping("api/search")
@Slf4j
public class SearchController {


    @Autowired
    private EtasSearchService etasSearchService;

    @Autowired
    private DiseaseService diseaseService;

    @Autowired
    private DiseaseIndexService diseaseIndexService;

    @GetMapping("disease")
    public Result doSearch(@RequestParam("keyword") String keyword){
        log.info("keyword:{}",keyword);
        List<DiseaseIndex> diseases = etasSearchService.searchBykeyword(keyword);
        Map<String, Object> map = new HashMap<>();
        map.put("total", diseases.size());
        map.put("results", diseases);
        return Result.ok().data(map);
    }

    @GetMapping("refresh")
    public Result doRefresh(){
        log.info("刷新搜索引擎");
        diseaseIndexService.deleteAll();
        List<Disease> dd = diseaseService.queryAll();
//
        List<DiseaseIndex> diseaseIndices = dd.stream().map(temp -> {
            DiseaseIndex di = new DiseaseIndex();
            di.setId(temp.getId());
            di.setClassSpecies(temp.getClassSpecies());
            di.setSymptomsDesc(temp.getSymptomsDesc());
            di.setPathogenDesc(temp.getPathogenDesc());
            di.setPathogenPic(temp.getPathogenPic());
            di.setPrevention(temp.getPrevention());
            di.setClassDomain(temp.getClassDomain());
            di.setClassPhylum(temp.getClassPhylum());
            di.setClassClass(temp.getClassClass());
            di.setClassFamily(temp.getClassFamily());
            di.setClassOrder(temp.getClassOrder());
            di.setClassGenus(temp.getClassGenus());
            di.setVirusType(temp.getVirusType());
            di.setPathogenType(temp.getPathogenType());
            di.setName(temp.getName());

            return di;
        }).collect(Collectors.toList());

        diseaseIndexService.updateAll(diseaseIndices);

        return Result.ok();
    }


}
