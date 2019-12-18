package com.mydesign.mycomputerscm.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;



@Data
@TableName( value="sys_rolemenu")

public class RoleMenu {
    @TableId(value="id")
    private String id;
    @TableField( "role_id")
    private String roleId;
    @TableField("menu_id")
    private  String menuId;

}
