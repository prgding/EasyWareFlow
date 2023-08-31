package me.ding.easywareflow.service;

import me.ding.easywareflow.entity.OutStore;
import me.ding.easywareflow.entity.Page;
import me.ding.easywareflow.entity.Result;

public interface OutStoreService {
    Result saveOutStore(OutStore outStore);

    Page outStorePage(Page page, OutStore outStore);

    Result confirmOutStore(OutStore outStore);
}
