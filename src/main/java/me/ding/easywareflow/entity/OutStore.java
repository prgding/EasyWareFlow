package me.ding.easywareflow.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 出库单表out_store表的实体类:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OutStore {
    private Integer outsId;//出库单id
    private Integer productId;//出库单出库的商品id
    private Integer storeId;//出库单出库的商品所在仓库id
    private Integer tallyId;//理货员id
    private Double outPrice;//商品的出库价格
    private Integer outNum;//出库的商品数量
    private Integer createBy;//创建出库单的用户id
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;//创建出库单的时间
    private String isOut;//是否出库,0.未出库,1.已出库
}