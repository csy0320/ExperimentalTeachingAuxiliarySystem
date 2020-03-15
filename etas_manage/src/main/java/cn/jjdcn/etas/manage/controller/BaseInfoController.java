package cn.jjdcn.etas.manage.controller;

import cn.jjdcn.etas.common.bean.Result;
import cn.jjdcn.etas.manage.entity.Disease;
import cn.jjdcn.etas.manage.service.DiseaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("manage")
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
