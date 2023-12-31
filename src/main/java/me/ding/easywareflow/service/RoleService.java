package me.ding.easywareflow.service;

import me.ding.easywareflow.dto.AssignRoleDto;
import me.ding.easywareflow.entity.Page;
import me.ding.easywareflow.entity.Result;
import me.ding.easywareflow.entity.Role;

import java.util.List;

public interface RoleService {
    //查询所有角色的业务方法
    List<Role> getAllRole();

    List<Role> queryRolesByUserId(Integer userId);

    //给用户分配角色的业务方法
    void assignRole(AssignRoleDto assignRoleDto);

    Page queryRolePage(Page page, Role role);

    Result saveRole(Role role);

    Result updateRoleState(Role role);

    List<Integer> queryAuthIds(Integer roleId);

    void deleteRole(Integer roleId);

    Result updateRoleDesc(Role role);
}
