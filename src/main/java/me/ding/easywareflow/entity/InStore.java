package me.ding.easywareflow.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 入库单表in_store表的实体类:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InStore {
    private Integer insId;//入库单id
    private Integer storeId;//仓库id
    private Integer productId;//商品id
    private Integer inNum;//入库数量
    private Integer createBy;//创建入库单的用户id
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createTime;//创建时间
    private Integer isIn;//是否入库,1.是,0.否
}
