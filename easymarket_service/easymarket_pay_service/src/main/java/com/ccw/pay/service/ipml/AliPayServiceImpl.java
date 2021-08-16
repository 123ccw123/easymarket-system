package com.ccw.pay.service.ipml;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePrecreateRequest;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.response.AlipayTradePrecreateResponse;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.ccw.pay.service.AliPayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Service
public class AliPayServiceImpl implements AliPayService {

    @Autowired
    private AlipayClient alipayClient;
    @Value("${alipay.notifyUrl}")
    private String notifyUrl;
    @Override
    public Map<String, Object> createNative(String out_trade_no, String total_fee) {
        Map<String,Object> resultMap= new HashMap<>();
        //单位转换 分转元
        Long total_fee_long = Long.parseLong(total_fee);
        BigDecimal total_fee_big = new BigDecimal(total_fee_long);
        BigDecimal divisor= new BigDecimal(100);
        BigDecimal money = total_fee_big.divide(divisor);

        AlipayTradePrecreateRequest request = new AlipayTradePrecreateRequest (); //创建API对应的request类
        request.setNotifyUrl(notifyUrl);
        request . setBizContent ( "{"   +
                "\"out_trade_no\":\""+out_trade_no+"\"," + //商户订单号
                "\"total_amount\":\""+money.doubleValue()+"\","   +
                "\"subject\":\"Iphone6 16G\","   +
                "\"store_id\":\"NJ_001\","   +
                "\"timeout_express\":\"90m\"}" ); //订单允许的最晚付款时间
        try {
            AlipayTradePrecreateResponse response = alipayClient.execute (request);
            System.out.print( response.getBody ());
            //根据response中的结果继续业务逻辑处理
            String code = response.getCode();
            if (code!=null&&code.equals("10000")) {
                resultMap.put("qr_code",response.getQrCode());
                resultMap.put("out_trade_no",response.getOutTradeNo());
                resultMap.put("total_fee",total_fee);
            }
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        return resultMap;
    }

    @Override
    public Map<String, String> selectPayStatus(String out_trade_no) {
        Map<String,String> resultMap = new HashMap();
        AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();
        request.setBizContent("{" +
                " \"out_trade_no\":\""+out_trade_no+"\"," +
                " \"trade_no\":\"\"}"); //设置业务参数
        //发出请求
        try {
            AlipayTradeQueryResponse reaponse = alipayClient.execute(request);
            String code = reaponse.getCode();
            if (code!=null&&code.equals("10000")) {
                resultMap.put("out_trade_no",reaponse.getOutTradeNo());
                resultMap.put("status",reaponse.getTradeStatus());
                resultMap.put("trade_no",reaponse.getTradeNo());
            }
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        return resultMap;
    }

    @Override
    public Map<String, String> createNative(Map<String, String> parameters) {

        Map<String, String> resultMap = new HashMap<>();
        AlipayTradePrecreateRequest request = new AlipayTradePrecreateRequest (); //创建API对应的request类
        request . setBizContent ( "{"   +
                "\"out_trade_no\":\""+parameters.get("out_trade_no")+"\"," + //商户订单号
                "\"body\":\"&queue="+parameters.get("queue")+"&routingKey="+parameters.get("routingKey")+"&exchange="+parameters.get("exchange")+"&username="+parameters.get("username")+"\"," +
                "\"total_amount\":\""+parameters.get("total_amount")+"\","   +
                "\"subject\":\"Iphone6 16G\","   +
                "\"store_id\":\"NJ_001\","   +
                "\"timeout_express\":\"90m\"}" ); //订单允许的最晚付款时间
        try {
            AlipayTradePrecreateResponse response = alipayClient.execute (request);
            System.out.print( response.getBody ());
            //根据response中的结果继续业务逻辑处理
            String code = response.getCode();
            if (code!=null&&code.equals("10000")) {
                resultMap.put("qr_code",response.getQrCode());
                resultMap.put("out_trade_no",response.getOutTradeNo());
                resultMap.put("total_fee",parameters.get("total_amount"));
            }
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        return resultMap;
    }
}
