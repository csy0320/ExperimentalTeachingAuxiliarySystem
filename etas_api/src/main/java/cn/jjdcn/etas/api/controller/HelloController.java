package cn.jjdcn.etas.api.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("api1")
public class HelloController {

    @GetMapping("hello")
    public String api(){
        return "hellooo1";
    }
}
