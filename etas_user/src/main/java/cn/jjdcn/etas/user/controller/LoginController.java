package cn.jjdcn.etas.user.controller;

import cn.jjdcn.etas.common.bean.Result;
import cn.jjdcn.etas.user.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("user")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("login")
    public Result<String> login(@RequestParam("username") String username, @RequestParam("password") String password, HttpServletResponse response){
        String jwt = loginService.login(username, password);
        if (jwt == null) return Result.error().message("用户名或密码错误");
        Cookie cookie = new Cookie("USER_TOKEN", jwt);
        response.addCookie(cookie);
        return Result.ok().message("登陆成功");
    }
}
