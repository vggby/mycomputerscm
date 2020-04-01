package com.mydesign.mycomputerscm.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName( value="bus_computerstorage")
public class ComputerStorage implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    @TableField(value="provider_id")
    private Integer providerId;
    @TableField(value="computer_type")
    private String computerType;
    @TableField(value="order_id")
    private String orderId;
    @TableField(value="colour")
    private String colour;
    @TableField(value="config")
    private String config;
    @TableField(value="price")
    private Double price;
    @TableField(value="number")
    private Integer number;
    @TableField(value="amount")
    private Double amount;
    @TableField(value="entry_date")
    private Date entryDate;
    @TableField(value="entry_op")
    private String entryOp;
    @TableField(value="remark")
    private String remark;

    @TableField(exist=false)
    private String providername;
}
