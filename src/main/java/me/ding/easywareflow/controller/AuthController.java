package me.ding.easywareflow.controller;

import me.ding.easywareflow.entity.Auth;
import me.ding.easywareflow.entity.Result;
import me.ding.easywareflow.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/auth")
@RestController
public class AuthController {

    //注入AuthService
    @Autowired
    private AuthService authService;

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
}