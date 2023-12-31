package me.ding.easywareflow.service.impl;

import com.alibaba.fastjson2.JSON;
import me.ding.easywareflow.dto.AssignAuthDto;
import me.ding.easywareflow.entity.Auth;
import me.ding.easywareflow.entity.Result;
import me.ding.easywareflow.mapper.AuthMapper;
import me.ding.easywareflow.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthServiceImpl implements AuthService {

    //注入AuthMapper
    @Autowired
    private AuthMapper authMapper;

    //注入Redis模板
    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * 根据用户id查询用户权限(菜单)树的业务方法
     */
    @Override
    public List<Auth> findAuthTree(int userId) {
        //先从redis中查询缓存,查到的是权限(菜单)树List<Auth>转的json串
        String authTreeListJson = redisTemplate.opsForValue().get(userId + ":authTree");
        if (StringUtils.hasText(authTreeListJson)) {//redis中查到缓存
            //将json串转回权限(菜单)树List<Auth>并返回
            return JSON.parseArray(authTreeListJson, Auth.class);
        }
        //redis中没有查到缓存,从数据库表中查询所有权限(菜单)
        List<Auth> allAuthList = authMapper.findAllAuth(userId);
        //将所有权限(菜单)List<Auth>转成权限(菜单)树List<Auth>
        List<Auth> authTreeList = allAuthToAuthTree(allAuthList, 0);
        //将权限(菜单)树List<Auth>转成json串并保存到redis
        redisTemplate.opsForValue().set(userId + ":authTree", JSON.toJSONString(authTreeList));
        //返回权限(菜单)树List<Auth>
        return authTreeList;
    }


    //将所有权限(菜单)转成权限(菜单)树的递归算法
    private List<Auth> allAuthToAuthTree(List<Auth> allAuthList, int parentId) {
        //获取父权限(菜单)id为参数parentId的所有权限(菜单)
        //【parentId最初为0,即最初查的是所有一级权限(菜单)】
        List<Auth> authList = new ArrayList<>();
        for (Auth auth : allAuthList) {
            if (auth.getParentId() == parentId) {
                authList.add(auth);
            }
        }
        //查询List<Auth> authList中每个权限(菜单)的所有子级权限(菜单)
        for (Auth auth : authList) {
            List<Auth> childAuthList = allAuthToAuthTree(allAuthList, auth.getAuthId());
            auth.setChildAuth(childAuthList);
        }
        return authList;
    }

    //查询整个权限(菜单)树的业务方法
    @Override
    public List<Auth> allAuthTree() {
        //先从redis中查询缓存,查到的是整个权限(菜单)树List<Auth>转的json串
        String allAuthTreeJson = redisTemplate.opsForValue().get("all:authTree");
        if (StringUtils.hasText(allAuthTreeJson)) {//redis中查到缓存
            //将json串转回整个权限(菜单)树List<Auth>并返回
            return JSON.parseArray(allAuthTreeJson, Auth.class);
        }
        //redis中没有查到缓存,从数据库表中查询所有权限(菜单)
        List<Auth> allAuthList = authMapper.getAllAuth();
        //将所有权限(菜单)List<Auth>转成整个权限(菜单)树List<Auth>
        List<Auth> allAuthTreeList = allAuthToAuthTree(allAuthList, 0);
        //将整个权限(菜单)树List<Auth>转成json串并保存到redis
        redisTemplate.opsForValue().set("all:authTree", JSON.toJSONString(allAuthTreeList));
        //返回整个权限(菜单)树List<Auth>
        return allAuthTreeList;
    }

    //给角色分配权限(菜单)的业务方法
    @Transactional
    @Override
    public void assignAuth(AssignAuthDto assignAuthDto) {

        //拿到角色id
        Integer roleId = assignAuthDto.getRoleId();
        //拿到给角色分配的所有权限(菜单)id
        List<Integer> authIds = assignAuthDto.getAuthIds();

        //根据角色id删除给角色已分配的所有权限(菜单)
        authMapper.delAuthByRoleId(roleId);

        //循环添加角色权限(菜单)关系
        for (Integer authId : authIds) {
            authMapper.insertRoleAuth(roleId, authId);
        }
    }

    @Override
    public Result saveAuth(Auth auth) {
        Auth byName = authMapper.findByName(auth.getAuthName());
        if (byName != null) {
            return Result.err(Result.CODE_ERR_BUSINESS, "权限已存在");
        }
        authMapper.insertAuth(auth);
        return Result.ok("添加权限成功");
    }

    @Override
    public Result updateAuthName(Auth auth) {
        authMapper.updateNameById(auth);
        return Result.ok("更改权限成功");
    }

    @Override
    public Result updateState(Auth auth) {
        int i = authMapper.updateAuthState(auth);
        if (i > 0) return Result.ok("更新状态成功");
        return Result.err(Result.CODE_ERR_BUSINESS, "更新状态失败");
    }

    @Override
    public void deleteAuth(Integer authId) {
        // 删除权限
        int i = authMapper.deleteAuthById(authId);
        if (i > 0) {
            // 收回分配过的权限
            authMapper.deleteAuthFromRole(authId);
        }
    }
}
