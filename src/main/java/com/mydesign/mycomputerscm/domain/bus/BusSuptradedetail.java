package com.mydesign.mycomputerscm.domain.bus;

import com.baomidou.mybatisplus.annotation.IdType;
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
 * @since 2020-03-14
 */
@Data
@Accessors(chain = true)
public class BusSuptradedetail  implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "invoice_id", type = IdType.AUTO)
    private Integer invoiceId;

    private Integer providerId;

    /**
     * 入库 出库
     */
    private String tradetype;

    private Integer number;

    private Double price;

    private Date entryDate;

    private String entryOp;

    private Double amount;

    private String remark;

    private String orderId;
}
