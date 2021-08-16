package com.ccw.search.service;

import java.util.Map;

public interface SkuEsService {

    /*导入数据*/
    public void importSku();

    /*搜索商品*/
    public Map<String,Object> search(Map<String,String> serchMap);
}
