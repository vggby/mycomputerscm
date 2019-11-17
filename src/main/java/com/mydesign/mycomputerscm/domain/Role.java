package com.mydesign.mycomputerscm.domain;

import lombok.Data;

@Data
public class Role {
    private String roleId;
    private String roleName;
    private String remark;
    private Integer status;
}
