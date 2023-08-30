package me.ding.easywareflow.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 存储用户登录信息的User类：
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginUser {
    private String userCode;//用户名
    private String userPwd;//密码
    private String userState;//用户状态
    private String verificationCode;//验证码
}
