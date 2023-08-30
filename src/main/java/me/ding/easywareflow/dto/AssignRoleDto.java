package me.ding.easywareflow.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssignRoleDto {
    //接收请求参数userId -- 用户id
    private Integer userId;

    //接收请求参数roleCheckList -- 给用户分配的所有角色名
    private List<String> roleCheckList;

}
