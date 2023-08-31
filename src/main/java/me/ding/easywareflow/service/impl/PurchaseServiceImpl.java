package me.ding.easywareflow.service.impl;

import me.ding.easywareflow.entity.Purchase;
import me.ding.easywareflow.entity.Result;
import me.ding.easywareflow.mapper.PurchaseMapper;
import me.ding.easywareflow.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PurchaseServiceImpl implements PurchaseService {
    @Autowired
    private PurchaseMapper purchaseMapper;

    //添加采购单的业务方法
    @Override
    public Result savePurchase(Purchase purchase) {
        //添加采购单
        int i = purchaseMapper.insertPurchase(purchase);
        if (i > 0) return Result.ok("采购单添加成功！");
        return Result.err(Result.CODE_ERR_BUSINESS, "采购单添加失败！");
    }

}
