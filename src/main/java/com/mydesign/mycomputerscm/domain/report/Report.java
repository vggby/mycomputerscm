package com.mydesign.mycomputerscm.domain.report;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author jobob
 * @since 2020-04-13
 */
@Data
@Accessors(chain = true)
public class Report implements Serializable {

    private static final long serialVersionUID = 1L;


    private Double price;

    private String computerType;

    private Integer salenumber;

    private Double amount;

    private Double totalcost;

    private Double grossprofit;


}
