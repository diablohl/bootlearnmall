package com.hl.bootlearnmall.service.imp;

import com.hl.bootlearnmall.Mapper.UserMapper;
import com.hl.bootlearnmall.domain.User;
import com.hl.bootlearnmall.exception.ImoocMallException;
import com.hl.bootlearnmall.exception.ImoocMallExceptionEnum;
import com.hl.bootlearnmall.service.UserService;
import com.hl.bootlearnmall.utils.MD5Utils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.security.NoSuchAlgorithmException;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    UserMapper userMapper;
    @Override
    public User getUser() {
        return userMapper.selectByPrimaryKey(1);
    }

    @Override
    public void register(String username, String password) {
        //重名校验
        User user=userMapper.selectByName(username);
        if (user != null) {
            throw new ImoocMallException(ImoocMallExceptionEnum.NAME_EXISTED);
        }
        User goodUser = new User();
        goodUser.setUsername(username);
        try {
            goodUser.setPassword(MD5Utils.getMD5Str(password));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        int count=userMapper.insertSelective(goodUser);
        if (count == 0) {
            throw new ImoocMallException(ImoocMallExceptionEnum.INSERT_FAILED);
        }
    }
}
