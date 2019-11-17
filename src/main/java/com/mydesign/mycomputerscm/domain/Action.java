package com.mydesign.mycomputerscm.domain;

import lombok.Data;

@Data
public class Action {
    public static final int ACTIONTYPE_NORMAL =1;
    public static final int ACTIONTYPE_AUTHOR =2;
    private String actionId;
    private String actionName;
    private Integer type;
    private String menuId;
    private String remark;

}
