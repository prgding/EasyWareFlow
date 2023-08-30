package me.ding.easywareflow.controller;

import me.ding.easywareflow.entity.Page;
import me.ding.easywareflow.entity.Result;
import me.ding.easywareflow.entity.Role;
import me.ding.easywareflow.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/role")
@RestController
public class RoleController {

    //注入RoleService
    @Autowired
    private RoleService roleService;

    /**
     * 查询所有角色的url接口role/role-list
     */
    @RequestMapping("/role-list")
    public Result queryAllRole(){
        //执行业务
        List<Role> roleList = roleService.getAllRole();
        //响应
        return Result.ok(roleList);
    }

    /**
     * 分页查询角色的url接口/role/role-page-list
     * 参数Page对象用于接收请求参数页码pageNum、每页行数pageSize;
     * 参数Role对象用于接收请求参数角色名roleName、角色代码roleCode、角色状态roleState;
     * 返回值Result对象向客户端响应组装了所有分页信息的Page对象;
     */
    @RequestMapping("/role-page-list")
    public Result roleListPage(Page page, Role role){
        //执行业务
        page = roleService.queryRolePage(page, role);
        //响应
        return Result.ok(page);
    }
}
