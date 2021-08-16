package com.ccw.sellersgoods.controller;

import com.ccw.sellersgoods.pojo.ItemCat;
import com.ccw.sellersgoods.service.ItemCatService;
import com.ccw.entity.PageResult;
import com.ccw.entity.Result;
import com.ccw.entity.StatusCode;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/****
 * @Author:ujiuye
 * @Description:
 * @Date 2021/2/1 14:19
 *****/
@Api(tags = "ItemCatController")
@RestController
@RequestMapping("/itemCat")
@CrossOrigin
public class ItemCatController {

    @Autowired
    private ItemCatService itemCatService;

    /***
     * ItemCat分页条件搜索实现
     * @param itemCat
     * @param page
     * @param size
     * @return
     */
    @ApiOperation(value = "ItemCat条件分页查询",notes = "分页条件查询ItemCat方法详情",tags = {"ItemCatController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true),
            @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true)
    })
    @PostMapping(value = "/search/{page}/{size}" )
    public Result<PageResult<ItemCat>> findPage(@RequestBody(required = false) @ApiParam(name = "ItemCat对象",value = "传入JSON数据",required = false) ItemCat itemCat, @PathVariable  int page, @PathVariable  int size){
        //调用ItemCatService实现分页条件查询ItemCat
        PageResult<ItemCat> pageResult = itemCatService.findPage(itemCat, page, size);
        return new Result(true,StatusCode.OK,"查询成功",pageResult);
    }

    /***
     * ItemCat分页搜索实现
     * @param page:当前页
     * @param size:每页显示多少条
     * @return
     */
    @ApiOperation(value = "ItemCat分页查询",notes = "分页查询ItemCat方法详情",tags = {"ItemCatController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true),
            @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true)
    })
    @GetMapping(value = "/search/{page}/{size}" )
    public Result<PageResult<ItemCat>> findPage(@PathVariable  int page, @PathVariable  int size){
        //调用ItemCatService实现分页查询ItemCat
        PageResult<ItemCat> pageResult = itemCatService.findPage(page, size);
        return new Result<PageResult<ItemCat>>(true,StatusCode.OK,"查询成功",pageResult);
    }

    /***
     * 多条件搜索品牌数据
     * @param itemCat
     * @return
     */
    @ApiOperation(value = "ItemCat条件查询",notes = "条件查询ItemCat方法详情",tags = {"ItemCatController"})
    @PostMapping(value = "/search" )
    public Result<List<ItemCat>> findList(@RequestBody(required = false) @ApiParam(name = "ItemCat对象",value = "传入JSON数据",required = false) ItemCat itemCat){
        //调用ItemCatService实现条件查询ItemCat
        List<ItemCat> list = itemCatService.findList(itemCat);
        return new Result<List<ItemCat>>(true,StatusCode.OK,"查询成功",list);
    }

    /***
     * 根据ID删除品牌数据
     * @param id
     * @return
     */
    @ApiOperation(value = "ItemCat根据ID删除",notes = "根据ID删除ItemCat方法详情",tags = {"ItemCatController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Long")
    @DeleteMapping(value = "/{id}" )
    public Result delete(@PathVariable Long id){
        //调用ItemCatService实现根据主键删除
        itemCatService.delete(id);
        return new Result(true,StatusCode.OK,"删除成功");
    }

    /***
     * 修改ItemCat数据
     * @param itemCat
     * @param id
     * @return
     */
    @ApiOperation(value = "ItemCat根据ID修改",notes = "根据ID修改ItemCat方法详情",tags = {"ItemCatController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Long")
    @PutMapping(value="/{id}")
    public Result update(@RequestBody @ApiParam(name = "ItemCat对象",value = "传入JSON数据",required = false) ItemCat itemCat, @PathVariable Long id){
        //设置主键值
        itemCat.setId(id);
        //调用ItemCatService实现修改ItemCat
        itemCatService.update(itemCat);
        return new Result(true,StatusCode.OK,"修改成功");
    }

    /***
     * 新增ItemCat数据
     * @param itemCat
     * @return
     */
    @ApiOperation(value = "ItemCat添加",notes = "添加ItemCat方法详情",tags = {"ItemCatController"})
    @PostMapping
    public Result add(@RequestBody  @ApiParam(name = "ItemCat对象",value = "传入JSON数据",required = true) ItemCat itemCat){
        //调用ItemCatService实现添加ItemCat
        itemCatService.add(itemCat);
        return new Result(true,StatusCode.OK,"添加成功");
    }

    /***
     * 根据ID查询ItemCat数据
     * @param id
     * @return
     */
    @ApiOperation(value = "ItemCat根据ID查询",notes = "根据ID查询ItemCat方法详情",tags = {"ItemCatController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Long")
    @GetMapping("/{id}")
    public Result<ItemCat> findById(@PathVariable Long id){
        //调用ItemCatService实现根据主键查询ItemCat
        ItemCat itemCat = itemCatService.findById(id);
        return new Result<ItemCat>(true,StatusCode.OK,"查询成功",itemCat);
    }

    /***
     * 查询ItemCat全部数据
     * @return
     */
    @ApiOperation(value = "查询所有ItemCat",notes = "查询所ItemCat有方法详情",tags = {"ItemCatController"})
    @GetMapping
    public Result<List<ItemCat>> findAll(){
        //调用ItemCatService实现查询所有ItemCat
        List<ItemCat> list = itemCatService.findAll();
        return new Result<List<ItemCat>>(true, StatusCode.OK,"查询成功",list) ;
    }

    /*实现表内子查询*/
    @ApiOperation(value = "表内查询子查询",notes = "查询所ItemCat有方法详情",tags = {"ItemCatController"})
    @ApiImplicitParams(value = {@ApiImplicitParam(name = "parentId",value = "父级id",required = true,dataType = "Long")})
    @GetMapping("/findParentId/{parentId}")
    public Result<List<ItemCat>> findParentId(@PathVariable(value = "parentId") Long parentId){
        //调用ItemCatService实现查询所有ItemCat
        List<ItemCat> list = itemCatService.findParentId(parentId);
        return new Result<List<ItemCat>>(true, StatusCode.OK,"查询成功",list) ;
    }
}
