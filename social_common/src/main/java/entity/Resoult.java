package entity;

import lombok.Data;

/**
 * @Author yaqiwe
 * @Date 2020/3/15 15:07
 * @Version 1.0
 * 返回格式
 */
@Data
public class Resoult <T> {

    /*返回码*/
    private Integer code;

    /**/
    private boolean flag=false;

    /*描述*/
    private String message;

    /*返回数据*/
    private T data;
}
