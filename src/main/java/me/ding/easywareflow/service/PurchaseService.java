package me.ding.easywareflow.service;

import me.ding.easywareflow.entity.Page;
import me.ding.easywareflow.entity.Purchase;
import me.ding.easywareflow.entity.Result;

public interface PurchaseService {
    Result savePurchase(Purchase purchase);

    Page queryPurchasePage(Page page, Purchase purchase);

    Result deletePurchase(Integer buyId);

    Result updatePurchase(Purchase purchase);
}
