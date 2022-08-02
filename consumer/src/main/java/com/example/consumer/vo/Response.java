package com.example.consumer.vo;

import com.example.api.enums.RespStatus;
import com.example.provider.exception.BusinessException;
import lombok.Data;

import java.io.Serializable;

/**
 * @projectName: dubbo_demo
 * @package: com.example.api.dto
 * @className: Response
 * @author: LiYinjian
 * @date: 2022/7/28 10:56
 * @version: 1.0
 * 此类作为服务提供方的服务返回信息，可直接提供给前端
 */

@Data
public class Response<T> implements Serializable {
    private final Integer status;

    private final String msg;

    private final T obj;

    public Response(Integer status, String msg) {
        this.status = status;
        this.msg = msg;
        this.obj = null;
    }

    public Response(Integer status, String msg, T obj) {
        this.status = status;
        this.msg = msg;
        this.obj = obj;
    }

    public static Response ok() {
        return new Response(RespStatus.OK.getStatus(), RespStatus.OK.getMsg());
    }

    public static <T> Response<T> ok(T obj) {
        return new Response(RespStatus.OK.getStatus(), RespStatus.OK.getMsg(), obj);
    }

    public static Response fail(Integer status, String msg) {
        return new Response(status, msg);
    }

    public static Response defineError(BusinessException e){
        return new Response(e.getErrorCode(),e.getErrorMsg());

    }
}
