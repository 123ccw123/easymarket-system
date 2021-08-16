package com.ccw.sellersgoods.controller;

import com.ccw.sellersgoods.pojo.SpecificationOption;
import com.ccw.sellersgoods.service.SpecificationOptionService;
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
@Api(tags = "SpecificationOptionController")
@RestController
@RequestMapping("/specificationOption")
@CrossOrigin
public class SpecificationOptionController {

    @Autowired
    private SpecificationOptionService specificationOptionService;

    /***
     * SpecificationOption分页条件搜索实现
     * @param specificationOption
     * @param page
     * @param size
     * @return
     */
    @ApiOperation(value = "SpecificationOption条件分页查询",notes = "分页条件查询SpecificationOption方法详情",tags = {"SpecificationOptionController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true),
            @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true)
    })
    @PostMapping(value = "/search/{page}/{size}" )
    public Result<PageResult<SpecificationOption>> findPage(@RequestBody(required = false) @ApiParam(name = "SpecificationOption对象",value = "传入JSON数据",required = false) SpecificationOption specificationOption, @PathVariable  int page, @PathVariable  int size){
        //调用SpecificationOptionService实现分页条件查询SpecificationOption
        PageResult<SpecificationOption> pageResult = specificationOptionService.findPage(specificationOption, page, size);
        return new Result(true,StatusCode.OK,"查询成功",pageResult);
    }

    /***
     * SpecificationOption分页搜索实现
     * @param page:当前页
     * @param size:每页显示多少条
     * @return
     */
    @ApiOperation(value = "SpecificationOption分页查询",notes = "分页查询SpecificationOption方法详情",tags = {"SpecificationOptionController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true),
            @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true)
    })
    @GetMapping(value = "/search/{page}/{size}" )
    public Result<PageResult<SpecificationOption>> findPage(@PathVariable  int page, @PathVariable  int size){
        //调用SpecificationOptionService实现分页查询SpecificationOption
        PageResult<SpecificationOption> pageResult = specificationOptionService.findPage(page, size);
        return new Result<PageResult<SpecificationOption>>(true,StatusCode.OK,"查询成功",pageResult);
    }

    /***
     * 多条件搜索品牌数据
     * @param specificationOption
     * @return
     */
    @ApiOperation(value = "SpecificationOption条件查询",notes = "条件查询SpecificationOption方法详情",tags = {"SpecificationOptionController"})
    @PostMapping(value = "/search" )
    public Result<List<SpecificationOption>> findList(@RequestBody(required = false) @ApiParam(name = "SpecificationOption对象",value = "传入JSON数据",required = false) SpecificationOption specificationOption){
        //调用SpecificationOptionService实现条件查询SpecificationOption
        List<SpecificationOption> list = specificationOptionService.findList(specificationOption);
        return new Result<List<SpecificationOption>>(true,StatusCode.OK,"查询成功",list);
    }

    /***
     * 根据ID删除品牌数据
     * @param id
     * @return
     */
    @ApiOperation(value = "SpecificationOption根据ID删除",notes = "根据ID删除SpecificationOption方法详情",tags = {"SpecificationOptionController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Long")
    @DeleteMapping(value = "/{id}" )
    public Result delete(@PathVariable Long id){
        //调用SpecificationOptionService实现根据主键删除
        specificationOptionService.delete(id);
        return new Result(true,StatusCode.OK,"删除成功");
    }

    /***
     * 修改SpecificationOption数据
     * @param specificationOption
     * @param id
     * @return
     */
    @ApiOperation(value = "SpecificationOption根据ID修改",notes = "根据ID修改SpecificationOption方法详情",tags = {"SpecificationOptionController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Long")
    @PutMapping(value="/{id}")
    public Result update(@RequestBody @ApiParam(name = "SpecificationOption对象",value = "传入JSON数据",required = false) SpecificationOption specificationOption, @PathVariable Long id){
        //设置主键值
        specificationOption.setId(id);
        //调用SpecificationOptionService实现修改SpecificationOption
        specificationOptionService.update(specificationOption);
        return new Result(true,StatusCode.OK,"修改成功");
    }

    /***
     * 新增SpecificationOption数据
     * @param specificationOption
     * @return
     */
    @ApiOperation(value = "SpecificationOption添加",notes = "添加SpecificationOption方法详情",tags = {"SpecificationOptionController"})
    @PostMapping
    public Result add(@RequestBody  @ApiParam(name = "SpecificationOption对象",value = "传入JSON数据",required = true) SpecificationOption specificationOption){
        //调用SpecificationOptionService实现添加SpecificationOption
        specificationOptionService.add(specificationOption);
        return new Result(true,StatusCode.OK,"添加成功");
    }

    /***
     * 根据ID查询SpecificationOption数据
     * @param id
     * @return
     */
    @ApiOperation(value = "SpecificationOption根据ID查询",notes = "根据ID查询SpecificationOption方法详情",tags = {"SpecificationOptionController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Long")
    @GetMapping("/{id}")
    public Result<SpecificationOption> findById(@PathVariable Long id){
        //调用SpecificationOptionService实现根据主键查询SpecificationOption
        SpecificationOption specificationOption = specificationOptionService.findById(id);
        return new Result<SpecificationOption>(true,StatusCode.OK,"查询成功",specificationOption);
    }

    /***
     * 查询SpecificationOption全部数据
     * @return
     */
    @ApiOperation(value = "查询所有SpecificationOption",notes = "查询所SpecificationOption有方法详情",tags = {"SpecificationOptionController"})
    @GetMapping
    public Result<List<SpecificationOption>> findAll(){
        //调用SpecificationOptionService实现查询所有SpecificationOption
        List<SpecificationOption> list = specificationOptionService.findAll();
        return new Result<List<SpecificationOption>>(true, StatusCode.OK,"查询成功",list) ;
    }
}
