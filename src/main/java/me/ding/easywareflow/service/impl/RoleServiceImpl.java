package me.ding.easywareflow.service.impl;

import me.ding.easywareflow.dto.AssignRoleDto;
import me.ding.easywareflow.entity.Page;
import me.ding.easywareflow.entity.Role;
import me.ding.easywareflow.mapper.RoleMapper;
import me.ding.easywareflow.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<Role> getAllRole() {
        //查询状态正常的所有角色
        return roleMapper.findAllRole();
    }

    @Override
    public List<Role> queryRolesByUserId(Integer userId) {
        return roleMapper.findRolesByUserId(userId);
    }

    @Transactional
    @Override
    public void assignRole(AssignRoleDto assignRoleDto) {
        //拿到用户id
        Integer userId = assignRoleDto.getUserId();
        //拿到给用户分配的所有角色名
        List<String> roleNameList = assignRoleDto.getRoleCheckList();
        //根据用户id删除给用户已分配的所有角色
        roleMapper.delRoleByUserId(userId);
        //循环添加用户角色关系
        for (String roleName : roleNameList) {
            //根据当前角色名查询当前角色的id
            int roleId = roleMapper.getRoleIdByName(roleName);
            //添加用户角色关系
            roleMapper.insertUserRole(userId, roleId);
        }
    }

    //分页查询角色的业务方法
    @Override
    public Page queryRolePage(Page page, Role role) {
        //查询角色总行数
        int roleCount = roleMapper.selectRoleCount(role);
        //分页查询角色
        List<Role> roleList = roleMapper.selectRolePage(page, role);
        //将查询到的总行数和当前页数据组装到Page对象
        page.setTotalNum(roleCount);
        page.setResultList(roleList);
        return page;
    }

}
