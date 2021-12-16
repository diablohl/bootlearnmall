package com.hl.bootlearnmall.controller;

import com.hl.bootlearnmall.common.ApiRestResponse;
import com.hl.bootlearnmall.request.AddProductReq;
import com.hl.bootlearnmall.service.ProductService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
public class ProductAdminController {

    @Resource
    ProductService productService;

    @PostMapping("admin/product/add")
    public ApiRestResponse addProduct(@Valid @RequestBody AddProductReq addProductReq) {
        productService.add(addProductReq);
        return ApiRestResponse.success();
    }
}
