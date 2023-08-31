package me.ding.easywareflow.service;

import me.ding.easywareflow.entity.Page;
import me.ding.easywareflow.entity.Result;
import me.ding.easywareflow.entity.Store;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StoreService {
    //查询所有仓库的业务方法
    List<Store> queryAllStore();

    Page queryStorePage(@Param("page") Page page, @Param("store") Store store);

    Result saveStore(Store store);

    Result updateStore(Store store);

    Result deleteStore(Integer storeId);
}
