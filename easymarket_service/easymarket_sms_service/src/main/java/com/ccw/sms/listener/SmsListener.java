package com.ccw.sms.listener;

import com.ccw.sms.utils.SmsUtils;
import org.apache.http.HttpResponse;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
@Component
public class SmsListener {

    @Autowired
    private SmsUtils smsUtils;

    @RabbitListener(queues = "easymarket.sms.queue")
    public void getMessage(Map<String,Object> map){
        String mobile= (String) map.get("mobile");
        String code = (String) map.get("code");
        HttpResponse httpResponse = smsUtils.sendMessage(mobile, code);
    }
}
