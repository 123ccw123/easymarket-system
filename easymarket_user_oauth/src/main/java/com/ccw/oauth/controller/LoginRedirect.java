package com.ccw.oauth.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
//restcontroller 跳转页面以后返回数据，controller不会返回数据
@RequestMapping("/oauth")
public class LoginRedirect {

    @GetMapping("/login")
    public String login(@RequestParam(name = "FROM", required = false) String formUrl, Model model){
        model.addAttribute("from",formUrl);
        return "login";
    }
}
