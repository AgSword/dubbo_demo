package com.example.consumer.vo;

import lombok.*;

/**
 * @projectName: dubbo_demo
 * @package: com.example.consumer.controller.vo
 * @className: RespVo
 * @author: LiYinjian
 * @date: 2022/7/20 16:35
 * @version: 1.0
 */

@Data
@AllArgsConstructor
@NoArgsConstructor

public class RespVo<T> {
    Integer code;
    String msg;
    T obj;

    // 登录成功时返回的RespVo
    public static <T> RespVo<T> success(RespVoEnum respVoEnum) {
        return new RespVo<T>(respVoEnum.Success.getCode(), RespVoEnum.Success.getMsg(), null);
    }

    public static <T> RespVo<T> success(RespVoEnum respVoEnum, T obj) {
        return new RespVo<T>(respVoEnum.Success.getCode(), RespVoEnum.Success.getMsg(), obj);
    }

    public static RespVo<Object> error(RespVoEnum respVoEnum) {
        return new RespVo<Object>(respVoEnum.getCode(), respVoEnum.getMsg(), null);
    }


}
