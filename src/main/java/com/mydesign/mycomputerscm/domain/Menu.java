package com.mydesign.mycomputerscm.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.List;
@Data
@Entity
@Table( name = "sys_menu")
public class Menu {
   /* *//** 菜单类别:含子菜单 *//*
    public static final int MENUTYPE_PARENT = 1;
    *//** 菜单类别:最终菜单 *//*
    public static final int MENUTYPE_NODE = 2;*/
    @Id
    @Column(name = "menu_id")
    private String menuId;
    @Column(name = "menuName")
    private String menuName;
    @Column(name = "type")
    private Integer type;

    @Column(name = "action_id")
    private String action;
    @Column(name = "parent_id")
    private String parentId;
    @Column(name = "sortOrder")
    private Integer sortOrder;
    @Column
    private String remark;
    @Transient
    private List<Menu> subMenuList;

}
