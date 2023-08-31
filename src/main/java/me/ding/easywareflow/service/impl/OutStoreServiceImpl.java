package me.ding.easywareflow.service.impl;

import me.ding.easywareflow.entity.OutStore;
import me.ding.easywareflow.entity.Result;
import me.ding.easywareflow.mapper.OutStoreMapper;
import me.ding.easywareflow.service.OutStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OutStoreServiceImpl implements OutStoreService {
    @Autowired
    private OutStoreMapper outStoreMapper;

    //添加出库单的业务方法
    @Override
    public Result saveOutStore(OutStore outStore) {
        //添加出库单
        int i = outStoreMapper.insertOutStore(outStore);
        if (i > 0) {
            return Result.ok("添加出库单成功！");
        }
        return Result.err(Result.CODE_ERR_BUSINESS, "添加出库单失败！");
    }

}
