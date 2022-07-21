package com.example.consumer.exception;

import com.example.consumer.vo.RespVo;
import com.example.consumer.vo.RespVoEnum;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.xml.bind.ValidationException;

/**
 * @projectName: dubbo_demo
 * @package: com.example.consumer.exception
 * @className: GlobalExceptionHandler
 * @author: LiYinjian
 * @date: 2022/7/20 17:19
 * @version: 1.0
 */


/**
 * 全局异常处理类
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    /**
     * 处理validator产生的异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler
    public RespVo handlerException(Exception e) {
        if (e instanceof BindException) {
            BindException ex = (BindException) e;
            RespVo<Object> respVo = new RespVo<>();
            respVo.setCode(RespVoEnum.UserInfo_Error.getCode());
            respVo.setMsg("参数校验异常：" + ex.getBindingResult().getAllErrors().get(0).getDefaultMessage());
            return respVo;
        }
        if (e instanceof RuntimeException) {
            return new RespVo<>(500, e.getMessage(), null);
        }
        return RespVo.error(RespVoEnum.Error);
    }
}
