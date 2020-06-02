package cn.jjdcn.soa.etas.bean;

import cn.jjdcn.soa.etas.response.ResponseCode;
import cn.jjdcn.soa.etas.response.ResponseMessage;
import lombok.Data;

import java.util.HashMap;

@Data
public class Result<T> {
    private Boolean success;
    private Integer code;
    private String message;
    private T data;

    private Result(Boolean success, Integer code, String message, T data) {
        this.success = success;
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static Result ok(){

        return new Result(true, ResponseCode.OK.getCode(), ResponseMessage.OK.getMessage(),new HashMap<>());
    }

    public static Result error(){
        return new Result(false, ResponseCode.ERROR.getCode(), ResponseMessage.ERROR.getMessage(),new HashMap<>());
    }

    public Result success(Boolean success){
        this.success = success;
        return this;
    }
    public Result code(Integer code){
        this.code = code;
        return this;
    }
    public Result message(String message){
        this.message = message;
        return this;
    }
    public Result data(T data){
        this.data = data;
        return this;
    }

}
