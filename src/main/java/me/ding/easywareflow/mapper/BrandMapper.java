package me.ding.easywareflow.mapper;

import me.ding.easywareflow.entity.Brand;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BrandMapper {
    //查询所有品牌的方法
    List<Brand> findAllBrand();

}
