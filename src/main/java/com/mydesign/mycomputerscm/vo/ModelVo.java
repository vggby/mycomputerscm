package com.mydesign.mycomputerscm.vo;

import com.mydesign.mycomputerscm.domain.Model;
import lombok.Data;


@Data
public class ModelVo extends Model {
    private Integer page=1;
    private Integer limit=10;
}
