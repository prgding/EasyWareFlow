package me.ding.easywareflow.service;

import me.ding.easywareflow.entity.Page;
import me.ding.easywareflow.entity.Result;
import me.ding.easywareflow.entity.User;

public interface UserService {

    //根据用户名查找用户的业务方法
    User findUserByCode(String userCode);

    //分页查询用户的业务方法
    Page queryUserPage(Page page, User user);

    //添加用户的业务方法
    Result saveUser(User user);

    Result updateUserState(User user);

    int deleteUserById(Integer userId);

    Result updateUserName(User user);

    Result resetPwd(Integer userId);
}
