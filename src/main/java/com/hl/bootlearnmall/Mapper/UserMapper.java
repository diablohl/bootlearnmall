package com.hl.bootlearnmall.Mapper;

import com.hl.bootlearnmall.domain.User;
import com.hl.bootlearnmall.domain.UserExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    long countByExample(UserExample example);

    int deleteByExample(UserExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    List<User> selectByExample(UserExample example);

    User selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") User record, @Param("example") UserExample example);

    int updateByExample(@Param("record") User record, @Param("example") UserExample example);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    User selectByName(String username);
    //两个以上参数就要手动指定params了
    User selectLoginCheck(@Param("username") String username,@Param("password") String password);
}