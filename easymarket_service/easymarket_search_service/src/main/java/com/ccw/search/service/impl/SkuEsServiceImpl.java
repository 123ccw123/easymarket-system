package com.ccw.search.service.impl;

import com.alibaba.fastjson.JSON;
import com.ccw.entity.Result;
import com.ccw.search.dao.SkuEsMapper;
import com.ccw.search.pojo.SkuInfo;
import com.ccw.search.service.SkuEsService;
import com.ccw.sellersgoods.feign.ItemFeign;
import com.ccw.sellersgoods.pojo.Item;
import org.apache.ibatis.javassist.runtime.Desc;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.*;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;

@Service
public class SkuEsServiceImpl implements SkuEsService {
    @Autowired
    private ItemFeign itemFeign;
    @Autowired
    private SkuEsMapper skuEsMapper;
    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    @Override
    public void importSku() {
        //远程调用查询SKU服务列表的接口，获得数据
        Result<List<Item>> listResult = itemFeign.findByStatus("1");
        //深度克隆
        List<SkuInfo> infos = JSON.parseArray(JSON.toJSONString(listResult.getData()), SkuInfo.class);

        for (SkuInfo info : infos) {
            //规格选项
            Map specMap = JSON.parseObject(info.getSpec(), Map.class);
            info.setSpecMap(specMap);
        }
        //将SKU数据导入到搜索引擎
        skuEsMapper.saveAll(infos);
    }

    @Override
    public Map<String, Object> search(Map<String, String> serchMap) {
        Map<String, Object> result = new HashMap<>();
        //根据searchMap获取查询条件关键字并进行处理
        String keywords = serchMap.get("keywords");
        if (StringUtils.isEmpty(keywords)) {
            keywords="小米";
        }
        //创建ES查询对象的构建对象
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        //设置查询条件

        //设置高亮查询
        queryBuilder.withHighlightFields(new HighlightBuilder.Field("title"));  //设置高亮字段
        queryBuilder.withHighlightBuilder(new HighlightBuilder().preTags("<em style=\"color:red\">").postTags("</em>"));
        //普通多条件查询
        //queryBuilder.withQuery(QueryBuilders.matchQuery("title",keywords));
        queryBuilder.withQuery(QueryBuilders.multiMatchQuery(keywords,"title","brand","category"));

        //====分组查询=====//
        //terms 表示查询列名 filed表示分组
        //商品类型
        queryBuilder.addAggregation(AggregationBuilders.terms("skuGroupCategory").field("category.keyword"));
        //品牌分类
        queryBuilder.addAggregation(AggregationBuilders.terms("skuGroupBrand").field("brand.keyword"));
        //规格分类
        queryBuilder.addAggregation(AggregationBuilders.terms("skuGroupSpec").field("spec.keyword"));
        //====分组结束=====//

        //!!!!过滤查询!!!!!//
        //创建过滤查询对象
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        //设置过滤查询条件
        if (!StringUtils.isEmpty(serchMap.get("category"))) {
            boolQueryBuilder.filter(QueryBuilders.matchQuery("category",serchMap.get("category")));
        }
        if (!StringUtils.isEmpty(serchMap.get("brand"))) {
            boolQueryBuilder.filter(QueryBuilders.matchQuery("brand",serchMap.get("brand")));
        }
        //规格处理
        if (serchMap!=null){
            for (String s : serchMap.keySet()) {
                if (s.startsWith("spec_")){ //规格信息
                    boolQueryBuilder.filter(QueryBuilders.matchQuery("specMap."+s.substring(5),serchMap.get(s)));
                }
            }
        }

        //价格排序
        if(!StringUtils.isEmpty(serchMap.get("price"))){
            String[] prices = serchMap.get("price").split("-");
            if(!prices[0].equals("0")){
                boolQueryBuilder.filter(QueryBuilders.rangeQuery("price").gte(prices[0]));
            }
            if(!prices[1].equals("*")){
                boolQueryBuilder.filter(QueryBuilders.rangeQuery("price").lt(prices[1]));
            }
        }
        //将过滤查询对象放到查询对象中
        queryBuilder.withFilter(boolQueryBuilder);
        //!!!!!!!!!!!!!!!//

        //分页查询
        Integer pageNum = null;
        if (serchMap.get("pageNum")==null){
            pageNum=1;
        }else{
            try {
                pageNum = Integer.parseInt(serchMap.get("pageNum"));
            } catch (NumberFormatException e) {
                e.printStackTrace();
                pageNum=1;
            }
        }

        Integer pageSize = 20;
        queryBuilder.withPageable(PageRequest.of(pageNum,pageSize));

        //排序
        String sortRule = serchMap.get("sortRule");
        String sortField = serchMap.get("sortField");
        if ((!StringUtils.isEmpty(sortRule))&&(!StringUtils.isEmpty(sortField))){
            queryBuilder.withSort(SortBuilders.fieldSort(sortField).order(sortRule.equals("DESC")?SortOrder.DESC: SortOrder.ASC));
        }

        //创建ES查询对象
        NativeSearchQuery build = queryBuilder.build();

        //根据ES的查询模板进行查询
        SearchHits<SkuInfo> search = elasticsearchRestTemplate.search(build, SkuInfo.class);
        //对高亮结果进行处理
        for (SearchHit<SkuInfo> searchHit : search) {
            //得到高亮结果内容
            Map<String, List<String>> highlightFields = searchHit.getHighlightFields();
            searchHit.getContent().setTitle(highlightFields.get("title").get(0)==null?searchHit.getContent().getTitle():highlightFields.get("title").get(0));
        }
        //处理查询结果

        //====处理分组结果====//
        Terms terms = search.getAggregations().get("skuGroupCategory");
        List<String> categoryList = this.getStringCategoryList(terms);

        Terms brand = search.getAggregations().get("skuGroupBrand");
        List<String> brandList = this.getStringCategoryList(brand);

        Terms spec = search.getAggregations().get("skuGroupSpec");
        Map<String,Set<String>> specMap=this.getStringSpecMap(spec);
        //==================//

        SearchPage<SkuInfo> searchHits = SearchHitSupport.searchPageFor(search, build.getPageable());
        ArrayList<SkuInfo> skuInfoList = new ArrayList<>();
        for (SearchHit<SkuInfo> skuInfoSearchHit : searchHits.getContent()) {
            //从结果中取得商品
            SkuInfo content = skuInfoSearchHit.getContent();
            //重新定义一个商品对象
            SkuInfo skuInfo = new SkuInfo();
            BeanUtils.copyProperties(content,skuInfo);
            skuInfoList.add(skuInfo);
        }
        //将查询结果放到result中并返回
        result.put("rows",skuInfoList); //返回结果集
        result.put("total",searchHits.getTotalElements()); //获取总记录数
        result.put("totalPages",searchHits.getTotalPages());  //获取总页数
        result.put("categoryList",categoryList); //获取商品分组结果
        result.put("brandList",brandList);  //获取品牌分组
        result.put("specMap",specMap); //获取规格选项
        return result;
    }


    private List<String> getStringCategoryList(Terms terms){
        List<String> list=new ArrayList<>();
        if (terms!=null){
            for (Terms.Bucket bucket : terms.getBuckets()) {
                //取得分组以后的值
                String key = bucket.getKeyAsString();
                list.add(key);
            }
        }
        return list;
    }
    private Map<String, Set<String>> getStringSpecMap(Terms terms){
        HashMap<String, Set<String>> hashMap = new HashMap<>();

        HashSet<String> hashSet = new HashSet<>();
        if (terms!=null){
            for (Terms.Bucket bucket : terms.getBuckets()) {
                //取得分组以后的值
                String key = bucket.getKeyAsString();
                hashSet.add(key);  //去重处理
            }
        }
        for (String s : hashSet) {
            Map<String,String> map = JSON.parseObject(s, Map.class);
            for (Map.Entry<String, String> stringStringEntry : map.entrySet()) {
                //取得规格名称
                String key = stringStringEntry.getKey();
                //取得规格选项
                String value = stringStringEntry.getValue();
                //对value去重
                Set<String> set = hashMap.get(key);
                if (set==null){
                    set = new HashSet<>();
                }
                set.add(value);
                hashMap.put(key,set);
            }
        }
        return hashMap;
    }
}
