package com.ccw.sellersgoods.controller;

import com.ccw.sellersgoods.pojo.TypeTemplate;
import com.ccw.sellersgoods.service.TypeTemplateService;
import com.ccw.entity.PageResult;
import com.ccw.entity.Result;
import com.ccw.entity.StatusCode;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/****
 * @Author:ujiuye
 * @Description:
 * @Date 2021/2/1 14:19
 *****/
@Api(tags = "TypeTemplateController")
@RestController
@RequestMapping("/typeTemplate")
@CrossOrigin
public class TypeTemplateController {

    @Autowired
    private TypeTemplateService typeTemplateService;

    /***
     * TypeTemplate分页条件搜索实现
     * @param typeTemplate
     * @param page
     * @param size
     * @return
     */
    @ApiOperation(value = "TypeTemplate条件分页查询",notes = "分页条件查询TypeTemplate方法详情",tags = {"TypeTemplateController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true),
            @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true)
    })
    @PostMapping(value = "/search/{page}/{size}" )
    public Result<PageResult<TypeTemplate>> findPage(@RequestBody(required = false) @ApiParam(name = "TypeTemplate对象",value = "传入JSON数据",required = false) TypeTemplate typeTemplate, @PathVariable  int page, @PathVariable  int size){
        //调用TypeTemplateService实现分页条件查询TypeTemplate
        PageResult<TypeTemplate> pageResult = typeTemplateService.findPage(typeTemplate, page, size);
        return new Result(true,StatusCode.OK,"查询成功",pageResult);
    }

    /***
     * TypeTemplate分页搜索实现
     * @param page:当前页
     * @param size:每页显示多少条
     * @return
     */
    @ApiOperation(value = "TypeTemplate分页查询",notes = "分页查询TypeTemplate方法详情",tags = {"TypeTemplateController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true),
            @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true)
    })
    @GetMapping(value = "/search/{page}/{size}" )
    public Result<PageResult<TypeTemplate>> findPage(@PathVariable  int page, @PathVariable  int size){
        //调用TypeTemplateService实现分页查询TypeTemplate
        PageResult<TypeTemplate> pageResult = typeTemplateService.findPage(page, size);
        return new Result<PageResult<TypeTemplate>>(true,StatusCode.OK,"查询成功",pageResult);
    }

    /***
     * 多条件搜索品牌数据
     * @param typeTemplate
     * @return
     */
    @ApiOperation(value = "TypeTemplate条件查询",notes = "条件查询TypeTemplate方法详情",tags = {"TypeTemplateController"})
    @PostMapping(value = "/search" )
    public Result<List<TypeTemplate>> findList(@RequestBody(required = false) @ApiParam(name = "TypeTemplate对象",value = "传入JSON数据",required = false) TypeTemplate typeTemplate){
        //调用TypeTemplateService实现条件查询TypeTemplate
        List<TypeTemplate> list = typeTemplateService.findList(typeTemplate);
        return new Result<List<TypeTemplate>>(true,StatusCode.OK,"查询成功",list);
    }

    /***
     * 根据ID删除品牌数据
     * @param id
     * @return
     */
    @ApiOperation(value = "TypeTemplate根据ID删除",notes = "根据ID删除TypeTemplate方法详情",tags = {"TypeTemplateController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Long")
    @DeleteMapping(value = "/{id}" )
    public Result delete(@PathVariable Long id){
        //调用TypeTemplateService实现根据主键删除
        typeTemplateService.delete(id);
        return new Result(true,StatusCode.OK,"删除成功");
    }

    /***
     * 修改TypeTemplate数据
     * @param typeTemplate
     * @param id
     * @return
     */
    @ApiOperation(value = "TypeTemplate根据ID修改",notes = "根据ID修改TypeTemplate方法详情",tags = {"TypeTemplateController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Long")
    @PutMapping(value="/{id}")
    public Result update(@RequestBody @ApiParam(name = "TypeTemplate对象",value = "传入JSON数据",required = false) TypeTemplate typeTemplate, @PathVariable Long id){
        //设置主键值
        typeTemplate.setId(id);
        //调用TypeTemplateService实现修改TypeTemplate
        typeTemplateService.update(typeTemplate);
        return new Result(true,StatusCode.OK,"修改成功");
    }

    /***
     * 新增TypeTemplate数据
     * @param typeTemplate
     * @return
     */
    @ApiOperation(value = "TypeTemplate添加",notes = "添加TypeTemplate方法详情",tags = {"TypeTemplateController"})
    @PostMapping
    public Result add(@RequestBody  @ApiParam(name = "TypeTemplate对象",value = "传入JSON数据",required = true) TypeTemplate typeTemplate){
        //调用TypeTemplateService实现添加TypeTemplate
        typeTemplateService.add(typeTemplate);
        return new Result(true,StatusCode.OK,"添加成功");
    }

    /***
     * 根据ID查询TypeTemplate数据
     * @param id
     * @return
     */
    @ApiOperation(value = "TypeTemplate根据ID查询",notes = "根据ID查询TypeTemplate方法详情",tags = {"TypeTemplateController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Long")
    @GetMapping("/{id}")
    public Result<TypeTemplate> findById(@PathVariable Long id){
        //调用TypeTemplateService实现根据主键查询TypeTemplate
        TypeTemplate typeTemplate = typeTemplateService.findById(id);
        return new Result<TypeTemplate>(true,StatusCode.OK,"查询成功",typeTemplate);
    }

    /***
     * 查询TypeTemplate全部数据
     * @return
     */
    @ApiOperation(value = "查询所有TypeTemplate",notes = "查询所TypeTemplate有方法详情",tags = {"TypeTemplateController"})
    @GetMapping
    public Result<List<TypeTemplate>> findAll(){
        //调用TypeTemplateService实现查询所有TypeTemplate
        List<TypeTemplate> list = typeTemplateService.findAll();
        return new Result<List<TypeTemplate>>(true, StatusCode.OK,"查询成功",list) ;
    }

    /*根据模板id查询模板规格选项*/
    @ApiOperation(value = "根据模板id查询规格选项",notes = "查询所TypeTemplate有方法详情",tags = {"TypeTemplateController"})
    @GetMapping("/findSpecList")
    public Result<List<Map>> findSpecList(Long id){
        List<Map> mapList = typeTemplateService.finSpecList(id);
        return new Result<List<Map>>(true, StatusCode.OK,"查询成功",mapList) ;
    }
}
