package com.ccw.sellersgoods.controller;

import com.ccw.sellersgoods.pojo.Seller;
import com.ccw.sellersgoods.service.SellerService;
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
@Api(tags = "SellerController")
@RestController
@RequestMapping("/seller")
@CrossOrigin
public class SellerController {

    @Autowired
    private SellerService sellerService;

    /***
     * Seller分页条件搜索实现
     * @param seller
     * @param page
     * @param size
     * @return
     */
    @ApiOperation(value = "Seller条件分页查询",notes = "分页条件查询Seller方法详情",tags = {"SellerController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true),
            @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true)
    })
    @PostMapping(value = "/search/{page}/{size}" )
    public Result<PageResult<Seller>> findPage(@RequestBody(required = false) @ApiParam(name = "Seller对象",value = "传入JSON数据",required = false) Seller seller, @PathVariable  int page, @PathVariable  int size){
        //调用SellerService实现分页条件查询Seller
        PageResult<Seller> pageResult = sellerService.findPage(seller, page, size);
        return new Result(true,StatusCode.OK,"查询成功",pageResult);
    }

    /***
     * Seller分页搜索实现
     * @param page:当前页
     * @param size:每页显示多少条
     * @return
     */
    @ApiOperation(value = "Seller分页查询",notes = "分页查询Seller方法详情",tags = {"SellerController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true),
            @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true)
    })
    @GetMapping(value = "/search/{page}/{size}" )
    public Result<PageResult<Seller>> findPage(@PathVariable  int page, @PathVariable  int size){
        //调用SellerService实现分页查询Seller
        PageResult<Seller> pageResult = sellerService.findPage(page, size);
        return new Result<PageResult<Seller>>(true,StatusCode.OK,"查询成功",pageResult);
    }

    /***
     * 多条件搜索品牌数据
     * @param seller
     * @return
     */
    @ApiOperation(value = "Seller条件查询",notes = "条件查询Seller方法详情",tags = {"SellerController"})
    @PostMapping(value = "/search" )
    public Result<List<Seller>> findList(@RequestBody(required = false) @ApiParam(name = "Seller对象",value = "传入JSON数据",required = false) Seller seller){
        //调用SellerService实现条件查询Seller
        List<Seller> list = sellerService.findList(seller);
        return new Result<List<Seller>>(true,StatusCode.OK,"查询成功",list);
    }

    /***
     * 根据ID删除品牌数据
     * @param id
     * @return
     */
    @ApiOperation(value = "Seller根据ID删除",notes = "根据ID删除Seller方法详情",tags = {"SellerController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "String")
    @DeleteMapping(value = "/{id}" )
    public Result delete(@PathVariable String id){
        //调用SellerService实现根据主键删除
        sellerService.delete(id);
        return new Result(true,StatusCode.OK,"删除成功");
    }

    /***
     * 修改Seller数据
     * @param seller
     * @param id
     * @return
     */
    @ApiOperation(value = "Seller根据ID修改",notes = "根据ID修改Seller方法详情",tags = {"SellerController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "String")
    @PutMapping(value="/{id}")
    public Result update(@RequestBody @ApiParam(name = "Seller对象",value = "传入JSON数据",required = false) Seller seller, @PathVariable String id){
        //设置主键值
        seller.setSellerId(id);
        //调用SellerService实现修改Seller
        sellerService.update(seller);
        return new Result(true,StatusCode.OK,"修改成功");
    }

    /***
     * 新增Seller数据
     * @param seller
     * @return
     */
    @ApiOperation(value = "Seller添加",notes = "添加Seller方法详情",tags = {"SellerController"})
    @PostMapping
    public Result add(@RequestBody  @ApiParam(name = "Seller对象",value = "传入JSON数据",required = true) Seller seller){
        //调用SellerService实现添加Seller
        sellerService.add(seller);
        return new Result(true,StatusCode.OK,"添加成功");
    }

    /***
     * 根据ID查询Seller数据
     * @param id
     * @return
     */
    @ApiOperation(value = "Seller根据ID查询",notes = "根据ID查询Seller方法详情",tags = {"SellerController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "String")
    @GetMapping("/{id}")
    public Result<Seller> findById(@PathVariable String id){
        //调用SellerService实现根据主键查询Seller
        Seller seller = sellerService.findById(id);
        return new Result<Seller>(true,StatusCode.OK,"查询成功",seller);
    }

    /***
     * 查询Seller全部数据
     * @return
     */
    @ApiOperation(value = "查询所有Seller",notes = "查询所Seller有方法详情",tags = {"SellerController"})
    @GetMapping
    public Result<List<Seller>> findAll(){
        //调用SellerService实现查询所有Seller
        List<Seller> list = sellerService.findAll();
        return new Result<List<Seller>>(true, StatusCode.OK,"查询成功",list) ;
    }
}
