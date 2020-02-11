package com.mydesign.mycomputerscm.vo;

import com.mydesign.mycomputerscm.domain.SysUser;
import lombok.Data;


@Data
public class SysUserVo extends SysUser {
    private Integer page=1;
    private Integer limit=10;
}
