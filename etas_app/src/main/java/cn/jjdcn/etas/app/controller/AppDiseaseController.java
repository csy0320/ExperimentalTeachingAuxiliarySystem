package cn.jjdcn.etas.app.controller;

import cn.jjdcn.etas.app.pojo.DiseaseVO;
import cn.jjdcn.etas.app.service.DiseaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
@Slf4j
public class AppDiseaseController {

    @Autowired
    private DiseaseService diseaseService;

    @GetMapping("disease")
    public ResponseEntity<DiseaseVO> getDiseaseDetail(@RequestParam("id") Integer id){

        log.info("id:{}",id);

        DiseaseVO result = diseaseService.queryById(id);

        return ResponseEntity.ok(result);
    }
}
