package com.yaqiwe.exception;

import entity.Resoult;
import entity.StatusCode;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import util.ResoultUtil;

/**
 * @Author yaqiwe
 * @Date 2020/3/16 15:42
 * @Version 1.0
 * 统一异常拦截
 */
@ControllerAdvice
@ResponseBody
public class QaExceptionHandler {

    @ExceptionHandler(Exception.class)
    public Resoult errorHandler(Exception e){
        if(e instanceof QaException){
            QaException baseException = (QaException) e;
            return ResoultUtil.error(baseException.getCode(),baseException.getMessage());
        }else {
            e.printStackTrace();
            return ResoultUtil.error(StatusCode.ERROR.getCode(),e.getMessage());
        }
    }

}
