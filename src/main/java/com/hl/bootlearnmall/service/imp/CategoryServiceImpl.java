package com.hl.bootlearnmall.service.imp;

import com.hl.bootlearnmall.Mapper.CategoryMapper;
import com.hl.bootlearnmall.domain.Category;
import com.hl.bootlearnmall.exception.ImoocMallException;
import com.hl.bootlearnmall.exception.ImoocMallExceptionEnum;
import com.hl.bootlearnmall.request.AddCategoryReq;
import com.hl.bootlearnmall.service.CategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
@Service
public class CategoryServiceImpl implements CategoryService {

    @Resource
    CategoryMapper categoryMapper;

    @Override
    public void addCategory(AddCategoryReq addCategoryReq) {
        Category category = new Category();
        BeanUtils.copyProperties(addCategoryReq,category);
        if (categoryMapper.selectByName(addCategoryReq.getName()) != null) {
            throw new ImoocMallException(ImoocMallExceptionEnum.NAME_EXISTED);
        }
        int i = categoryMapper.insertSelective(category);
        if (i != 1) {
            throw new ImoocMallException(ImoocMallExceptionEnum.CREATE_FAILED);
        }
    }
}
