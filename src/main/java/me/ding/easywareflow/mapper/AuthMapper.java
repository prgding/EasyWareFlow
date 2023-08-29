package me.ding.easywareflow.mapper;

import me.ding.easywareflow.entity.Auth;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AuthMapper {
    //根据用户id查询用户所有权限(菜单)的方法
    List<Auth> findAllAuth(int userId);

}
