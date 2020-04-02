package com.mydesign.mycomputerscm.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName( value="sys_brand")
public class Brand {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    @TableField(value="brand")
    private String brand;
}
