package com.example.consumer.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public enum RespVoEnum {
    Success(200, "查询成功"),
    Error(500, "服务器失败"),
    UserInfo_Error(50001, "用户信息不合法!"),
    UserNoExist_Error(50002, "用户不存在!"),
    Password_Error(50003, "密码错误!"),

    ;

    private final Integer code;
    private final String msg;
}
