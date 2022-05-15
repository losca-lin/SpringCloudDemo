package com.tjnu.losca.core;

import lombok.Getter;

@Getter
public class ResultJson<T> {
    //多服务通信加上无参构造
    public ResultJson() {}
    /**
     * 状态码
     * */
    private Integer code;
    /**
     * 返回的数据
     * */
    private T obj;
    /**
     * 文字消息
     * */
    private String message;

    private ResultJson(Integer code, T obj, String message) {
        this.code = code;
        this.obj = obj;
        this.message = message;
    }
    public static <T> ResultJson<T> getInstance(ResultCode resultCode, T obj, String message) {
        return new ResultJson<T>(resultCode.getCode(), obj, message);
    }
    public static <T> ResultJson<T> success(T obj, String message) {
        return new ResultJson<T>(ResultCode.SUCCESS.getCode(), obj, message);
    }
    public static <T> ResultJson<T> success(T obj) {
        return new ResultJson<T>(ResultCode.SUCCESS.getCode(), obj,"");
    }
    public static <T> ResultJson<T> failed(String message) {
        return new ResultJson<T>(ResultCode.FAILED.getCode(), null,message);
    }
    public static <T> ResultJson<T> unauth(String message) {
        return new ResultJson<T>(ResultCode.UNAUTHORIZATION.getCode(), null,message);
    }
    public static <T> ResultJson<T> forbid(){
        return new ResultJson<T>(ResultCode.FORBID.getCode(), null, "该用户无权限");
    }
}
