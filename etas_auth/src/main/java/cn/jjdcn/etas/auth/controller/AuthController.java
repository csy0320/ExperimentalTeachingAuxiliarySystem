package cn.jjdcn.etas.auth.controller;

import cn.jjdcn.etas.auth.service.AuthService;
import cn.jjdcn.etas.common.bean.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@RestController
@RequestMapping("auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("accredit")
    public Result<Object> accredit(@RequestParam("username") String username,
                                   @RequestParam("password") String password,
                                   HttpServletResponse response){
        Object result = authService.accredit(username, password);
        if (result == null) {
            return Result.error();
        }
        String token = (String) result ;
        Cookie cookie = new Cookie("TOKEN", token);
        log.info("auth中心登陆成功token:{}",token);
        response.addCookie(cookie);
        return Result.ok().message("登陆成功");
    }
}
