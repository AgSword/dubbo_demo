package com.example.consumer.handler;

import com.example.api.enums.RespStatus;
import com.example.consumer.vo.Response;
import com.example.provider.exception.BusinessException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;



/**
 * @Description
 * @projectName: dubbo_demo
 * @package: com.example.consumer.handler
 * @className: GlobalExceptionHandler
 * @author: LiYinjian
 * @date: 2022/8/2 14:47
 * @version: 1.0
 */

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler
    public Response bussinessExceptionHandler (Exception e){
        if (e instanceof BusinessException){
            BusinessException exception = (BusinessException) e;
            return Response.defineError(exception);
        }
        if (e instanceof BindException){
            BindException bindException = (BindException) e;
            String message = bindException.getBindingResult().getFieldError().getDefaultMessage();
            return new Response(50001,message);
        }
        return Response.fail(RespStatus.SERVER_ERROR.getStatus(), RespStatus.SERVER_ERROR.getMsg());
    }
}
