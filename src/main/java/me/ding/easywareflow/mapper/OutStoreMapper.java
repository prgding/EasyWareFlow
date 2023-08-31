package me.ding.easywareflow.mapper;

import me.ding.easywareflow.entity.OutStore;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OutStoreMapper {
    int insertOutStore(OutStore outStore);
}
