package com.mydesign.mycomputerscm.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName( value="sys_role")
public class Role {
    /*角色状态 启用*/

    public static final transient int ROLESTATE_ENABLE=1;
    /*角色状态 停用*/

    public static  final transient int ROLESTATE_DISABLE=2;
    /*角色状态 删除*/

    public static final transient int ROLESTATE_DEL=3;
    @TableId(type= IdType.UUID ,value="role_id")
    private String roleId;
   @TableField("role_name")
    private String roleName;

    private String remark;

    private Integer status;
}
