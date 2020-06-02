package cn.jjdcn.soa.etas.controller;

import cn.jjdcn.soa.etas.bean.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class MyExcepttionHandler {

    @ExceptionHandler(value = Exception.class)
    public Result exceptionHandler(Exception e){
//        System.out.println("未知异常！原因是:"+e.getMessage());
        return Result.error().message(e.getMessage());
    }
}
