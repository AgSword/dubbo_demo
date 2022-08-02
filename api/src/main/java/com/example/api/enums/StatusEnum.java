package com.example.api.enums;

import com.fasterxml.jackson.annotation.JsonValue;

import java.io.Serializable;

/**
 * @Description 为blog状态的枚举类，状态
 * @projectName: dubbo_demo
 * @package: com.example.api.enums
 * @className: StatusEnum
 * @author: LiYinjian
 * @date: 2022/8/1 19:51
 * @version: 1.0
 */

public enum StatusEnum implements Serializable {
    DRAFT(0,"草稿"),
    PUBLISHED(1,"已出版");


    private Integer code;
    private String value;

    StatusEnum(Integer code,String value){
        this.code=code;
        this.value=value;
    }

    public Integer getCode() {
        return code;
    }

    @JsonValue
    public String getValue() {
        return value;
    }
    //通过值获取对应的枚举
    public static StatusEnum getStatusEnum(Integer code){
        StatusEnum[] enumConstants = StatusEnum.class.getEnumConstants();
        for (StatusEnum statusEnum:enumConstants){
            if (code.equals(statusEnum.getCode())){
                return statusEnum;
            }
        }
        return PUBLISHED;
    }

}
