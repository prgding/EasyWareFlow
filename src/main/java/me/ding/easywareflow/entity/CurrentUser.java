package me.ding.easywareflow.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 此User类只封装了用户的用户id、用户名和真实姓名
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CurrentUser {
    private int userId;//用户id
    private String userCode;//用户名
    private String userName;//真实姓名
}
