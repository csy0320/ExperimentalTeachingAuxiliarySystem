package cn.jjdcn.etas.manage.auth;

import cn.jjdcn.etas.common.exception.RRException;
import cn.jjdcn.etas.common.utils.JwtUtils;
import cn.jjdcn.etas.common.utils.MyJwtUtil;
import cn.jjdcn.etas.manage.auth.annotation.CheckAuth;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.condition.RequestConditionHolder;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

@Aspect
@Component
@Slf4j
public class AuthAspect {

    @Around("@annotation(cn.jjdcn.etas.manage.auth.annotation.CheckAuth)")
    public Object checkAuth(ProceedingJoinPoint joinPoint) {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes attributes = (ServletRequestAttributes) requestAttributes;
        HttpServletRequest request = attributes.getRequest();
        String token = request.getHeader("Token");
        try {
            String id = MyJwtUtil.parseJWT(token).getId();

            log.info("token: {}", token);
            if (id == null) {
                throw new RRException("未登录,请登录后尝试");
            }
//        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
//        Method method = signature.getMethod();
//        CheckAuth checkAuth = method.getAnnotation(CheckAuth.class);
//        String value = checkAuth.value();

            if (!(Long.valueOf(id) == 1L)) {
                throw new RRException("权限不足");
            }

            return joinPoint.proceed();
        } catch (Throwable throwable) {
            throw new RRException("没有权限", throwable);
        }
    }
}
