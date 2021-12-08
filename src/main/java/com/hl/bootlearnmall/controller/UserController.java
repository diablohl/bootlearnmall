package com.hl.bootlearnmall.controller;

import com.hl.bootlearnmall.common.ApiRestResponse;
import com.hl.bootlearnmall.domain.User;
import com.hl.bootlearnmall.exception.ImoocMallExceptionEnum;
import com.hl.bootlearnmall.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @PostMapping("/register")
    @ResponseBody
    public ApiRestResponse register(@RequestParam("username")String username,@RequestParam("password")String password){
        if (!StringUtils.hasText(username)) return ApiRestResponse.error(ImoocMallExceptionEnum.NEED_USER_NAME);
        if (!StringUtils.hasText(password)) return ApiRestResponse.error(ImoocMallExceptionEnum.NEED_PASSWORD);
        if (password.length()<8) return ApiRestResponse.error(ImoocMallExceptionEnum.NEED_TOO_SHORT);
        userService.register(username, password);
        return ApiRestResponse.success();
    }
}
