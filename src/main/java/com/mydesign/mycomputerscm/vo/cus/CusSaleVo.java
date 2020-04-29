package com.mydesign.mycomputerscm.vo.cus;

import com.mydesign.mycomputerscm.domain.cus.CusSale;
import lombok.Data;


@Data
public class CusSaleVo extends CusSale {
    private Integer page=1;
    private Integer limit=10;
    private String[] imeis;
}
