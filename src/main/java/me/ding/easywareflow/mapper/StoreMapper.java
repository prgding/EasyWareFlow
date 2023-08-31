package me.ding.easywareflow.mapper;

import me.ding.easywareflow.entity.Page;
import me.ding.easywareflow.entity.Store;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StoreMapper {
    List<Store> findAllStore();

    int selectStoreCount(Store store);

    List<Store> selectStorePage(Page page, Store store);

    int insertStore(Store store);

    int updateStoreById(Store store);

    int deleteStoreById(Integer storeId);
}
