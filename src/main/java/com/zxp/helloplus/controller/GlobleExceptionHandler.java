package com.zxp.helloplus.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@ControllerAdvice
@ResponseBody
@Slf4j
public class GlobleExceptionHandler {
    /**
     * 要拦截的异常Exception
     */
    @ExceptionHandler(value = Exception.class)
    public String exceptionHandler(Exception e) {
        log.info("异常类型：{}\n异常信息：{}", e.getClass(), e.getMessage());
        if (e instanceof MethodArgumentNotValidException) {
            MethodArgumentNotValidException ex = (MethodArgumentNotValidException) e;
            return ex.getMessage();
        } else if (e instanceof BindException) {
            BindException ex = (BindException) e;
            List<ObjectError> allErrors = ex.getAllErrors();
            ObjectError objectError = allErrors.get(0);
            String ms = objectError.getDefaultMessage();
            return ms;
        } else {

            return "其他异常";
        }
    }
}