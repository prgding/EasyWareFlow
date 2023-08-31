package me.ding.easywareflow.service;

import me.ding.easywareflow.entity.InStore;
import me.ding.easywareflow.entity.Result;

public interface InStoreService {
    Result saveInStore(InStore inStore, Integer buyId);
}
