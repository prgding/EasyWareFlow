package me.ding.easywareflow.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
/**
 * 单位表unit表对应的实体类:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Unit implements Serializable {
    private Integer unitId;//单位id
    private String unitName;//单位
    private String unitDesc;//单位描述
}
