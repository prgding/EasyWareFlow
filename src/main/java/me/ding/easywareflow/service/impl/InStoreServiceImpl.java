package me.ding.easywareflow.service.impl;

import me.ding.easywareflow.entity.InStore;
import me.ding.easywareflow.entity.Result;
import me.ding.easywareflow.mapper.InStoreMapper;
import me.ding.easywareflow.mapper.PurchaseMapper;
import me.ding.easywareflow.service.InStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class InStoreServiceImpl implements InStoreService {

    @Autowired
    private InStoreMapper inStoreMapper;
    @Autowired
    private PurchaseMapper purchaseMapper;

    //添加入库单的业务方法
    @Transactional//事务处理
    @Override
    public Result saveInStore(InStore inStore, Integer buyId) {
        //添加入库单
        int i = inStoreMapper.insertInStore(inStore);
        if (i > 0) {
            //根据id将采购单状态改为已入库
            int j = purchaseMapper.updateIsInById(buyId);
            if (j > 0) return Result.ok("入库单添加成功！");
            return Result.err(Result.CODE_ERR_BUSINESS, "入库单添加失败！");
        }
        return Result.err(Result.CODE_ERR_BUSINESS, "入库单添加失败！");
    }
}
