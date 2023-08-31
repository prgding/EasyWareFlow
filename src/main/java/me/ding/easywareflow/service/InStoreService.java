package me.ding.easywareflow.service;

import me.ding.easywareflow.entity.InStore;
import me.ding.easywareflow.entity.Page;
import me.ding.easywareflow.entity.Result;

public interface InStoreService {
    Result saveInStore(InStore inStore, Integer buyId);

    Page queryInStorePage(Page page, InStore inStore);

    Result confirmInStore(InStore inStore);
}
