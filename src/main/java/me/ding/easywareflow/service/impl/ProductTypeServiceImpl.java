package me.ding.easywareflow.service.impl;

import me.ding.easywareflow.entity.ProductType;
import me.ding.easywareflow.mapper.ProductTypeMapper;
import me.ding.easywareflow.service.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

//指定缓存的名称即键的前缀,一般是@CacheConfig标注的类的全类名
@CacheConfig(cacheNames = "me.ding.easywareflow.service.impl.productTypeServiceImpl")
@Service
public class ProductTypeServiceImpl implements ProductTypeService {

    //注入ProductTypeMapper
    @Autowired
    private ProductTypeMapper productTypeMapper;

    /*
      查询所有商品分类树的业务方法
     */
    //对查询到的所有商品分类树进行缓存,缓存到redis的键为all:typeTree
    @Cacheable(key = "'all:typeTree'")
    @Override
    public List<ProductType> allProductTypeTree() {
        //查询所有商品分类
        List<ProductType> allTypeList = productTypeMapper.findAllProductType();
        //将所有商品分类List<ProductType>转成商品分类树List<ProductType>
        //返回商品分类树List<ProductType>
        return allTypeToTypeTree(allTypeList, 0);
    }

    //将查询到的所有商品分类List<ProductType>转成商品分类树List<ProductType>的算法
    private List<ProductType> allTypeToTypeTree(List<ProductType> allTypeList, Integer parentId){
        List<ProductType> typeList = new ArrayList<>();
        for (ProductType productType : allTypeList) {
            if(productType.getParentId().equals(parentId)){
                typeList.add(productType);
            }
        }
        for (ProductType productType : typeList) {
            List<ProductType> childTypeList =
                    allTypeToTypeTree(allTypeList, productType.getTypeId());
            productType.setChildProductCategory(childTypeList);
        }
        return typeList;
    }
}
