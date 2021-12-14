package com.hl.bootlearnmall.service.imp;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hl.bootlearnmall.Mapper.CategoryMapper;
import com.hl.bootlearnmall.domain.Category;
import com.hl.bootlearnmall.exception.ImoocMallException;
import com.hl.bootlearnmall.exception.ImoocMallExceptionEnum;
import com.hl.bootlearnmall.request.AddCategoryReq;
import com.hl.bootlearnmall.request.UpdateCategoryReq;
import com.hl.bootlearnmall.service.CategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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

    @Override
    public void updateCategory(UpdateCategoryReq updateCategoryReq) {
        Category category = new Category();
        BeanUtils.copyProperties(updateCategoryReq,category);
        if (updateCategoryReq.getName() != null) {
            Category oldcategory = categoryMapper.selectByName(updateCategoryReq.getName());
            if (oldcategory != null && !updateCategoryReq.getId().equals(oldcategory.getId())) {
                throw new ImoocMallException(ImoocMallExceptionEnum.NAME_EXISTED);
            }
        }
        int i = categoryMapper.updateByPrimaryKeySelective(category);
        if (i != 1) {
            throw new ImoocMallException(ImoocMallExceptionEnum.UPDATE_FAILD);
        }
    }

    @Override
    public void delete(Integer id) {

        if (categoryMapper.selectByPrimaryKey(id)==null) {
            throw new ImoocMallException(ImoocMallExceptionEnum.DELETE_FAILED);
        }
        int i = categoryMapper.deleteByPrimaryKey(id);
        if (i != 1) {
            throw new ImoocMallException(ImoocMallExceptionEnum.DELETE_FAILED);
        }
    }

    @Override
    public PageInfo listForAdmin(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize, "type,order_num");
        List<Category> categoryList = categoryMapper.selectList();
        PageInfo pageInfo=new PageInfo(categoryList);
        return pageInfo;

    }


}
