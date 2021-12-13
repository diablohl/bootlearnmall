package com.hl.bootlearnmall.controller;

import com.hl.bootlearnmall.common.ApiRestResponse;
import com.hl.bootlearnmall.common.Constant;
import com.hl.bootlearnmall.domain.User;
import com.hl.bootlearnmall.exception.ImoocMallExceptionEnum;
import com.hl.bootlearnmall.request.AddCategoryReq;
import com.hl.bootlearnmall.service.CategoryService;
import com.hl.bootlearnmall.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Controller
public class CategoryController {

    @Resource
    UserService userService;
    @Resource
    CategoryService categoryService;

    @PostMapping("/category/add")
    @ResponseBody
    public ApiRestResponse addCategory(HttpSession session, @RequestBody AddCategoryReq addCategoryReq){
        //入参校验
        if (addCategoryReq.getName() == null||addCategoryReq.getOrderNum()==null
                ||addCategoryReq.getParentId()==null||addCategoryReq.getType()==null) {
            return ApiRestResponse.error(ImoocMallExceptionEnum.PARA_NOT_NULL);
        }
        //检验登陆状态与权限
        User currUser = (User) session.getAttribute(Constant.IMOOC_MALL_USER);
        if (currUser == null) {
            return ApiRestResponse.error(ImoocMallExceptionEnum.NEED_LOGIN);
        }
        if (userService.checkAdminRole(currUser)) {
            categoryService.addCategory(addCategoryReq);
            return ApiRestResponse.success();
        } else {
            return ApiRestResponse.error(ImoocMallExceptionEnum.NEED_ADMIN);
        }
    }
}
