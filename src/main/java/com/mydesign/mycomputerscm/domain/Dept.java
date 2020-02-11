package com.mydesign.mycomputerscm.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@TableName("sys_dept")
public class Dept {
    @TableField("dept_name")
    private String deptName;
    @TableId(type= IdType.UUID ,value="dept_id")
    private String deptId;
    @TableField("dept_add")
    private String deptAdd;
    @TableField("dept_phone")
    private String deptPhone;
    @TableField("remake")
    private String remake;
}
