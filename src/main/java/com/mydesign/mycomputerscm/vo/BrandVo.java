package com.mydesign.mycomputerscm.vo;

import com.mydesign.mycomputerscm.domain.Brand;
import lombok.Data;


@Data
public class BrandVo extends Brand {
    private Integer page=1;
    private Integer limit=10;
}
