package me.ding.easywareflow.mapper;

import me.ding.easywareflow.entity.InStore;
import me.ding.easywareflow.entity.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface InStoreMapper {
    int insertInStore(InStore inStore);

    int selectInStoreCount(InStore inStore);

    List<InStore> selectInStorePage(@Param("page") Page page, @Param("inStore") InStore inStore);

    int updateIsInById(Integer insId);
}
