package me.ding.easywareflow.service.impl;

import me.ding.easywareflow.entity.Store;
import me.ding.easywareflow.mapper.StoreMapper;
import me.ding.easywareflow.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

//指定缓存的名称即键的前缀,一般是@CacheConfig标注的类的全类名
@CacheConfig(cacheNames = "me.ding.easywareflow.service.impl.StoreServiceImpl")
@Service
public class StoreServiceImpl implements StoreService {

    //注入StoreMapper
    @Autowired
    private StoreMapper storeMapper;

    /*
      查询所有仓库的业务方法
     */
    //对查询到的所有仓库进行缓存,缓存到redis的键为all:store
    @Cacheable(key = "'all:store'")
    @Override
    public List<Store> queryAllStore() {
        //查询所有仓库
        return storeMapper.findAllStore();
    }
}
