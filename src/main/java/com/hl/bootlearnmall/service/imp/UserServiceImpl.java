package com.hl.bootlearnmall.service.imp;

import com.hl.bootlearnmall.Mapper.UserMapper;
import com.hl.bootlearnmall.domain.User;
import com.hl.bootlearnmall.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
@Service
public class UserServiceImpl implements UserService {
    @Resource
    UserMapper userMapper;
    @Override
    public User getUser() {
        return userMapper.selectByPrimaryKey(1);
    }
}
