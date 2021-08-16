package com.ccw.controller;

import com.alibaba.fastjson.JSON;
import com.ccw.user.pojo.User;
import com.ccw.service.UserService;
import com.ccw.entity.PageResult;
import com.ccw.entity.Result;
import com.ccw.entity.StatusCode;
import com.ccw.utils.BCrypt;
import com.ccw.utils.JwtUtil;
import com.ccw.utils.PhoneFormatCheckUtils;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/****
 * @Author:ujiuye
 * @Description:
 * @Date 2021/2/1 14:19
 *****/
@Api(tags = "UserController")
@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    /***
     * User分页条件搜索实现
     * @param user
     * @param page
     * @param size
     * @return
     */
    @ApiOperation(value = "User条件分页查询",notes = "分页条件查询User方法详情",tags = {"UserController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true),
            @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true)
    })
    @PostMapping(value = "/search/{page}/{size}" )
    public Result<PageResult<User>> findPage(@RequestBody(required = false) @ApiParam(name = "User对象",value = "传入JSON数据",required = false) User user, @PathVariable  int page, @PathVariable  int size){
        //调用UserService实现分页条件查询User
        PageResult<User> pageResult = userService.findPage(user, page, size);
        return new Result(true,StatusCode.OK,"查询成功",pageResult);
    }

    /***
     * User分页搜索实现
     * @param page:当前页
     * @param size:每页显示多少条
     * @return
     */
    @ApiOperation(value = "User分页查询",notes = "分页查询User方法详情",tags = {"UserController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true),
            @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true)
    })
    @GetMapping(value = "/search/{page}/{size}" )
    public Result<PageResult<User>> findPage(@PathVariable  int page, @PathVariable  int size){
        //调用UserService实现分页查询User
        PageResult<User> pageResult = userService.findPage(page, size);
        return new Result<PageResult<User>>(true,StatusCode.OK,"查询成功",pageResult);
    }

    /***
     * 多条件搜索品牌数据
     * @param user
     * @return
     */
    @ApiOperation(value = "User条件查询",notes = "条件查询User方法详情",tags = {"UserController"})
    @PostMapping(value = "/search" )
    public Result<List<User>> findList(@RequestBody(required = false) @ApiParam(name = "User对象",value = "传入JSON数据",required = false) User user){
        //调用UserService实现条件查询User
        List<User> list = userService.findList(user);
        return new Result<List<User>>(true,StatusCode.OK,"查询成功",list);
    }

    /***
     * 根据ID删除品牌数据
     * @param id
     * @return
     */
    @ApiOperation(value = "User根据ID删除",notes = "根据ID删除User方法详情",tags = {"UserController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Long")
    @DeleteMapping(value = "/{id}" )
    public Result delete(@PathVariable Long id){
        //调用UserService实现根据主键删除
        userService.delete(id);
        return new Result(true,StatusCode.OK,"删除成功");
    }

    /***
     * 修改User数据
     * @param user
     * @param id
     * @return
     */
    @ApiOperation(value = "User根据ID修改",notes = "根据ID修改User方法详情",tags = {"UserController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Long")
    @PutMapping(value="/{id}")
    public Result update(@RequestBody @ApiParam(name = "User对象",value = "传入JSON数据",required = false) User user, @PathVariable Long id){
        //设置主键值
        user.setId(id);
        //调用UserService实现修改User
        userService.update(user);
        return new Result(true,StatusCode.OK,"修改成功");
    }

    /***
     * 新增User数据
     * @param user
     * @return
     */
    @ApiOperation(value = "User添加",notes = "添加User方法详情",tags = {"UserController"})
    @PostMapping("/{smsCode}")
    public Result add(@RequestBody  @ApiParam(name = "User对象",value = "传入JSON数据",required = true) User user,@PathVariable(value = "smsCode") String smsCode){

        if (!userService.checkedCode(user.getPhone(),smsCode)){
            return new Result(false,StatusCode.ERROR,"验证码不匹配，请重新输入！");
        }
        //调用UserService实现添加User
        userService.add(user);
        return new Result(true,StatusCode.OK,"添加成功");
    }

    /***
     * 根据ID查询User数据
     * @param id
     * @return
     */
    @ApiOperation(value = "User根据ID查询",notes = "根据ID查询User方法详情",tags = {"UserController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Long")
    @GetMapping("/{id}")
    public Result<User> findById(@PathVariable Long id){
        //调用UserService实现根据主键查询User
        User user = userService.findById(id);
        return new Result<User>(true,StatusCode.OK,"查询成功",user);
    }

    /***
     * 查询User全部数据
     * @return
     */
    @ApiOperation(value = "查询所有User",notes = "查询所User有方法详情",tags = {"UserController"})
    @GetMapping
    //添加权限的注解
    @PreAuthorize("hasAnyAuthority('admin')")
    public Result<List<User>> findAll(){
        //调用UserService实现查询所有User
        List<User> list = userService.findAll();
        return new Result<List<User>>(true, StatusCode.OK,"查询成功",list) ;
    }

    /*发送验证码*/
    @ApiOperation(value = "发送验证码",tags = {"UserController"})
    @GetMapping("/createCode")
    public Result createCode(String phoneNumber){
        if (!PhoneFormatCheckUtils.isPhoneLegal(phoneNumber)) {
            return new Result(false,StatusCode.ERROR,"输入手机格式有误");
        }
        userService.CreateSms(phoneNumber);
        return new Result(true,StatusCode.OK,"验证码已经成功发送");
    }

    /*根据用户名校验用户登录*/
    @ApiOperation(value = "用户登录验证",tags = {"UserController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "userName", value = "用户名", required = true),
            @ApiImplicitParam(paramType = "path", name = "password", value = "用户密码", required = true)
    })
    @GetMapping("load/findByUserName/{userName}/{password}")
    public Result findByUserName(@PathVariable(name = "userName") String userName , @PathVariable(name = "password") String password, HttpServletResponse response){

        //根据用户名获取用户信息
        User userPass = userService.findByUserName(userName);
        //判断用户密码是否正确
        if (userPass!=null && BCrypt.checkpw(password,userPass.getPassword())) {

            //生成jwt令牌
            Map map = new HashMap();
            map.put("role","USER");
            map.put("success","SUCCESS");
            map.put("username",userName);
            String jwtToken = JwtUtil.createJWT(JSON.toJSONString(UUID.randomUUID().toString()),JSON.toJSONString(map),null);

            //将令牌存入到cookies中

            Cookie cookie = new Cookie("Authorization",jwtToken);
            //Cookie cookie = new Cookie();
            //设置cookies的保存路径
            cookie.setPath("/");
            response.addCookie(cookie);

            return new Result(true,StatusCode.OK,"登录成功！",jwtToken);
        }
        return new Result(false,StatusCode.ERROR,"登录失败！");
    }

    @GetMapping("/load/findByUserName/{userName}")
    public Result<User> findByUserName(@PathVariable(name = "userName") String userName ){

        //根据用户名获取用户信息
        User user = userService.findByUserName(userName);

        return new Result<User>(true,StatusCode.OK,"登录成功！",user);

    }
}
