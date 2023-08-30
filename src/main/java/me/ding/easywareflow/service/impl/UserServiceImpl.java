package me.ding.easywareflow.service.impl;

import me.ding.easywareflow.entity.Page;
import me.ding.easywareflow.entity.Result;
import me.ding.easywareflow.entity.User;
import me.ding.easywareflow.mapper.UserMapper;
import me.ding.easywareflow.service.UserService;
import me.ding.easywareflow.utils.DigestUtil;
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

    //添加用户的业务方法
    @Override
    public Result saveUser(User user) {
        //根据用户名查询用户
        User oldUser = userMapper.findUserByCode(user.getUserCode());
        if (oldUser != null) {
            //用户已存在
            return Result.err(Result.CODE_ERR_BUSINESS, "该用户已存在！");
        }
        //用户不存在,对密码加密,添加用户
        String userPwd = DigestUtil.hmacSign(user.getUserPwd());
        user.setUserPwd(userPwd);
        userMapper.insertUser(user);
        return Result.ok("添加用户成功！");
    }

    //修改用户状态的业务方法
    @Override
    public Result updateUserState(User user) {
        //根据用户id修改用户状态
        int i = userMapper.updateUserState(user);
        if (i > 0) {
            return Result.ok("修改成功！");
        }
        return Result.err(Result.CODE_ERR_BUSINESS, "修改失败！");
    }
}
