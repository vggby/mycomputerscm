package com.mydesign.mycomputerscm.vo.st;

import com.mydesign.mycomputerscm.domain.sto.SysBaosun;
import lombok.Data;


@Data
public class BaosunVo extends SysBaosun {
    private Integer page=1;
    private Integer limit=10;
}
