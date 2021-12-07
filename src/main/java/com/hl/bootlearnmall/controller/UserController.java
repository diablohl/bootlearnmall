package com.hl.bootlearnmall.controller;

import com.hl.bootlearnmall.domain.User;
import com.hl.bootlearnmall.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
public class UserController {
    @Resource
    UserService userService;

    @GetMapping("/usertest")
    @ResponseBody
    public User personalPage(){
        return userService.getUser();
    }
}
