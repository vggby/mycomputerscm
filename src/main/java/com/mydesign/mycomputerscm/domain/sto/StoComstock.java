package com.mydesign.mycomputerscm.domain.sto;

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
//库存表
public class StoComstock implements Serializable {

    private static final long serialVersionUID = 1L;

    private String imei;

    private String computerType;

    private String colour;

    private String config;

    private Integer providerId;

    private Date entryDate;

    private Double price;

    private Double lossamount;

    private String orderId;

}
