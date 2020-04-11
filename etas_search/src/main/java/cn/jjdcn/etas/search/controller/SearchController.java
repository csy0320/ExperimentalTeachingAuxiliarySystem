package cn.jjdcn.etas.search.controller;

import cn.jjdcn.etas.common.bean.Result;
import cn.jjdcn.etas.search.pojo.Disease;
import cn.jjdcn.etas.search.service.EtasSearchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("search")
@Slf4j
public class SearchController {


    @Autowired
    private EtasSearchService etasSearchService;

    @RequestMapping("disease")
    public Result doSearch(@RequestParam("keyword") String keyword){
        log.info("keyword:{}",keyword);
        List<Disease> diseases = etasSearchService.searchBykeyword(keyword);
        Map<String, Object> map = new HashMap<>();
        map.put("total", diseases.size());
        map.put("results", diseases);
        return Result.ok().data(map);
    }
}
