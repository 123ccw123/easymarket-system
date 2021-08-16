package com.ccw.controller;

import com.ccw.search.feign.SkuSearchFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller //restcontrller返回数据  controller跳转页面
@RequestMapping("/search")
public class SkuController {

    @Autowired
    private SkuSearchFeign skuSearchFeign;
    @GetMapping("/list")
    public String search(@RequestParam(required = false) Map searchMap, Model model){
        //远程调用搜索页面，查询sku的结果
        Map resultMap = skuSearchFeign.search(searchMap);
        //将结果返回到model模型中
        model.addAttribute("result",resultMap);
        //返回搜索条件
        model.addAttribute("searchMap",searchMap);
        //返回搜索地址
        String url = this.setUrl(searchMap);
        model.addAttribute("url",url);
        //跳转页面
        return "search";
    }

    public String setUrl(Map<String,String> searchMap){
        String url="/search/list";
        if (!CollectionUtils.isEmpty(searchMap)) {
            for (Map.Entry<String, String> stringStringEntry : searchMap.entrySet()) {
                String key = stringStringEntry.getKey();
                String value = stringStringEntry.getValue();
                url+=key+"="+value+"&";
            }
        }
        if (url.lastIndexOf("&")!=-1){
            url.substring(0,url.lastIndexOf("&"));
        }
        return url;
    }
}
