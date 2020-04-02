package com.mydesign.mycomputerscm.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author jobob
 * @since 2020-03-29
 */
@Data
@Accessors(chain = true)
public class BusComputerreturn implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private static final long serialVersionUID = 1L;

    private String computerType;

    private String providername;

    private Integer number;

    private Double price;

    private Double amount;

    private String entryOp;

    private Date entryDate;

    private String remark;
    @TableField(value="order_id")
    private String orderId;

}
