package com.ccw.sellersgoods.feign;

import com.ccw.entity.Result;
import com.ccw.sellersgoods.pojo.Item;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient(value = "SELLERSGOODS")
@RequestMapping("/item")
public interface ItemFeign {
    @GetMapping("/findByStatus/{status}")
    public Result<List<Item>> findByStatus(@PathVariable(value = "status") String status) ;

    @GetMapping("/{id}")
    public Result<Item> findById(@PathVariable(value = "id") Long id);

    /*库存实时变化*/
    @PutMapping("/decrCount/{username}")
    public Result decrCount(@PathVariable(value = "username") String username);
}
