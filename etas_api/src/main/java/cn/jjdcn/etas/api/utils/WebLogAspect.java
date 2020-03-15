package cn.jjdcn.etas.api.utils;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class WebLogAspect {

    @Pointcut("execution(public * cn.jjdcn.etas.api.controller..*.*(..))")
    public void controllerLog() {}

    @Before("controllerLog()")
    public void logBeforeController(){
        log.info("=======>go into before WebLogAspect");
    }

    @After("controllerLog()")
    public void logAfterController(){
        log.info("=======>go into after WebLogAspect");
    }
}
