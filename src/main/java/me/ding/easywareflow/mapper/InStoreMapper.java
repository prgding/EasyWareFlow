package me.ding.easywareflow.mapper;

import me.ding.easywareflow.entity.InStore;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface InStoreMapper {
    int insertInStore(InStore inStore);
}
