package com.mydesign.mycomputerscm.domain;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


@Data

@TableName( value="sys_users")
public class SysUser {

    @TableId(type= IdType.UUID ,value="user_id")
    private String userid;
    @TableField
    private String username;
    @TableField
    private String password;
    @TableField
    private String remark;
    @TableField
    private Integer status;

}
