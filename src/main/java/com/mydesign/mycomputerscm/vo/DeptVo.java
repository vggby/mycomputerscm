package com.mydesign.mycomputerscm.vo;

import com.mydesign.mycomputerscm.domain.Dept;
import lombok.Data;


@Data
public class DeptVo extends Dept {
    private Integer page=1;
    private Integer limit=10;
}
