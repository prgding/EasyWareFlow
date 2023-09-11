package me.ding.easywareflow.controller;

import me.ding.easywareflow.entity.*;
import me.ding.easywareflow.service.InStoreService;
import me.ding.easywareflow.service.PurchaseService;
import me.ding.easywareflow.service.StoreService;
import me.ding.easywareflow.utils.TokenUtils;
import me.ding.easywareflow.utils.WarehouseConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/purchase")
@RestController
public class PurchaseController {

    //注入PurchaseService
    @Autowired
    private PurchaseService purchaseService;
    @Autowired
    private StoreService storeService;

    /**
     * 添加采购单的url接口/purchase/purchase-add
     */
    @RequestMapping("/purchase-add")
    public Result addPurchase(@RequestBody Purchase purchase) {
        return purchaseService.savePurchase(purchase);
    }


    /**
     * 分页查询采购单的url接口/purchase/purchase-page-list
     * 参数Page对象用于接收请求参数页码pageNum、每页行数pageSize;
     * 参数Purchase对象用于接收请求参数仓库id storeId、商品名称productName、
     * 采购人buyUser、是否生成入库单isIn、起止时间startTime和endTime;
     * 返回值Result对象向客户端响应组装了所有分页信息的Page对象;
     */
    @RequestMapping("/purchase-page-list")
    public Result purchasePageList(Page page, Purchase purchase) {
        //执行业务
        page = purchaseService.queryPurchasePage(page, purchase);
        //响应
        return Result.ok(page);
    }

    /**
     * 删除采购单的url接口/purchase/purchase-delete/{buyId}
     */
    @RequestMapping("/purchase-delete/{buyId}")
    public Result deletePurchase(@PathVariable Integer buyId){
        //执行业务
        return purchaseService.deletePurchase(buyId);
    }

    /**
     * 修改采购单的url接口/purchase/purchase-update
     */
    @RequestMapping("/purchase-update")
    public Result updatePurchase(@RequestBody Purchase purchase){
        //执行业务
        return purchaseService.updatePurchase(purchase);
    }
    @Autowired
    private TokenUtils tokenUtils;

    @Autowired
    private InStoreService inStoreService;

    /**
     * 添加入库单的url接口/purchase/in-warehouse-record-add
     * 将请求头Token的值即客户端归还的token赋值给参数变量token;
     */
    @RequestMapping("/in-warehouse-record-add")
    public Result addInStore(@RequestBody Purchase purchase, @RequestHeader(WarehouseConstants.HEADER_TOKEN_NAME) String token){
        //获取当前登录的用户
        CurrentUser currentUser = tokenUtils.getCurrentUser(token);
        //获取当前登录的用户id -- 创建入库单的用户id
        int createBy = currentUser.getUserId();

        //创建InStore对象封装添加的入库单的信息
        InStore inStore = new InStore();
        inStore.setStoreId(purchase.getStoreId());
        inStore.setProductId(purchase.getProductId());
        inStore.setInNum(purchase.getFactBuyNum());
        inStore.setCreateBy(createBy);

        //执行业务
        return inStoreService.saveInStore(inStore, purchase.getBuyId());
    }


}
