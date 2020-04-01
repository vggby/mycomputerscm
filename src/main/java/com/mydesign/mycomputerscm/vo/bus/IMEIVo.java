package com.mydesign.mycomputerscm.vo.bus;

import lombok.Data;


@Data
public class IMEIVo extends com.mydesign.mycomputerscm.domain.bus.IMEI {
    private Integer page=1;
    private Integer limit=10;
}
