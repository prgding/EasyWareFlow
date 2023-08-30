package me.ding.easywareflow.controller;

import me.ding.easywareflow.entity.CurrentUser;
import me.ding.easywareflow.entity.Page;
import me.ding.easywareflow.entity.Result;
import me.ding.easywareflow.entity.Role;
import me.ding.easywareflow.service.RoleService;
import me.ding.easywareflow.utils.TokenUtils;
import me.ding.easywareflow.utils.WarehouseConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RequestMapping("/role")
@RestController
public class RoleController {

    //注入RoleService
    @Autowired
    private RoleService roleService;
    @Autowired
    private TokenUtils tokenUtils;

    /**
     * 查询所有角色的url接口role/role-list
     */
    @RequestMapping("/role-list")
    public Result queryAllRole() {
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
    public Result roleListPage(Page page, Role role) {
        //执行业务
        page = roleService.queryRolePage(page, role);
        //响应
        return Result.ok(page);
    }

    /**
     * 添加角色的url接口/role/role-add
     * 将请求头Token的值即客户端归还的token赋值给参数变量token;
     */
    @RequestMapping("/role-add")
    public Result addRole(@RequestBody Role role, @RequestHeader(WarehouseConstants.HEADER_TOKEN_NAME) String token) {
        //获取当前登录的用户
        CurrentUser currentUser = tokenUtils.getCurrentUser(token);
        //获取当前登录的用户id,即创建新角色的用户id
        int createBy = currentUser.getUserId();
        role.setCreateBy(createBy);
        //执行业务
        return roleService.saveRole(role);
    }

    /**
     * 修改角色状态的url接口/role/role-state-update
     * 将请求头Token的值即客户端归还的token赋值给参数变量token;
     */
    @RequestMapping("/role-state-update")
    public Result updateRoleState(@RequestBody Role role, @RequestHeader(WarehouseConstants.HEADER_TOKEN_NAME) String token) {
        //获取当前登录的用户
        CurrentUser currentUser = tokenUtils.getCurrentUser(token);
        //获取当前登录的用户id,即修改角色的用户id
        int updateBy = currentUser.getUserId();

        //设置修改角色的用户id和修改时间
        role.setUpdateBy(updateBy);
        role.setUpdateTime(new Date());

        return roleService.updateRoleState(role);
    }

}
