package me.ding.easywareflow.mapper;

import me.ding.easywareflow.entity.Purchase;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PurchaseMapper {
    int insertPurchase(Purchase purchase);
}
