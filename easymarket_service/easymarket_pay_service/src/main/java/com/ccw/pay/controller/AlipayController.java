package com.ccw.pay.controller;

import com.alibaba.fastjson.JSON;
import com.ccw.entity.Result;
import com.ccw.entity.StatusCode;
import com.ccw.order.feign.OrderFeign;
import com.ccw.order.pojo.PayLog;
import com.ccw.pay.service.AliPayService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@RequestMapping("/alipay")
@RestController
public class AlipayController {

    @Autowired
    private AliPayService aliPayService;
    @Autowired
    private OrderFeign orderFeign;
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @RequestMapping("/createNative")
    public Result<Map> createNative(@RequestParam Map<String,String> parameters){

        if (parameters==null) {
            //从缓存中取出支付日志
            Result<PayLog> payLogResult = orderFeign.selectPayLogFromRedis();
            PayLog payLog = null;
            if (payLogResult != null) {
                payLog = payLogResult.getData();
                Map map = aliPayService.createNative(payLog.getOutTradeNo(), payLog.getTotalFee() + "");
                return new Result<>(true, StatusCode.OK, "预下单成功！", map);
            } else {
                Map map = new HashMap();
                return new Result<>(false, StatusCode.ERROR, "预下单失败", map);
            }
        }else {
            //秒杀支付
            Map<String, String> map = aliPayService.createNative(parameters);
            return new Result<>(true,StatusCode.OK,"秒杀预下单成功！",map);
        }
    }

    //
    @RequestMapping("/notify/url")
    public String getResult(HttpServletRequest request){
        Map<String,String> resultMap = new HashMap<>();
        //1、通过request请求解析请求中的参数
        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String name = parameterNames.nextElement();   //key值
            System.out.println("name---------"+name);
            String parameter = request.getParameter(name);
            System.out.println("value---------" + parameter);
            resultMap.put(name,parameter);
        }
        String mapStr = JSON.toJSONString(resultMap);
        System.out.println(mapStr);

        String body = resultMap.get("body");
        String[] strings = body.split("&");
        Map<String,String> map = new HashMap<>();
        for (String string : strings) {
            String[] var = string.split("=");
            map.put(var[0],var[1]);
        }
        rabbitTemplate.convertAndSend(map.get("exchange"),map.get("routingKey"),mapStr);
        return "return----success";
    }

    @RequestMapping("/selectPayStatus")
    public Result<Map> selectPayStatus(String out_trade_no){
        Map<String,String> map = aliPayService.selectPayStatus(out_trade_no);
        if (map==null) {
            return new Result<>(false,StatusCode.ERROR,"支付异常，请重新支付");
        }
        Result result = null;
        int i = 0;
        while(true){
            if (map.get("status")!=null&&map.get("status").equals("TRADE_SUCCESS")) {
                //修改支付状态
                orderFeign.updateStatus(out_trade_no,map.get("trade_no"));
                result = new Result(true,StatusCode.OK,"支付成功！");
                break;
            }
            if (map.get("status")!=null&&map.get("status").equals("TRADE_CLOSED")) {
                result = new Result(true,StatusCode.OK,"交易超时或已经退款！");
                break;
            }
            if (map.get("status")!=null&&map.get("status").equals("TRADE_FINISHED")) {
                result = new Result(true,StatusCode.OK,"交易结束，不可退款！！");
                break;
            }
            try {
                //每三秒执行一次程序
                Thread.sleep(30000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            i++;
            if (i>10){
                result = new Result<>(false,StatusCode.ERROR,"交易失败，请重新进行交易！");
                break;
            }
        }
        return result;
    }
}
