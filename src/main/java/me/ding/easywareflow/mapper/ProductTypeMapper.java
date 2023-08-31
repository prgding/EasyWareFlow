package me.ding.easywareflow.mapper;

import me.ding.easywareflow.entity.ProductType;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductTypeMapper {
    //查询所有商品分类的方法
    List<ProductType> findAllProductType();

    ProductType findTypeByCode(String typeCode);

    int insertProductType(ProductType productType);

    int deleteProductType(Integer typeId);

    int updateTypeById(ProductType productType);
}
