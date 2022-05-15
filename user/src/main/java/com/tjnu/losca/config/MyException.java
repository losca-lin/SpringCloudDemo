package com.tjnu.losca.config;

import com.tjnu.losca.core.ResultJson;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class MyException {
    @ExceptionHandler
    public ResultJson myExceptionHandler(Exception ex){
        ex.printStackTrace();
        return ResultJson.failed(ex.getMessage());
    }
}