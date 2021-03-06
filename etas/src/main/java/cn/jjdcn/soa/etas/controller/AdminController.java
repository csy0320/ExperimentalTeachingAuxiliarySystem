package cn.jjdcn.soa.etas.controller;

import cn.jjdcn.soa.etas.bean.Result;
import cn.jjdcn.soa.etas.entity.User;
import cn.jjdcn.soa.etas.exception.RRException;
import cn.jjdcn.soa.etas.service.AuthService;
import cn.jjdcn.soa.etas.service.UserService;
import cn.jjdcn.soa.etas.utils.MyJwtUtil;
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
@RequestMapping("api/auth")
public class AdminController {

    @Autowired
    private AuthService authService;

    @Autowired
    private UserService userService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @PostMapping("admin/login")
    public Result adminLogin(@RequestParam("username") String username,
                             @RequestParam("password") String password,
                             @RequestParam("captcha") String captcha,
                             HttpServletRequest request){
//        log.info("username:{} password:{} captcha:{} ,sessionId:{}",username,password,captcha,request.getSession().getId());
        // 验证captcha
        String code = stringRedisTemplate.opsForValue().get("captcha" + request.getSession().getId());
        if (StringUtils.isBlank(captcha)) {
            return Result.error().message("请输入验证码").data(null);
        }
        if (!captcha.equalsIgnoreCase(code)) {
            return Result.error().message("验证码已过期,请刷新获取").data(null);
        }
        stringRedisTemplate.delete(stringRedisTemplate.keys("captcha" + request.getSession().getId()));
        // 获取jwt
        String jwt = authService.accredit(username, password);
        if (jwt == null || "".equals(jwt)) {
            return Result.error().message("用户名或密码错误");
        }
        return Result.ok().message("登录成功").data(jwt);
    }
    @PostMapping("admin/password")
    public Result adminChangePassword(@RequestParam("password") String password,
                             @RequestParam("password1") String password1,
                             HttpServletRequest request){
        if (!password.equals(password1))
            return Result.error().message("两个密码不一致");
        if (password.length()<8)
            return Result.error().message("密码长度不能低于8");
        String token = request.getHeader("Authorization");
        try {
            String id = MyJwtUtil.parseJWT(token).getId();

            User user = User.builder().id(Long.valueOf(id)).password(password).build();
            if (userService.updatePassword(user)){
                return Result.ok();
            }
            return Result.error();
        } catch (Throwable throwable) {
            throw new RRException("没有权限", throwable);
        }

    }
}
