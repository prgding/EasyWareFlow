package me.ding.easywareflow.mapper;

import me.ding.easywareflow.entity.Unit;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UnitMapper {
    List<Unit> findAllUnit();
}
