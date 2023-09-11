package me.ding.easywareflow.controller;

import me.ding.easywareflow.entity.Auth;
import me.ding.easywareflow.entity.CurrentUser;
import me.ding.easywareflow.entity.Result;
import me.ding.easywareflow.service.AuthService;
import me.ding.easywareflow.utils.TokenUtils;
import me.ding.easywareflow.utils.WarehouseConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RequestMapping("/auth")
@RestController
public class AuthController {

    @Autowired
    private AuthService authService;
    @Autowired
    private TokenUtils tokenUtils;

    /**
     * 查询整个权限(菜单)树的url接口/auth/auth-tree
     */
    @RequestMapping("/auth-tree")
    public Result allAuthTree() {
        //执行业务
        List<Auth> allAuthTree = authService.allAuthTree();
        //响应
        return Result.ok(allAuthTree);
    }

    // 添加权限
    @RequestMapping("/auth-add")
    public Result addAuth(@RequestBody Auth auth, @RequestHeader(WarehouseConstants.HEADER_TOKEN_NAME) String token) {
        CurrentUser currentUser = tokenUtils.getCurrentUser(token);
        int createBy = currentUser.getUserId();
        auth.setCreateBy(createBy);
        auth.setCreateTime(new Date());
        return authService.saveAuth(auth);
    }

    // 修改权限
    @RequestMapping("/auth-update")
    public Result updateAuth(@RequestBody Auth auth, @RequestHeader(WarehouseConstants.HEADER_TOKEN_NAME) String token) {
        CurrentUser currentUser = tokenUtils.getCurrentUser(token);
        int updateBy = currentUser.getUserId();
        auth.setUpdateBy(updateBy);
        auth.setUpdateTime(new Date());
        return authService.updateAuthName(auth);
    }

    @RequestMapping("/auth-state-update")
    public Result updateAuthState(@RequestBody Auth auth, @RequestHeader(WarehouseConstants.HEADER_TOKEN_NAME) String token) {
        CurrentUser currentUser = tokenUtils.getCurrentUser(token);
        int updateBy = currentUser.getUserId();
        auth.setUpdateBy(updateBy);
        auth.setUpdateTime(new Date());
        return authService.updateState(auth);
    }


}
