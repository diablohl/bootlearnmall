package com.hl.bootlearnmall.service.imp;

import com.hl.bootlearnmall.Mapper.ProductMapper;
import com.hl.bootlearnmall.domain.Product;
import com.hl.bootlearnmall.exception.ImoocMallException;
import com.hl.bootlearnmall.exception.ImoocMallExceptionEnum;
import com.hl.bootlearnmall.request.AddProductReq;
import com.hl.bootlearnmall.service.ProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ProductServiceImpl implements ProductService {

    @Resource
    ProductMapper productMapper;

    @Override
    public void add(AddProductReq addProductReq) {
        Product product = new Product();
        BeanUtils.copyProperties(addProductReq, product);
        if (productMapper.selectByName(addProductReq.getName()) != null) {
            throw new ImoocMallException(ImoocMallExceptionEnum.NAME_EXISTED);
        }
        int i = productMapper.insertSelective(product);
        if (i == 0) {
            throw new ImoocMallException(ImoocMallExceptionEnum.CREATE_FAILED);
        }
    }
}
