package me.ding.easywareflow.mapper;

import me.ding.easywareflow.entity.Supply;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SupplyMapper {
    //查询所有供应商的方法
    List<Supply> findAllSupply();

}
