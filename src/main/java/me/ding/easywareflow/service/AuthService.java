package me.ding.easywareflow.service;

import me.ding.easywareflow.dto.AssignAuthDto;
import me.ding.easywareflow.entity.Auth;
import me.ding.easywareflow.entity.Result;

import java.util.List;

public interface AuthService {
    //根据用户id查询用户权限(菜单)树的业务方法
    List<Auth> findAuthTree(int userId);

    List<Auth> allAuthTree();

    void assignAuth(AssignAuthDto assignAuthDto);

    Result saveAuth(Auth auth);

    Result updateAuthName(Auth auth);

    Result updateState(Auth auth);
}
