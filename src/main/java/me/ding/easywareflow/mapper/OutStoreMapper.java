package me.ding.easywareflow.mapper;

import me.ding.easywareflow.entity.OutStore;
import me.ding.easywareflow.entity.Page;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OutStoreMapper {
    int insertOutStore(OutStore outStore);

    int outStoreCount(OutStore outStore);

    List<OutStore> outStorePage(Page page, OutStore outStore);

    int updateIsOutById(Integer outsId);
}
