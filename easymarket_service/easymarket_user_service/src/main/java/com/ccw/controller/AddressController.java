package com.ccw.controller;

import com.ccw.user.pojo.Address;
import com.ccw.service.AddressService;
import com.ccw.entity.PageResult;
import com.ccw.entity.Result;
import com.ccw.entity.StatusCode;
import com.ccw.utils.TokenDecode;
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
@Api(tags = "AddressController")
@RestController
@RequestMapping("/address")
@CrossOrigin
public class AddressController {

    @Autowired
    private AddressService addressService;

    @Autowired
    private TokenDecode tokenDecode;

    /***
     * Address分页条件搜索实现
     * @param address
     * @param page
     * @param size
     * @return
     */
    @ApiOperation(value = "Address条件分页查询",notes = "分页条件查询Address方法详情",tags = {"AddressController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true),
            @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true)
    })
    @PostMapping(value = "/search/{page}/{size}" )
    public Result<PageResult<Address>> findPage(@RequestBody(required = false) @ApiParam(name = "Address对象",value = "传入JSON数据",required = false) Address address, @PathVariable  int page, @PathVariable  int size){
        //调用AddressService实现分页条件查询Address
        PageResult<Address> pageResult = addressService.findPage(address, page, size);
        return new Result(true,StatusCode.OK,"查询成功",pageResult);
    }

    /***
     * Address分页搜索实现
     * @param page:当前页
     * @param size:每页显示多少条
     * @return
     */
    @ApiOperation(value = "Address分页查询",notes = "分页查询Address方法详情",tags = {"AddressController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true),
            @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true)
    })
    @GetMapping(value = "/search/{page}/{size}" )
    public Result<PageResult<Address>> findPage(@PathVariable  int page, @PathVariable  int size){
        //调用AddressService实现分页查询Address
        PageResult<Address> pageResult = addressService.findPage(page, size);
        return new Result<PageResult<Address>>(true,StatusCode.OK,"查询成功",pageResult);
    }

    /***
     * 多条件搜索品牌数据
     * @param address
     * @return
     */
    @ApiOperation(value = "Address条件查询",notes = "条件查询Address方法详情",tags = {"AddressController"})
    @PostMapping(value = "/search" )
    public Result<List<Address>> findList(@RequestBody(required = false) @ApiParam(name = "Address对象",value = "传入JSON数据",required = false) Address address){
        //调用AddressService实现条件查询Address
        List<Address> list = addressService.findList(address);
        return new Result<List<Address>>(true,StatusCode.OK,"查询成功",list);
    }

    /***
     * 根据ID删除品牌数据
     * @param id
     * @return
     */
    @ApiOperation(value = "Address根据ID删除",notes = "根据ID删除Address方法详情",tags = {"AddressController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Long")
    @DeleteMapping(value = "/{id}" )
    public Result delete(@PathVariable Long id){
        //调用AddressService实现根据主键删除
        addressService.delete(id);
        return new Result(true,StatusCode.OK,"删除成功");
    }

    /***
     * 修改Address数据
     * @param address
     * @param id
     * @return
     */
    @ApiOperation(value = "Address根据ID修改",notes = "根据ID修改Address方法详情",tags = {"AddressController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Long")
    @PutMapping(value="/{id}")
    public Result update(@RequestBody @ApiParam(name = "Address对象",value = "传入JSON数据",required = false) Address address, @PathVariable Long id){
        //设置主键值
        address.setId(id);
        //调用AddressService实现修改Address
        addressService.update(address);
        return new Result(true,StatusCode.OK,"修改成功");
    }

    /***
     * 新增Address数据
     * @param address
     * @return
     */
    @ApiOperation(value = "Address添加",notes = "添加Address方法详情",tags = {"AddressController"})
    @PostMapping
    public Result add(@RequestBody  @ApiParam(name = "Address对象",value = "传入JSON数据",required = true) Address address){
        //调用AddressService实现添加Address
        addressService.add(address);
        return new Result(true,StatusCode.OK,"添加成功");
    }

    /***
     * 根据ID查询Address数据
     * @param id
     * @return
     */
    @ApiOperation(value = "Address根据ID查询",notes = "根据ID查询Address方法详情",tags = {"AddressController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Long")
    @GetMapping("/{id}")
    public Result<Address> findById(@PathVariable Long id){
        //调用AddressService实现根据主键查询Address
        Address address = addressService.findById(id);
        return new Result<Address>(true,StatusCode.OK,"查询成功",address);
    }

    /***
     * 查询Address全部数据
     * @return
     */
    @ApiOperation(value = "查询所有Address",notes = "查询所Address有方法详情",tags = {"AddressController"})
    @GetMapping
    public Result<List<Address>> findAll(){
        //调用AddressService实现查询所有Address
        List<Address> list = addressService.findAll();
        return new Result<List<Address>>(true, StatusCode.OK,"查询成功",list) ;
    }

    /*根据用户名字查询用户收货地址*/
    @GetMapping("/findListByUsername")
    public Result<List<Address>> findListByUsername(){
        Map<String, String> userInfo = tokenDecode.getUserInfo();
        String username = userInfo.get("username");
        List<Address> listByUsername = addressService.findListByUsername(username);
        return new Result<>(true,StatusCode.OK,"获取用户地址成功！",listByUsername);
    }
}
