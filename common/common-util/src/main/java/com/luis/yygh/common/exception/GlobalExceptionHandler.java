package com.luis.yygh.common.exception;


import com.luis.yygh.common.result.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result<Object> error(Exception e) {
        e.printStackTrace();
        return Result.fail(e.getMessage());
    }

    @ExceptionHandler(YyghException.class)
    @ResponseBody
    public Result<Object> error(YyghException e) {
        e.printStackTrace();
        return Result.fail(e.getMessage());
    }

}
