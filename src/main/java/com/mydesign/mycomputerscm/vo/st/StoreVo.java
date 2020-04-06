package com.mydesign.mycomputerscm.vo.st;

import com.mydesign.mycomputerscm.domain.sto.StoComstock;
import lombok.Data;


@Data
public class StoreVo extends StoComstock {
    private Integer page=1;
    private Integer limit=10;
}
