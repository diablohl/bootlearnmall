package com.hl.bootlearnmall.service;

import com.github.pagehelper.PageInfo;
import com.hl.bootlearnmall.domain.Product;
import com.hl.bootlearnmall.request.AddProductReq;

public interface ProductService {

    void add(AddProductReq addProductReq);

    void update(Product updateProduct);

    void delete(Integer id);

    void batchUpdateSellStatus(Integer[] ids, Integer sellStatus);

    PageInfo listForAdmin(Integer pageNum, Integer pageSize);
}
