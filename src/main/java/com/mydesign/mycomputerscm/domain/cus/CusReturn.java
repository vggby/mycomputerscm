package com.mydesign.mycomputerscm.domain.cus;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDate;


@Data
@Accessors(chain = true)
public class CusReturn implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer customerId;

    private Double price;

    private String computerType;

    private Integer salenumber;

    private Double amount;

    private String colour;

    private String config;

    private String entryOp;

    private LocalDate entryDate;

    private String remark;

    private String orderId;


}
