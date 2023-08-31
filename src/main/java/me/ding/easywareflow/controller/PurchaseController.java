package me.ding.easywareflow.controller;

import me.ding.easywareflow.entity.Purchase;
import me.ding.easywareflow.entity.Result;
import me.ding.easywareflow.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/purchase")
@RestController
public class PurchaseController {

    //注入PurchaseService
    @Autowired
    private PurchaseService purchaseService;

    /**
     * 添加采购单的url接口/purchase/purchase-add
     */
    @RequestMapping("/purchase-add")
    public Result addPurchase(@RequestBody Purchase purchase){
        return purchaseService.savePurchase(purchase);
    }
}
