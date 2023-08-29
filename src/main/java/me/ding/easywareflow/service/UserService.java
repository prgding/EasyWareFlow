package me.ding.easywareflow.service;

import me.ding.easywareflow.entity.User;

public interface UserService {

    //根据用户名查找用户的业务方法
    User findUserByCode(String userCode);
}
