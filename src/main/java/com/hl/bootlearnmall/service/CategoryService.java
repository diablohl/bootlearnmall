package com.hl.bootlearnmall.service;

import com.github.pagehelper.PageInfo;
import com.hl.bootlearnmall.request.AddCategoryReq;
import com.hl.bootlearnmall.request.UpdateCategoryReq;

public interface CategoryService {
    void addCategory(AddCategoryReq addCategoryReq);

    void updateCategory(UpdateCategoryReq updateCategoryReq);

    void delete(Integer id);

    PageInfo listForAdmin(Integer pageNum, Integer pageSize);

}
