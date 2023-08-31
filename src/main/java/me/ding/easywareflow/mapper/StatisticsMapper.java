package me.ding.easywareflow.mapper;

import me.ding.easywareflow.entity.Statistics;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StatisticsMapper {
    List<Statistics> statisticsStoreInvent();
}
