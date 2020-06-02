package cn.jjdcn.soa.etas.controller;

import cn.jjdcn.soa.etas.bean.Result;
import cn.jjdcn.soa.etas.service.DiseaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("api/manage")
public class BaseInfoController {
    @Autowired
    private DiseaseService diseaseService;

    @GetMapping("info")
    public Result<Map> getInfo() {
        Map baseInfo = diseaseService.queryBaseInfo();
        if (baseInfo != null) {
            return Result.ok().data(baseInfo);
        } else {
            return Result.error().message("没有数据");
        }
    }
}
