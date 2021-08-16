package com.ccw.sellersgoods.controller;

import com.ccw.sellersgoods.pojo.GoodsDesc;
import com.ccw.sellersgoods.service.GoodsDescService;
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
@Api(tags = "GoodsDescController")
@RestController
@RequestMapping("/goodsDesc")
@CrossOrigin
public class GoodsDescController {

    @Autowired
    private GoodsDescService goodsDescService;

    /***
     * GoodsDesc分页条件搜索实现
     * @param goodsDesc
     * @param page
     * @param size
     * @return
     */
    @ApiOperation(value = "GoodsDesc条件分页查询",notes = "分页条件查询GoodsDesc方法详情",tags = {"GoodsDescController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true),
            @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true)
    })
    @PostMapping(value = "/search/{page}/{size}" )
    public Result<PageResult<GoodsDesc>> findPage(@RequestBody(required = false) @ApiParam(name = "GoodsDesc对象",value = "传入JSON数据",required = false) GoodsDesc goodsDesc, @PathVariable  int page, @PathVariable  int size){
        //调用GoodsDescService实现分页条件查询GoodsDesc
        PageResult<GoodsDesc> pageResult = goodsDescService.findPage(goodsDesc, page, size);
        return new Result(true,StatusCode.OK,"查询成功",pageResult);
    }

    /***
     * GoodsDesc分页搜索实现
     * @param page:当前页
     * @param size:每页显示多少条
     * @return
     */
    @ApiOperation(value = "GoodsDesc分页查询",notes = "分页查询GoodsDesc方法详情",tags = {"GoodsDescController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true),
            @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true)
    })
    @GetMapping(value = "/search/{page}/{size}" )
    public Result<PageResult<GoodsDesc>> findPage(@PathVariable  int page, @PathVariable  int size){
        //调用GoodsDescService实现分页查询GoodsDesc
        PageResult<GoodsDesc> pageResult = goodsDescService.findPage(page, size);
        return new Result<PageResult<GoodsDesc>>(true,StatusCode.OK,"查询成功",pageResult);
    }

    /***
     * 多条件搜索品牌数据
     * @param goodsDesc
     * @return
     */
    @ApiOperation(value = "GoodsDesc条件查询",notes = "条件查询GoodsDesc方法详情",tags = {"GoodsDescController"})
    @PostMapping(value = "/search" )
    public Result<List<GoodsDesc>> findList(@RequestBody(required = false) @ApiParam(name = "GoodsDesc对象",value = "传入JSON数据",required = false) GoodsDesc goodsDesc){
        //调用GoodsDescService实现条件查询GoodsDesc
        List<GoodsDesc> list = goodsDescService.findList(goodsDesc);
        return new Result<List<GoodsDesc>>(true,StatusCode.OK,"查询成功",list);
    }

    /***
     * 根据ID删除品牌数据
     * @param id
     * @return
     */
    @ApiOperation(value = "GoodsDesc根据ID删除",notes = "根据ID删除GoodsDesc方法详情",tags = {"GoodsDescController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Long")
    @DeleteMapping(value = "/{id}" )
    public Result delete(@PathVariable Long id){
        //调用GoodsDescService实现根据主键删除
        goodsDescService.delete(id);
        return new Result(true,StatusCode.OK,"删除成功");
    }

    /***
     * 修改GoodsDesc数据
     * @param goodsDesc
     * @param id
     * @return
     */
    @ApiOperation(value = "GoodsDesc根据ID修改",notes = "根据ID修改GoodsDesc方法详情",tags = {"GoodsDescController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Long")
    @PutMapping(value="/{id}")
    public Result update(@RequestBody @ApiParam(name = "GoodsDesc对象",value = "传入JSON数据",required = false) GoodsDesc goodsDesc, @PathVariable Long id){
        //设置主键值
        goodsDesc.setGoodsId(id);
        //调用GoodsDescService实现修改GoodsDesc
        goodsDescService.update(goodsDesc);
        return new Result(true,StatusCode.OK,"修改成功");
    }

    /***
     * 新增GoodsDesc数据
     * @param goodsDesc
     * @return
     */
    @ApiOperation(value = "GoodsDesc添加",notes = "添加GoodsDesc方法详情",tags = {"GoodsDescController"})
    @PostMapping
    public Result add(@RequestBody  @ApiParam(name = "GoodsDesc对象",value = "传入JSON数据",required = true) GoodsDesc goodsDesc){
        //调用GoodsDescService实现添加GoodsDesc
        goodsDescService.add(goodsDesc);
        return new Result(true,StatusCode.OK,"添加成功");
    }

    /***
     * 根据ID查询GoodsDesc数据
     * @param id
     * @return
     */
    @ApiOperation(value = "GoodsDesc根据ID查询",notes = "根据ID查询GoodsDesc方法详情",tags = {"GoodsDescController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Long")
    @GetMapping("/{id}")
    public Result<GoodsDesc> findById(@PathVariable Long id){
        //调用GoodsDescService实现根据主键查询GoodsDesc
        GoodsDesc goodsDesc = goodsDescService.findById(id);
        return new Result<GoodsDesc>(true,StatusCode.OK,"查询成功",goodsDesc);
    }

    /***
     * 查询GoodsDesc全部数据
     * @return
     */
    @ApiOperation(value = "查询所有GoodsDesc",notes = "查询所GoodsDesc有方法详情",tags = {"GoodsDescController"})
    @GetMapping
    public Result<List<GoodsDesc>> findAll(){
        //调用GoodsDescService实现查询所有GoodsDesc
        List<GoodsDesc> list = goodsDescService.findAll();
        return new Result<List<GoodsDesc>>(true, StatusCode.OK,"查询成功",list) ;
    }
}
