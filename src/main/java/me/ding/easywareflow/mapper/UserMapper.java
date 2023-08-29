package me.ding.easywareflow.mapper;


import me.ding.easywareflow.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    //根据用户名查找用户的方法
    User findUserByCode(String userCode);
}
