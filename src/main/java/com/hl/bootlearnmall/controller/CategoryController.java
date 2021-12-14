package com.hl.bootlearnmall.controller;

import com.github.pagehelper.PageInfo;
import com.hl.bootlearnmall.common.ApiRestResponse;
import com.hl.bootlearnmall.common.Constant;
import com.hl.bootlearnmall.domain.User;
import com.hl.bootlearnmall.exception.ImoocMallExceptionEnum;
import com.hl.bootlearnmall.request.AddCategoryReq;
import com.hl.bootlearnmall.request.UpdateCategoryReq;
import com.hl.bootlearnmall.service.CategoryService;
import com.hl.bootlearnmall.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class CategoryController {

    @Resource
    UserService userService;
    @Resource
    CategoryService categoryService;

    @ApiOperation("后台添加目录")
    @PostMapping("admin/category/add")
    @ResponseBody
    public ApiRestResponse addCategory(HttpSession session, @Valid @RequestBody AddCategoryReq addCategoryReq){
        //入参校验  //更新：使用注解方式
//        if (addCategoryReq.getName() == null||addCategoryReq.getOrderNum()==null
//                ||addCategoryReq.getParentId()==null||addCategoryReq.getType()==null) {
//            return ApiRestResponse.error(ImoocMallExceptionEnum.PARA_NOT_NULL);
//        }
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

    /**
     * 更新目录接口
     * @param session
     * @param updateCategoryReq
     * @return
     */
    @PostMapping("admin/category/update")
    @ResponseBody
    public ApiRestResponse updateCategory(HttpSession session, @Valid @RequestBody UpdateCategoryReq updateCategoryReq){

        //检验登陆状态与权限
        User currUser = (User) session.getAttribute(Constant.IMOOC_MALL_USER);
        if (currUser == null) {
            return ApiRestResponse.error(ImoocMallExceptionEnum.NEED_LOGIN);
        }
        if (userService.checkAdminRole(currUser)) {
            categoryService.updateCategory(updateCategoryReq);
            return ApiRestResponse.success();
        } else {
            return ApiRestResponse.error(ImoocMallExceptionEnum.NEED_ADMIN);
        }
    }

    @PostMapping("admin/category/delete")
    @ResponseBody
    public ApiRestResponse deleteCategory(@RequestParam Integer id){

        categoryService.delete(id);
        return ApiRestResponse.success();
    }

    @ApiOperation("后台目录列表")
    @PostMapping("admin/category/list")
    @ResponseBody
    public ApiRestResponse listCategoryForAdmin(@RequestParam Integer pageNum, @RequestParam Integer pageSize){
        PageInfo pageInfo = categoryService.listForAdmin(pageNum, pageSize);
        return ApiRestResponse.success(pageInfo);
    }

}
