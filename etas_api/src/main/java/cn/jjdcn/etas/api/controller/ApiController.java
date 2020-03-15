package cn.jjdcn.etas.api.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping("api")
public class ApiController {

    @GetMapping("hello")
    public String api(){
        log.info("api");
        return "hellooo";
    }
}
