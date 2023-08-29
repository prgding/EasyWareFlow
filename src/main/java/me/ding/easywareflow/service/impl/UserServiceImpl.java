package me.ding.easywareflow.service.impl;

import me.ding.easywareflow.entity.User;
import me.ding.easywareflow.mapper.UserMapper;
import me.ding.easywareflow.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    //注入UserMapper
    @Autowired
    private UserMapper userMapper;

    //根据用户名查找用户的业务方法
    @Override
    public User findUserByCode(String userCode) {
        return userMapper.findUserByCode(userCode);
    }
}
