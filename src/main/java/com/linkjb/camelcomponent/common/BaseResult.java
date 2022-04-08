package com.linkjb.camelcomponent.common;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName BaseResult
 * @Description 通用返回类
 * @Author shark
 * @Data 2022/3/18 16:16
 **/
@Data
public class BaseResult {

    //成功状态码
    public static final int OK = 1;
    //失败状态码
    public static final int ERROR = 0;

    //返回码
    private Integer code;
    //返回消息
    private String message;

    //存放数据
    private Object data;
    //其他数据
    private final Map<String,Object> other = new HashMap<>();


    public BaseResult(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
    public BaseResult(Integer code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /**
     * 快捷成功BaseResult对象
     */
    public static BaseResult ok(String message){
        return new BaseResult(BaseResult.OK , message);
    }

    public static BaseResult ok(String message, Object data){
        return new BaseResult(BaseResult.OK , message, data );
    }

    /**
     * 快捷失败BaseResult对象
     */
    public static BaseResult error(String message){
        return new BaseResult(BaseResult.ERROR , message);
    }

    /**
     * 自定义数据区域
     */
    public BaseResult append(String key , Object msg){
        other.put(key , msg);
        return this;
    }
}

