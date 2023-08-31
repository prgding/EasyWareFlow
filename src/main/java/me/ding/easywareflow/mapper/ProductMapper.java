package me.ding.easywareflow.mapper;

import me.ding.easywareflow.entity.Page;
import me.ding.easywareflow.entity.Product;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ProductMapper {
    //查询商品总行数的方法
    int selectProductCount(Product product);

    //分页查询商品的方法
    List<Product> selectProductPage(@Param("page") Page page, @Param("product") Product product);

    int insertProduct(Product product);

    int updateStateById(Product product);

    int deleteProductById(Integer productId);

    int updateProductById(Product product);
}
