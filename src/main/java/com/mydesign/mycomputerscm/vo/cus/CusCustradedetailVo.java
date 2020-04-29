package com.mydesign.mycomputerscm.vo.cus;

import com.mydesign.mycomputerscm.domain.cus.CusCustradedetail;
import lombok.Data;
@Data
public class CusCustradedetailVo   extends CusCustradedetail{

    private Integer page=1;
    private Integer limit=10;
}


