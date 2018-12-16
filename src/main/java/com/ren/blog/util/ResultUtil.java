package com.ren.blog.util;/*
 *@Author:WuRen
 *@Description:
 *@date: 20:54 2018/12/14
 */

public class ResultUtil <T>{


    private static final String CODE_SUCCESS = "success";

    private static final String CODE_FAIL = "fail";

    private String code;
    private T data;
    private String msg;

    public ResultUtil(){

    }

    public ResultUtil(String code){
        this.code = code;
    }

    public ResultUtil(String code, T data){
        this.code = code;
        this.data = data;
    }

    public ResultUtil(String code, String msg){
        this.code = code;
        this.msg = msg;
    }

    public static ResultUtil success(){
        return new ResultUtil(CODE_SUCCESS);
    }

    public static ResultUtil success(Object data){
        return new ResultUtil(CODE_SUCCESS, data);
    }

    public static ResultUtil fail(String msg){
        return new ResultUtil(CODE_FAIL, msg);
    }

    public static ResultUtil widthCode(String errorCode) {
        return new ResultUtil (errorCode);
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
