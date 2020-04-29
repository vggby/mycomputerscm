package com.mydesign.mycomputerscm.domain.cus;

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
 * @since 2020-04-19
 */
@Data
@Accessors(chain = true)
public class CusCustradedetail implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "invoice_id", type = IdType.AUTO)
    private Integer invoiceId;

    /**
     * 客户id
     */
    private Integer customerId;

    /**
     * 销售 退货
     */
    private String tradetype;

    /**
     * 总数
     */
    private Integer number;

    private Double price;

    private Date entryDate;

    private String entryOp;

    private Double amount;

    private String remark;

    private String orderId;



}
