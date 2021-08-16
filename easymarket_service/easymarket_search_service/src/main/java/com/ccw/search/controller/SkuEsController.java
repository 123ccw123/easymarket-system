package com.ccw.search.controller;

import com.ccw.entity.Result;
import com.ccw.search.service.SkuEsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/search")
@CrossOrigin
public class SkuEsController {
    @Autowired
    private SkuEsService skuEsService;

    @PostMapping("/importSku")
    public Result importSku(){
        skuEsService.importSku();
        return new Result();
    }

    /*搜索商品*/
    @GetMapping("/searchGoods")
    public Map search(@RequestParam Map<String,String> searchMap){
        return skuEsService.search(searchMap);
    }
}
