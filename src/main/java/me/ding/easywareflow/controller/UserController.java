package me.ding.easywareflow.controller;

import me.ding.easywareflow.entity.*;
import me.ding.easywareflow.service.AuthService;
import me.ding.easywareflow.service.UserService;
import me.ding.easywareflow.utils.TokenUtils;
import me.ding.easywareflow.utils.WarehouseConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private AuthService authService;
    @Autowired
    private TokenUtils tokenUtils;
    @Autowired
    private UserService userService;

    /**
     * 加载当前登录用户权限(菜单)树的url接口/user/auth-list
     * 将请求头Token的值即前端归还的token,赋值给请求处理方法的参数String clientToken
     */
    @GetMapping("/auth-list")
    public Result authList(@RequestHeader(WarehouseConstants.HEADER_TOKEN_NAME) String clientToken) {
        //从前端归还的token中解析出当前登录用户的信息
        CurrentUser currentUser = tokenUtils.getCurrentUser(clientToken);
        //根据用户id查询用户权限(菜单)树
        List<Auth> authTreeList = authService.findAuthTree(currentUser.getUserId());
        //响应
        return Result.ok(authTreeList);
    }

    /**
     * 分页查询用户的url接口/user/user-list
     * 参数Page对象用于接收请求参数页码pageNum、每页行数pageSize;
     * 参数User对象用于接收请求参数用户名userCode、用户类型userType、用户状态userState;
     * 返回值Result对象向客户端响应组装了所有分页信息的Page对象;
     */
    @RequestMapping("/user-list")
    public Result userListPage(Page page, User user) {
        //执行业务
        page = userService.queryUserPage(page, user);
        //响应
        return Result.ok(page);
    }

}
