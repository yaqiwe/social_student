package entity;

import lombok.Getter;

/**
 * @Author yaqiwe
 * @Date 2020/3/15 15:23
 * @Version 1.0
 * 返回状态码
 */
@Getter
public enum StatusCode {
    OK(20000,"成功"),
    ERROR(20001,"失败"),
    LOGIN_ERROE(20002,"用户名或密码错误"),
    ACCESS_ERROR(20003,"权限不足"),
    REMOTE_ERROE(20004,"远程调用失败"),
    REP_ERROE(20005,"重复操作"),

    ENTERPRISE_NULL(30001,"企业ID能不能为空"),
    ;
    private Integer code;

    private String message;

    StatusCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
