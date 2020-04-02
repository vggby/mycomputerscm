package com.mydesign.mycomputerscm.vo;

import com.mydesign.mycomputerscm.domain.BusComputerreturn;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
public class COutportVo extends BusComputerreturn {
    private Integer page = 1;
    private Integer limit = 10;
    private String[] imeis;

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date startTime;

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date endTime;
}
