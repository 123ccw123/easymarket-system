package com.ccw.user.feign;

import com.ccw.entity.Result;
import com.ccw.user.pojo.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "USER")
public interface UserFeign {
    @GetMapping("/user/load/findByUserName/{userName}")
    public Result<User> findByUserName(@PathVariable(name = "userName") String userName );
}
