package com.ccw.seckill.controller;

import com.ccw.seckill.entity.SeckillStatus;
import com.ccw.seckill.pojo.SeckillOrder;
import com.ccw.seckill.service.SeckillOrderService;
import com.ccw.entity.PageResult;
import com.ccw.entity.Result;
import com.ccw.entity.StatusCode;
import com.ccw.utils.TokenDecode;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/****
 * @Author:ujiuye
 * @Description:
 * @Date 2021/2/1 14:19
 *****/
@Api(tags = "SeckillOrderController")
@RestController
@RequestMapping("/seckillOrder")
@CrossOrigin
public class SeckillOrderController {

    @Autowired
    private SeckillOrderService seckillOrderService;

    @Autowired
    private TokenDecode tokenDecode;
    /***
     * SeckillOrder分页条件搜索实现
     * @param seckillOrder
     * @param page
     * @param size
     * @return
     */
    @ApiOperation(value = "SeckillOrder条件分页查询",notes = "分页条件查询SeckillOrder方法详情",tags = {"SeckillOrderController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true),
            @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true)
    })
    @PostMapping(value = "/search/{page}/{size}" )
    public Result<PageResult<SeckillOrder>> findPage(@RequestBody(required = false) @ApiParam(name = "SeckillOrder对象",value = "传入JSON数据",required = false) SeckillOrder seckillOrder, @PathVariable  int page, @PathVariable  int size){
        //调用SeckillOrderService实现分页条件查询SeckillOrder
        PageResult<SeckillOrder> pageResult = seckillOrderService.findPage(seckillOrder, page, size);
        return new Result(true,StatusCode.OK,"查询成功",pageResult);
    }

    /***
     * SeckillOrder分页搜索实现
     * @param page:当前页
     * @param size:每页显示多少条
     * @return
     */
    @ApiOperation(value = "SeckillOrder分页查询",notes = "分页查询SeckillOrder方法详情",tags = {"SeckillOrderController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true),
            @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true)
    })
    @GetMapping(value = "/search/{page}/{size}" )
    public Result<PageResult<SeckillOrder>> findPage(@PathVariable  int page, @PathVariable  int size){
        //调用SeckillOrderService实现分页查询SeckillOrder
        PageResult<SeckillOrder> pageResult = seckillOrderService.findPage(page, size);
        return new Result<PageResult<SeckillOrder>>(true,StatusCode.OK,"查询成功",pageResult);
    }

    /***
     * 多条件搜索品牌数据
     * @param seckillOrder
     * @return
     */
    @ApiOperation(value = "SeckillOrder条件查询",notes = "条件查询SeckillOrder方法详情",tags = {"SeckillOrderController"})
    @PostMapping(value = "/search" )
    public Result<List<SeckillOrder>> findList(@RequestBody(required = false) @ApiParam(name = "SeckillOrder对象",value = "传入JSON数据",required = false) SeckillOrder seckillOrder){
        //调用SeckillOrderService实现条件查询SeckillOrder
        List<SeckillOrder> list = seckillOrderService.findList(seckillOrder);
        return new Result<List<SeckillOrder>>(true,StatusCode.OK,"查询成功",list);
    }

    /***
     * 根据ID删除品牌数据
     * @param id
     * @return
     */
    @ApiOperation(value = "SeckillOrder根据ID删除",notes = "根据ID删除SeckillOrder方法详情",tags = {"SeckillOrderController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Long")
    @DeleteMapping(value = "/{id}" )
    public Result delete(@PathVariable Long id){
        //调用SeckillOrderService实现根据主键删除
        seckillOrderService.delete(id);
        return new Result(true,StatusCode.OK,"删除成功");
    }

    /***
     * 修改SeckillOrder数据
     * @param seckillOrder
     * @param id
     * @return
     */
    @ApiOperation(value = "SeckillOrder根据ID修改",notes = "根据ID修改SeckillOrder方法详情",tags = {"SeckillOrderController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Long")
    @PutMapping(value="/{id}")
    public Result update(@RequestBody @ApiParam(name = "SeckillOrder对象",value = "传入JSON数据",required = false) SeckillOrder seckillOrder, @PathVariable Long id){
        //设置主键值
        seckillOrder.setId(id);
        //调用SeckillOrderService实现修改SeckillOrder
        seckillOrderService.update(seckillOrder);
        return new Result(true,StatusCode.OK,"修改成功");
    }

    /***
     * 新增SeckillOrder数据
     * @param seckillOrder
     * @return
     */
    @ApiOperation(value = "SeckillOrder添加",notes = "添加SeckillOrder方法详情",tags = {"SeckillOrderController"})
    @PostMapping
    public Result add(@RequestBody  @ApiParam(name = "SeckillOrder对象",value = "传入JSON数据",required = true) SeckillOrder seckillOrder){
        //调用SeckillOrderService实现添加SeckillOrder
        seckillOrderService.add(seckillOrder);
        return new Result(true,StatusCode.OK,"添加成功");
    }

    /***
     * 根据ID查询SeckillOrder数据
     * @param id
     * @return
     */
    @ApiOperation(value = "SeckillOrder根据ID查询",notes = "根据ID查询SeckillOrder方法详情",tags = {"SeckillOrderController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Long")
    @GetMapping("/{id}")
    public Result<SeckillOrder> findById(@PathVariable Long id){
        //调用SeckillOrderService实现根据主键查询SeckillOrder
        SeckillOrder seckillOrder = seckillOrderService.findById(id);
        return new Result<SeckillOrder>(true,StatusCode.OK,"查询成功",seckillOrder);
    }

    /***
     * 查询SeckillOrder全部数据
     * @return
     */
    @ApiOperation(value = "查询所有SeckillOrder",notes = "查询所SeckillOrder有方法详情",tags = {"SeckillOrderController"})
    @GetMapping
    public Result<List<SeckillOrder>> findAll(){
        //调用SeckillOrderService实现查询所有SeckillOrder
        List<SeckillOrder> list = seckillOrderService.findAll();
        return new Result<List<SeckillOrder>>(true, StatusCode.OK,"查询成功",list) ;
    }

    @PostMapping("/add")
    public boolean add(Long id,String timeKey){
        //String username="ujiuye";
        String username = tokenDecode.getUserInfo().get("username");
        try {
            return seckillOrderService.add(id,timeKey,username);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @GetMapping("/queryStatus")
    public Result queryStatus(){
        //String username = "ujiuye";
        String username = tokenDecode.getUserInfo().get("username");
        SeckillStatus seckillStatus = seckillOrderService.queryStatusFromRedis(username);
        return new Result(true,StatusCode.OK,"订单状态成功！",seckillStatus);
    }
}
