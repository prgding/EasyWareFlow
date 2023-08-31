package me.ding.easywareflow.service.impl;

import me.ding.easywareflow.entity.Place;
import me.ding.easywareflow.mapper.PlaceMapper;
import me.ding.easywareflow.service.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
//指定缓存的名称即键的前缀,一般是@CacheConfig标注的类的全类名
@CacheConfig(cacheNames = "me.ding.easywareflow.service.impl.PlaceServiceImpl")
@Service
public class PlaceServiceImpl implements PlaceService {
    @Autowired
    private PlaceMapper placeMapper;

    /*
      查询所有产地的业务方法
     */
    //对查询到的所有产地进行缓存,缓存到redis的键为all:place
    @Cacheable(key = "'all:place'")
    @Override
    public List<Place> queryAllPlace() {
        //查询所有产地
        return placeMapper.findAllPlace();
    }

}
