package com.ccw.search.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(value = "SEARCH")
public interface SkuSearchFeign {
    @GetMapping("/search/searchGoods")
    public Map search(@RequestParam Map<String,String> searchMap);
}
