package me.ding.easywareflow.mapper;


import me.ding.easywareflow.entity.Page;
import me.ding.easywareflow.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {

    //根据用户名查找用户的方法
    User findUserByCode(String userCode);

    //查询用户总行数的方法
    int selectUserCount(User user);

    //分页查询用户的方法
    List<User> selectUserPage(@Param("page") Page page, @Param("user") User user);
}
