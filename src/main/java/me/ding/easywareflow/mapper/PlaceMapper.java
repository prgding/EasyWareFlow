package me.ding.easywareflow.mapper;

import me.ding.easywareflow.entity.Place;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PlaceMapper {
    List<Place> findAllPlace();
}
