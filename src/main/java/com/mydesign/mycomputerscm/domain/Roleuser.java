package com.mydesign.mycomputerscm.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName( value="sys_roleuser")
public class Roleuser {
    @TableId(type= IdType.UUID ,value="id")
    private String id;
    /** 用户ID */
    @TableField("user_id")
    private String userid;

    /** 角色ID */
    @TableField("role_id")
    private String roleid;
}
