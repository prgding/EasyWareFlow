package me.ding.easywareflow.service;

import me.ding.easywareflow.entity.ProductType;

import java.util.List;

public interface ProductTypeService {

    //查询所有商品分类树的业务方法
    List<ProductType> allProductTypeTree();
}
