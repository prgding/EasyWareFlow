package me.ding.easywareflow.controller;

import me.ding.easywareflow.entity.CurrentUser;
import me.ding.easywareflow.entity.LoginUser;
import me.ding.easywareflow.entity.Result;
import me.ding.easywareflow.entity.User;
import me.ding.easywareflow.service.UserService;
import me.ding.easywareflow.utils.DigestUtil;
import me.ding.easywareflow.utils.TokenUtils;
import me.ding.easywareflow.utils.WarehouseConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    //注入UserService
    @Autowired
    private UserService userService;

    //注入redis模板
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    //注入TokenUtils
    @Autowired
    private TokenUtils tokenUtils;

    /**
     * 登录的url接口/login
     */
    @PostMapping("/login")
    public Result login(@RequestBody LoginUser loginUser) {
      /*
        校验验证码：
       */
        if (!stringRedisTemplate.hasKey(loginUser.getVerificationCode())) {
            return Result.err(Result.CODE_ERR_BUSINESS, "验证码不正确！");
        }

      /*
        校验用户名密码:
       */
        //根据用户名查询用户
        User user = userService.findUserByCode(loginUser.getUserCode());
        if (user != null) {//查到了用户
            //查到的用户状态是已审核
            if (user.getUserState().equals(WarehouseConstants.USER_STATE_PASS)) {
                //将用户录入的密码进行加密
                String password = DigestUtil.hmacSign(loginUser.getUserPwd());
                if (password.equals(user.getUserPwd())) {//查到的用户的密码和用户录入的密码相同
                    //生成token并响应给前端
                    CurrentUser currentUser = new
                            CurrentUser(user.getUserId(), user.getUserCode(), user.getUserName());
                    String token = tokenUtils.loginSign(currentUser, user.getUserPwd());
                    return Result.ok("登录成功！", token);
                } else {//查到的用户的密码和用户录入的密码不同
                    return Result.err(Result.CODE_ERR_BUSINESS, "密码不正确！");
                }
            } else {//查到的用户状态是未审核
                return Result.err(Result.CODE_ERR_BUSINESS, "用户未审核！");
            }
        } else {//没有查到用户
            return Result.err(Result.CODE_ERR_BUSINESS, "该用户不存在！");
        }
    }
}
