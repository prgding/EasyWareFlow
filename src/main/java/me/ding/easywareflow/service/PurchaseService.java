package me.ding.easywareflow.service;

import me.ding.easywareflow.entity.Purchase;
import me.ding.easywareflow.entity.Result;

public interface PurchaseService {
    Result savePurchase(Purchase purchase);
}
