package com.mydesign.mycomputerscm.vo.cus;

import com.mydesign.mycomputerscm.domain.cus.CusReturn;
import lombok.Data;


@Data
public class CusSaleReVo extends CusReturn {
    private Integer page=1;
    private Integer limit=10;
    private String[] imeis;
}
