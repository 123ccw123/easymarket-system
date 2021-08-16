package com.ccw.sellersgoods.controller;

import com.ccw.sellersgoods.pojo.Item;
import com.ccw.sellersgoods.service.ItemService;
import com.ccw.entity.PageResult;
import com.ccw.entity.Result;
import com.ccw.entity.StatusCode;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/****
 * @Author:ujiuye
 * @Description:
 * @Date 2021/2/1 14:19
 *****/
@Api(tags = "ItemController")
@RestController
@RequestMapping("/item")
@CrossOrigin
public class ItemController {

    @Autowired
    private ItemService itemService;

    /***
     * Item分页条件搜索实现
     * @param item
     * @param page
     * @param size
     * @return
     */
    @ApiOperation(value = "Item条件分页查询",notes = "分页条件查询Item方法详情",tags = {"ItemController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true),
            @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true)
    })
    @PostMapping(value = "/search/{page}/{size}" )
    public Result<PageResult<Item>> findPage(@RequestBody(required = false) @ApiParam(name = "Item对象",value = "传入JSON数据",required = false) Item item, @PathVariable  int page, @PathVariable  int size){
        //调用ItemService实现分页条件查询Item
        PageResult<Item> pageResult = itemService.findPage(item, page, size);
        return new Result(true,StatusCode.OK,"查询成功",pageResult);
    }

    /***
     * Item分页搜索实现
     * @param page:当前页
     * @param size:每页显示多少条
     * @return
     */
    @ApiOperation(value = "Item分页查询",notes = "分页查询Item方法详情",tags = {"ItemController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true),
            @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true)
    })
    @GetMapping(value = "/search/{page}/{size}" )
    public Result<PageResult<Item>> findPage(@PathVariable  int page, @PathVariable  int size){
        //调用ItemService实现分页查询Item
        PageResult<Item> pageResult = itemService.findPage(page, size);
        return new Result<PageResult<Item>>(true,StatusCode.OK,"查询成功",pageResult);
    }

    /***
     * 多条件搜索品牌数据
     * @param item
     * @return
     */
    @ApiOperation(value = "Item条件查询",notes = "条件查询Item方法详情",tags = {"ItemController"})
    @PostMapping(value = "/search" )
    public Result<List<Item>> findList(@RequestBody(required = false) @ApiParam(name = "Item对象",value = "传入JSON数据",required = false) Item item){
        //调用ItemService实现条件查询Item
        List<Item> list = itemService.findList(item);
        return new Result<List<Item>>(true,StatusCode.OK,"查询成功",list);
    }

    /***
     * 根据ID删除品牌数据
     * @param id
     * @return
     */
    @ApiOperation(value = "Item根据ID删除",notes = "根据ID删除Item方法详情",tags = {"ItemController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Long")
    @DeleteMapping(value = "/{id}" )
    public Result delete(@PathVariable Long id){
        //调用ItemService实现根据主键删除
        itemService.delete(id);
        return new Result(true,StatusCode.OK,"删除成功");
    }

    /***
     * 修改Item数据
     * @param item
     * @param id
     * @return
     */
    @ApiOperation(value = "Item根据ID修改",notes = "根据ID修改Item方法详情",tags = {"ItemController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Long")
    @PutMapping(value="/{id}")
    public Result update(@RequestBody @ApiParam(name = "Item对象",value = "传入JSON数据",required = false) Item item, @PathVariable Long id){
        //设置主键值
        item.setId(id);
        //调用ItemService实现修改Item
        itemService.update(item);
        return new Result(true,StatusCode.OK,"修改成功");
    }

    /***
     * 新增Item数据
     * @param item
     * @return
     */
    @ApiOperation(value = "Item添加",notes = "添加Item方法详情",tags = {"ItemController"})
    @PostMapping
    public Result add(@RequestBody  @ApiParam(name = "Item对象",value = "传入JSON数据",required = true) Item item){
        //调用ItemService实现添加Item
        itemService.add(item);
        return new Result(true,StatusCode.OK,"添加成功");
    }

    /***
     * 根据ID查询Item数据
     * @param id
     * @return
     */
    @ApiOperation(value = "Item根据ID查询",notes = "根据ID查询Item方法详情",tags = {"ItemController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Long")
    @GetMapping("/{id}")
    public Result<Item> findById(@PathVariable Long id){
        //调用ItemService实现根据主键查询Item
        Item item = itemService.findById(id);
        return new Result<Item>(true,StatusCode.OK,"查询成功",item);
    }

    /***
     * 查询Item全部数据
     * @return
     */
    @ApiOperation(value = "查询所有Item",notes = "查询所Item有方法详情",tags = {"ItemController"})
    @GetMapping
    public Result<List<Item>> findAll(){
        //调用ItemService实现查询所有Item
        List<Item> list = itemService.findAll();
        return new Result<List<Item>>(true, StatusCode.OK,"查询成功",list) ;
    }

    /*根据status进行查询*/
    @GetMapping("/findByStatus/{status}")
    public Result<List<Item>> findByStatus(@PathVariable(value = "status") String status){
        List<Item> res = itemService.findByStstus(status);
        return new Result<List<Item>>(true, StatusCode.OK,"查询成功",res);
    }

    /*库存实时变化*/
    @PutMapping("/decrCount/{username}")
    public Result decrCount(@PathVariable(value = "username") String username){
        itemService.decrCount(username);
        return new Result();
    }
}
