package cn.jjdcn.soa.etas.controller;

import cn.jjdcn.soa.etas.utils.RandomCaptchaImageUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

@Slf4j
@Controller
@RequestMapping("api/auth")
public class CaptchaController {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @GetMapping(value = "getCaptchaImag")
    public void getVerify(HttpServletRequest request, HttpServletResponse response) {
        try {
            //设置相应类型,告诉浏览器输出的内容为图片
            response.setContentType("image/jpeg");
            //设置响应头信息，告诉浏览器不要缓存此内容
            response.setHeader("Pragma", "No-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expire", 0);
            RandomCaptchaImageUtil captchaImageUtil = new RandomCaptchaImageUtil();
            //输出验证码图片方法
            String code = captchaImageUtil.getRandCode(request, response);
//            request.getSession().setAttribute("CaptchaCode",code);
            stringRedisTemplate.opsForValue().set("captcha"+request.getSession().getId(),code,60, TimeUnit.SECONDS);
            log.info(request.getSession().getId()+"::" + stringRedisTemplate.opsForValue().get("captcha" + request.getSession().getId()));
        } catch (Exception e) {
            log.error("获取验证码失败>>>>   ", e);
        }
    }
}