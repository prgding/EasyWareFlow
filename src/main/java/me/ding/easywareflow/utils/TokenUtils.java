package me.ding.easywareflow.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import me.ding.easywareflow.entity.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * token工具类
 */
@Component
public class TokenUtils {

    /**
     * 常量:
     */
    //token中存放用户id对应的名字
    private static final String CLAIM_NAME_USERID = "CLAIM_NAME_USERID";
    //token中存放用户名对应的名字
    private static final String CLAIM_NAME_USERCODE = "CLAIM_NAME_USERCODE";
    //token中存放用户真实姓名对应的名字
    private static final String CLAIM_NAME_USERNAME = "CLAIM_NAME_USERNAME";
    //注入redis模板
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    //注入配置文件中的warehouse.expire-time属性 -- token的过期时间
    @Value("${warehouse.expire-time}")
    private int expireTime;

    private String sign(CurrentUser currentUser, String securityKey) {
        String token = JWT.create()
                .withClaim(CLAIM_NAME_USERID, currentUser.getUserId())
                .withClaim(CLAIM_NAME_USERCODE, currentUser.getUserCode())
                .withClaim(CLAIM_NAME_USERNAME, currentUser.getUserName())
                .withIssuedAt(new Date())//发行时间
                .withExpiresAt(new Date(System.currentTimeMillis() +
                        expireTime * 1000))//有效时间
                .sign(Algorithm.HMAC256(securityKey));
        return token;
    }

    /**
     * 将当前用户信息以用户密码为密钥生成token的方法
     */
    public String loginSign(CurrentUser currentUser, String password) {
        //生成token
        String token = sign(currentUser, password);
        //将token保存到redis中,并设置token在redis中的过期时间
        stringRedisTemplate.opsForValue().set(token, token,
                expireTime * 2, TimeUnit.SECONDS);
        return token;
    }

}
