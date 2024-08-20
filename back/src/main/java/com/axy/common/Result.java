package com.axy.common;

import lombok.Data;

@Data
public class Result {
    private int code;//200or400
    private String msg;//成功或失败
    private long total;//总记录数
    private Object data;//数据(不确定类型,使用object)

    public static Result fail(String msg){
        return result(400,"失败", 0L,null);
    }
    public static Result success(){
        return result(200,"成功", 0L,null);
    }
    public static Result success(Object data){
        return result(200,"成功", 0L,data);
    }
    public static Result success(long total,Object data){
        return result(200,"成功", total,data);
    }
    private static Result result(int code,String msg,long total,Object data){
        Result res = new Result();
        res.setCode(code);
        res.setMsg(msg);
        res.setTotal(total);
        res.setData(data);
        return res;
    }

}