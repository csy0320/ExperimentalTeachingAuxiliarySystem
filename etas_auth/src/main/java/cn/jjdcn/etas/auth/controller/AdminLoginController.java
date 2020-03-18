package cn.jjdcn.etas.auth.controller;

import cn.jjdcn.etas.auth.service.AuthService;
import cn.jjdcn.etas.common.bean.Result;
import cn.jjdcn.etas.user.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequestMapping("auth")
public class AdminLoginController {

    @Autowired
    private AuthService authService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @PostMapping("admin/login")
    public Result adminLogin(@RequestParam("username") String username,
                             @RequestParam("password") String password,
                             @RequestParam("captcha") String captcha,
                             HttpServletRequest request){
        log.info("username:{} password:{} captcha:{}",username,password,captcha);
        // 验证captcha
        String code = stringRedisTemplate.opsForValue().get("captcha" + request.getSession().getId());
        if (StringUtils.isBlank(captcha)) return Result.error().message("请输入验证码").data(null);
        if (!captcha.equalsIgnoreCase(code)) return Result.error().message("验证码错误,请重新获取").data(null);
        stringRedisTemplate.delete(stringRedisTemplate.keys("captcha" + request.getSession().getId()));
        // 获取jwt
//        String jwt = loginService.login(username, password);
        String jwt = ((String) authService.accredit(username, password));
        if (jwt == null) return Result.error().message("用户名或密码错误");
        return Result.ok().message("登陆成功").data(jwt);
    }
}
