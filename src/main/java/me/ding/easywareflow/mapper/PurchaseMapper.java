package me.ding.easywareflow.mapper;

import me.ding.easywareflow.entity.Page;
import me.ding.easywareflow.entity.Purchase;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PurchaseMapper {
    int insertPurchase(Purchase purchase);

    int selectPurchaseCount(Purchase purchase);

    List<Purchase> selectPurchasePage(@Param("page") Page page, @Param("purchase") Purchase purchase);

    int deletePurchaseById(Integer buyId);

    int updatePurchaseById(Purchase purchase);

    int updateIsInById(Integer buyId);
}
