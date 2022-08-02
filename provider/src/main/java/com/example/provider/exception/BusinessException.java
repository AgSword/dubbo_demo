package com.example.provider.exception;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Description
 * @projectName: dubbo_demo
 * @package: com.example.provider.exception
 * @className: BusinessException
 * @author: LiYinjian
 * @date: 2022/8/2 14:35
 * @version: 1.0
 */

@Data
@NoArgsConstructor
public class BusinessException extends RuntimeException implements Serializable {
    private Integer errorCode;
    private String errorMsg;

    public BusinessException( Integer errorCode, String errorMsg){
        super(errorMsg);
        this.errorCode=errorCode;
        this.errorMsg=errorMsg;
    }
}
