package me.ding.easywareflow.service;

import me.ding.easywareflow.entity.Page;
import me.ding.easywareflow.entity.Product;

public interface ProductService {
    Page queryProductPage(Page page, Product product);
}
