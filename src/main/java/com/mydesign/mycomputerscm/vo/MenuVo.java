package com.mydesign.mycomputerscm.vo;

import com.mydesign.mycomputerscm.domain.Menu;
import lombok.Data;


@Data
public class MenuVo extends Menu  {
    private Integer page=1;
    private Integer limit=10;
}
