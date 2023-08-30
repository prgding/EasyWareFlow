package me.ding.easywareflow.mapper;

import me.ding.easywareflow.entity.Page;
import me.ding.easywareflow.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RoleMapper {
    //查询状态正常的所有角色的方法
    List<Role> findAllRole();

    List<Role> findRolesByUserId(Integer userId);

    //根据用户id删除给用户已分配的所有角色
    int delRoleByUserId(Integer userId);

    //根据角色名称查询角色id
    int getRoleIdByName(String roleName);

    //添加用户角色关系的方法
    void insertUserRole(Integer userId, Integer roleId);

    int selectRoleCount(Role role);

    List<Role> selectRolePage(@Param("page") Page page, @Param("role") Role role);

    Role findRoleByNameOrCode(String roleName, String roleCode);

    void insertRole(Role role);

    int updateRoleState(Role role);
}
