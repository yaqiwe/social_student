package com.yaqiwe.exception;

import entity.StatusCode;
import lombok.Data;

/**
 * @Author yaqiwe
 * @Date 2020/3/16 15:37
 * @Version 1.0
 * 异常信息类
 */
@Data
public class QaException extends RuntimeException {
    private Integer code;

    public QaException(StatusCode code) {
        super(code.getMessage());
        this.code = code.getCode();
    }

    public QaException(String message) {
        super(message);
        this.code = StatusCode.ERROR.getCode();
    }
}
