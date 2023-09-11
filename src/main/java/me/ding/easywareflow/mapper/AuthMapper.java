package me.ding.easywareflow.mapper;

import me.ding.easywareflow.entity.Auth;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AuthMapper {
    //根据用户id查询用户所有权限(菜单)的方法
    List<Auth> findAllAuth(int userId);
    //查询所有权限(菜单)
    List<Auth> getAllAuth();
    //根据角色id删除给角色已分配的所有权限(菜单)
    void delAuthByRoleId(Integer roleId);
    //添加角色权限(菜单)关系的方法
    void insertRoleAuth(Integer roleId, Integer authId);

    Auth findByName(String authName);

    void insertAuth(Auth auth);

    void updateNameById(Auth auth);

    int updateAuthState(Auth auth);

    int deleteAuthById(Integer authId);

    void deleteAuthFromRole(Integer authId);
}
