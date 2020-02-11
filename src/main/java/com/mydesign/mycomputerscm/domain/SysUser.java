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

    @TableField("emp_name")
    private String empName;
    @TableField("emp_phone")
    private String empPhone;
    @TableField("emp_type")
    private Integer empType;
    @TableField("did")
    private String did;

    private transient   String deptname;


}
