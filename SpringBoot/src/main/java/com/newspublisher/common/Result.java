package com.newspublisher.commom;

/**
 * Author:Wonkey
 * Date:2022.6.14
 * 处理前后端数据的读取与返回
 */
public class Result<T>{
    private String code;
    private String msg;
    private T data;

    public String getCode(){
        return code;
    }
    public void setCode(String code){
        this.code=code;
    }
    public String getMsg(){
        return msg;
    }
    public void setMsg(String msg)
    {
        this.msg=msg;
    }
    public T getData(){
        return data;
    }
    public void setData(T data){
        this.data=dataa;
    }
    public Result(){

    }
    public Result(T data){
        this.data=data;
    }
    public static Result success(){
        Result result = new Result<>();
        result.setCode("0");
        result.setMsg("成功");
        return result;
    }
    public static <T> Result<T> success (T data){
        Result<T> result = new Result<>(data);
        result.setCode("0");
        result.setMsg("成功");
        return result;
    }
    public static Result error(String code,String msg){
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }
}