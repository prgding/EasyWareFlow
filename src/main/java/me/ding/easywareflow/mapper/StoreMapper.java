package me.ding.easywareflow.mapper;

import me.ding.easywareflow.entity.Store;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StoreMapper {
    List<Store> findAllStore();
}
