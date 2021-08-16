package com.ccw.order.controller;

import com.ccw.order.pojo.PayLog;
import com.ccw.order.service.PayLogService;
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
@Api(tags = "PayLogController")
@RestController
@RequestMapping("/payLog")
@CrossOrigin
public class PayLogController {

    @Autowired
    private PayLogService payLogService;

    /***
     * PayLog分页条件搜索实现
     * @param payLog
     * @param page
     * @param size
     * @return
     */
    @ApiOperation(value = "PayLog条件分页查询",notes = "分页条件查询PayLog方法详情",tags = {"PayLogController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true),
            @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true)
    })
    @PostMapping(value = "/search/{page}/{size}" )
    public Result<PageResult<PayLog>> findPage(@RequestBody(required = false) @ApiParam(name = "PayLog对象",value = "传入JSON数据",required = false) PayLog payLog, @PathVariable  int page, @PathVariable  int size){
        //调用PayLogService实现分页条件查询PayLog
        PageResult<PayLog> pageResult = payLogService.findPage(payLog, page, size);
        return new Result(true,StatusCode.OK,"查询成功",pageResult);
    }

    /***
     * PayLog分页搜索实现
     * @param page:当前页
     * @param size:每页显示多少条
     * @return
     */
    @ApiOperation(value = "PayLog分页查询",notes = "分页查询PayLog方法详情",tags = {"PayLogController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true),
            @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true)
    })
    @GetMapping(value = "/search/{page}/{size}" )
    public Result<PageResult<PayLog>> findPage(@PathVariable  int page, @PathVariable  int size){
        //调用PayLogService实现分页查询PayLog
        PageResult<PayLog> pageResult = payLogService.findPage(page, size);
        return new Result<PageResult<PayLog>>(true,StatusCode.OK,"查询成功",pageResult);
    }

    /***
     * 多条件搜索品牌数据
     * @param payLog
     * @return
     */
    @ApiOperation(value = "PayLog条件查询",notes = "条件查询PayLog方法详情",tags = {"PayLogController"})
    @PostMapping(value = "/search" )
    public Result<List<PayLog>> findList(@RequestBody(required = false) @ApiParam(name = "PayLog对象",value = "传入JSON数据",required = false) PayLog payLog){
        //调用PayLogService实现条件查询PayLog
        List<PayLog> list = payLogService.findList(payLog);
        return new Result<List<PayLog>>(true,StatusCode.OK,"查询成功",list);
    }

    /***
     * 根据ID删除品牌数据
     * @param id
     * @return
     */
    @ApiOperation(value = "PayLog根据ID删除",notes = "根据ID删除PayLog方法详情",tags = {"PayLogController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "String")
    @DeleteMapping(value = "/{id}" )
    public Result delete(@PathVariable String id){
        //调用PayLogService实现根据主键删除
        payLogService.delete(id);
        return new Result(true,StatusCode.OK,"删除成功");
    }

    /***
     * 修改PayLog数据
     * @param payLog
     * @param id
     * @return
     */
    @ApiOperation(value = "PayLog根据ID修改",notes = "根据ID修改PayLog方法详情",tags = {"PayLogController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "String")
    @PutMapping(value="/{id}")
    public Result update(@RequestBody @ApiParam(name = "PayLog对象",value = "传入JSON数据",required = false) PayLog payLog, @PathVariable String id){
        //设置主键值
        payLog.setOutTradeNo(id);
        //调用PayLogService实现修改PayLog
        payLogService.update(payLog);
        return new Result(true,StatusCode.OK,"修改成功");
    }

    /***
     * 新增PayLog数据
     * @param payLog
     * @return
     */
    @ApiOperation(value = "PayLog添加",notes = "添加PayLog方法详情",tags = {"PayLogController"})
    @PostMapping
    public Result add(@RequestBody  @ApiParam(name = "PayLog对象",value = "传入JSON数据",required = true) PayLog payLog){
        //调用PayLogService实现添加PayLog
        payLogService.add(payLog);
        return new Result(true,StatusCode.OK,"添加成功");
    }

    /***
     * 根据ID查询PayLog数据
     * @param id
     * @return
     */
    @ApiOperation(value = "PayLog根据ID查询",notes = "根据ID查询PayLog方法详情",tags = {"PayLogController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "String")
    @GetMapping("/{id}")
    public Result<PayLog> findById(@PathVariable String id){
        //调用PayLogService实现根据主键查询PayLog
        PayLog payLog = payLogService.findById(id);
        return new Result<PayLog>(true,StatusCode.OK,"查询成功",payLog);
    }

    /***
     * 查询PayLog全部数据
     * @return
     */
    @ApiOperation(value = "查询所有PayLog",notes = "查询所PayLog有方法详情",tags = {"PayLogController"})
    @GetMapping
    public Result<List<PayLog>> findAll(){
        //调用PayLogService实现查询所有PayLog
        List<PayLog> list = payLogService.findAll();
        return new Result<List<PayLog>>(true, StatusCode.OK,"查询成功",list) ;
    }
}
