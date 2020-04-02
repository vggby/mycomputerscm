package com.mydesign.mycomputerscm.domain.bus;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;



@Data
@TableName("bus_IMEI")
public class IMEI implements Serializable {

    @TableField( "imei")
    private String imei;


    @TableField( "order_id")
    private String orderId;



}
