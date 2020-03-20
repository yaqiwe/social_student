package com.yaqiwe.listener;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @Author yaqiwe
 * @Date 2020/3/20 9:26
 * @Version 1.0
 * 发送短信
 */
@Component
@RabbitListener(queues = "sms")
@Slf4j
public class SmsListener {

    @Autowired
    private Environment environment;

    @RabbitHandler
    public void executeSms(Map<String, String> map){
        String mobile=map.get("mobile");
        String checkcode=map.get("checkcode");
        sendSms(mobile,checkcode);
    }

    private void sendSms(String mobile,String checkcode){
        String AccessKeyID=environment.getProperty("aliyun.sms.AccessKeyID");
        String AccessKeySecret=environment.getProperty("aliyun.sms.AccessKeySecret");
        String TemplateCode=environment.getProperty("aliyun.sms.TemplateCode");
        String SignName=environment.getProperty("aliyun.sms.SignName");
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", AccessKeyID, AccessKeySecret);
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.aliyuncs.com");
        request.setSysVersion("2017-05-25");
        request.setSysAction("SendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("PhoneNumbers", mobile);
        request.putQueryParameter("SignName", SignName);
        request.putQueryParameter("TemplateCode", TemplateCode);
        request.putQueryParameter("TemplateParam", "{\"code\":\""+checkcode+"\"}");
        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }
}
