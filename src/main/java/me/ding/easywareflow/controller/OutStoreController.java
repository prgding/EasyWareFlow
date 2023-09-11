package me.ding.easywareflow.controller;

import me.ding.easywareflow.entity.CurrentUser;
import me.ding.easywareflow.entity.OutStore;
import me.ding.easywareflow.entity.Page;
import me.ding.easywareflow.entity.Result;
import me.ding.easywareflow.service.OutStoreService;
import me.ding.easywareflow.service.StoreService;
import me.ding.easywareflow.utils.TokenUtils;
import me.ding.easywareflow.utils.WarehouseConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/outstore")
@RestController
public class OutStoreController {

    @Autowired
    private OutStoreService outStoreService;
    @Autowired
    private TokenUtils tokenUtils;
    @Autowired
    private StoreService storeService;

    /**
     * 添加出库单的url接口/outstore/outstore-add
     * 将请求头Token的值即客户端归还的token赋值给参数变量token;
     */
    @RequestMapping("/outstore-add")
    public Result addOutStore(@RequestBody OutStore outStore, @RequestHeader(WarehouseConstants.HEADER_TOKEN_NAME) String token){

        //获取当前登录的用户
        CurrentUser currentUser = tokenUtils.getCurrentUser(token);
        //获取当前登录的用户id,即添加出库单的用户id
        int createBy = currentUser.getUserId();
        outStore.setCreateBy(createBy);
        //响应
        return outStoreService.saveOutStore(outStore);
    }

    /**
     * 分页查询出库单的url接口/outstore/outstore-page-list
     * 参数Page对象用于接收请求参数页码pageNum、每页行数pageSize;
     * 参数OutStore对象用于接收请求参数仓库id storeId、商品名称productName、
     * 是否出库isOut、起止时间startTime和endTime;
     * 返回值Result对象向客户端响应组装了所有分页信息的Page对象;
     */
    @RequestMapping("/outstore-page-list")
    public Result outStorePageList(Page page, OutStore outStore){
        //执行业务
        page = outStoreService.outStorePage(page, outStore);
        //响应
        return Result.ok(page);
    }

    /**
     * 确定出库的url接口/outstore/outstore-confirm
     */
    @RequestMapping("/outstore-confirm")
    public Result confirmOutStore(@RequestBody OutStore outStore){
        //执行业务
        return outStoreService.confirmOutStore(outStore);
    }

}
