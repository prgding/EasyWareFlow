package me.ding.easywareflow.controller;

import me.ding.easywareflow.entity.Page;
import me.ding.easywareflow.entity.Result;
import me.ding.easywareflow.entity.Store;
import me.ding.easywareflow.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/store")
@RestController
public class StoreController {

    //注入StoreService
    @Autowired
    private StoreService storeService;

    /**
     * 分页查询仓库的url接口/store/store-page-list
     * 参数Page对象用于接收请求参数页码pageNum、每页行数pageSize;
     * 参数Store对象用于接收请求参数仓库名称storeName、仓库地址storeAddress、
     * 联系人contact、联系电话phone;
     * 返回值Result对象向客户端响应组装了所有分页信息的Page对象;
     */
    @RequestMapping("/store-page-list")
    public Result storePageList(Page page, Store store) {
        //执行业务
        page = storeService.queryStorePage(page, store);
        System.out.println("page = " + page);
        //响应
        return Result.ok(page);
    }

    /**
     * 添加仓库的url接口/store/store-add
     */
    @RequestMapping("/store-add")
    public Result addStore(@RequestBody Store store) {
        //执行业务
        return storeService.saveStore(store);
    }


    /**
     * 修改仓库的url接口/store/store-update
     */
    @RequestMapping("/store-update")
    public Result updateStore(@RequestBody Store store) {
        //执行业务
        return storeService.updateStore(store);
    }

    /**
     * 删除仓库的url接口/store/store-delete/{storeId}
     */
    @RequestMapping("/store-delete/{storeId}")
    public Result deleteStore(@PathVariable Integer storeId) {
        //执行业务
        return storeService.deleteStore(storeId);
    }

    /**
     * 导出数据的url接口/store/exportTable
     * 参数Page对象用于接收请求参数页码pageNum、每页行数pageSize;
     * 参数Store对象用于接收请求参数仓库名称storeName、仓库地址storeAddress、
     * 联系人contact、联系电话phone;
     * 返回值Result对象向客户端响应组装了当前页数据的List;
     */
    @RequestMapping("/exportTable")
    public Result exportTable(Page page, Store store) {
        //分页查询仓库
        page = storeService.queryStorePage(page, store);
        //拿到当前页数据
        List<?> resultList = page.getResultList();
        System.out.println("resultList = " + resultList);
        //响应
        return Result.ok(resultList);
    }

}
