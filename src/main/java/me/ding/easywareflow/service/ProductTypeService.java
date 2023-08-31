package me.ding.easywareflow.service;

import me.ding.easywareflow.entity.ProductType;
import me.ding.easywareflow.entity.Result;

import java.util.List;

public interface ProductTypeService {

    //查询所有商品分类树的业务方法
    List<ProductType> allProductTypeTree();

    Result queryTypeByCode(String typeCode);

    Result saveProductType(ProductType productType);

    Result removeProductType(Integer typeId);

    Result updateProductType(ProductType productType);
}
