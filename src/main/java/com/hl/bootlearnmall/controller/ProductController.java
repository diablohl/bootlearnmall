package com.hl.bootlearnmall.controller;

import com.hl.bootlearnmall.common.ApiRestResponse;
import com.hl.bootlearnmall.domain.Product;
import com.hl.bootlearnmall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    @Autowired
    ProductService productService;


    @GetMapping("product/detail")
    public ApiRestResponse detail(@RequestParam Integer id){
        Product product = productService.detail(id);
        return ApiRestResponse.success(product);
    }
}
