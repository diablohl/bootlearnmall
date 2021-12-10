package com.hl.bootlearnmall.controller;

import com.hl.bootlearnmall.common.ApiRestResponse;
import com.hl.bootlearnmall.common.Constant;
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
import javax.servlet.http.HttpSession;

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
    public ApiRestResponse register(@RequestParam String username,@RequestParam String password){
        if (!StringUtils.hasText(username)) return ApiRestResponse.error(ImoocMallExceptionEnum.NEED_USER_NAME);
        if (!StringUtils.hasText(password)) return ApiRestResponse.error(ImoocMallExceptionEnum.NEED_PASSWORD);
        if (password.length()<8) return ApiRestResponse.error(ImoocMallExceptionEnum.NEED_TOO_SHORT);
        userService.register(username, password);
        return ApiRestResponse.success();
    }

    /**
     * 用户登录，并用session保持登陆状态
     * @param username
     * @param password
     * @param session
     * @return
     */
    @PostMapping("/login")
    @ResponseBody
    public ApiRestResponse login(@RequestParam("username")String username,
                                 @RequestParam("password")String password, HttpSession session){
        if (!StringUtils.hasText(username)) return ApiRestResponse.error(ImoocMallExceptionEnum.NEED_USER_NAME);
        if (!StringUtils.hasText(password)) return ApiRestResponse.error(ImoocMallExceptionEnum.NEED_PASSWORD);
        User login = userService.login(username, password);
        //保存到session
        session.setAttribute(Constant.IMOOC_MALL_USER,login);
        return ApiRestResponse.success(login);
    }

    /**
     * 更新个人签名
     * @param session
     * @param signature
     * @return
     */
    @PostMapping("/user/update")
    @ResponseBody
    public ApiRestResponse updataUserInfo(HttpSession session,@RequestParam String signature){

        User currUser = (User)session.getAttribute(Constant.IMOOC_MALL_USER);
        if (currUser == null) {
            return ApiRestResponse.error(ImoocMallExceptionEnum.NEED_LOGIN);
        }
        User user = new User();
        user.setId(currUser.getId());
        user.setPersonalizedSignature(signature);
        userService.updateUserInformation(user);
        return ApiRestResponse.success();
    }

    @PostMapping("/logout")
    @ResponseBody
    public ApiRestResponse logout(HttpSession session){

        session.removeAttribute(Constant.IMOOC_MALL_USER);
        return ApiRestResponse.success();
    }

    @PostMapping("/adminlogin")
    @ResponseBody
    public ApiRestResponse adminlogin(@RequestParam("username")String username,
                                 @RequestParam("password")String password, HttpSession session){
        if (!StringUtils.hasText(username)) return ApiRestResponse.error(ImoocMallExceptionEnum.NEED_USER_NAME);
        if (!StringUtils.hasText(password)) return ApiRestResponse.error(ImoocMallExceptionEnum.NEED_PASSWORD);
        User login = userService.login(username, password);
        if (userService.checkAdminRole(login)) {
            //保存到session
            session.setAttribute(Constant.IMOOC_MALL_USER, login);
            return ApiRestResponse.success(login);
        } else {
            return ApiRestResponse.error(ImoocMallExceptionEnum.NEED_ADMIN);
        }

    }
}
