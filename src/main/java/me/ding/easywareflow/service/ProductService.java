package me.ding.easywareflow.service;

import me.ding.easywareflow.entity.Page;
import me.ding.easywareflow.entity.Product;
import me.ding.easywareflow.entity.Result;

public interface ProductService {
    Page queryProductPage(Page page, Product product);

    Result saveProduct(Product product);

    Result updateProductState(Product product);

    Result deleteProduct(Integer productId);

    Result updateProduct(Product product);
}
