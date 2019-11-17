package com.mydesign.mycomputerscm.domain;

import lombok.Data;

import java.util.List;
@Data
public class Menu {
    /** 菜单类别:含子菜单 */
    public static final int MENUTYPE_PARENT = 1;
    /** 菜单类别:最终菜单 */
    public static final int MENUTYPE_NODE = 2;

    private String menuId;
    private String menuName;
    private Integer type;
    private String typeName;
    private Action action;
    private String parentId;
    private Integer sortOrder;
    private String remark;

    private List<Menu> subMenuList;
}
