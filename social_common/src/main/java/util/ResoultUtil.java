package util;

import entity.Resoult;
import entity.StatusCode;

/**
 * @Author yaqiwe
 * @Date 2020/3/15 15:16
 * @Version 1.0
 * 返回格式的工具类
 */
public class ResoultUtil {

    //成功
    public static Resoult success(Object data){
        Resoult resoult=new Resoult();
        resoult.setCode(StatusCode.OK.getCode());
        resoult.setMessage(StatusCode.OK.getMessage());
        resoult.setFlag(true);
        resoult.setData(data);
        return resoult;
    }

    public static Resoult success(){
        return success(null);
    }

    //失败
    public static Resoult error(Integer code,String msg){
        Resoult resoult=new Resoult();
        resoult.setCode(code);
        resoult.setFlag(false);
        resoult.setMessage(msg);
        return resoult;
    }

}
