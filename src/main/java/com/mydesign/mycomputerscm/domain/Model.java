package com.mydesign.mycomputerscm.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName( value="sys_model")
public class Model {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    @TableField(value="brand")
    private String brand;
    @TableField(value="price")
    private Double price;
    @TableField(value="remark")
    private String remark;
    @TableField(value="modelname")
    private String modelname;
}
