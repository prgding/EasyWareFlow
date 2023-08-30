package me.ding.easywareflow.service.impl;

import me.ding.easywareflow.entity.Page;
import me.ding.easywareflow.entity.User;
import me.ding.easywareflow.mapper.UserMapper;
import me.ding.easywareflow.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public Page queryUserPage(Page page, User user) {
        //查询用户总行数
        int userCount = userMapper.selectUserCount(user);
        //分页查询用户
        List<User> userList = userMapper.selectUserPage(page, user);
        //将查询到的总行数和当前页数据组装到Page对象
        page.setTotalNum(userCount);
        page.setResultList(userList);
        return page;
    }
}
