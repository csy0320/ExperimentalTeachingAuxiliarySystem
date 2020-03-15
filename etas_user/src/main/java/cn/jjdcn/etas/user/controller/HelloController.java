package cn.jjdcn.etas.user.controller;

import cn.jjdcn.etas.user.entity.User;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("user")
public class HelloController {

    @PostMapping("hello")
    public String hello(@RequestBody Map map){
        System.out.println(map.containsKey("PurchaseReqList"));
        return "hello";
    }
}
