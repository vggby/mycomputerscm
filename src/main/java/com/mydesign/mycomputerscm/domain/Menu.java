package com.mydesign.mycomputerscm.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
@TableName("sys_menu")

public class Menu {
   /* *//** 菜单类别:含子菜单 *//*
    public static final int MENUTYPE_PARENT = 1;
    *//** 菜单类别:最终菜单 *//*
    public static final int MENUTYPE_NODE = 2;*/
    @TableId(value="menu_id")
    private String menuId;
    @TableField("menu_name")
    private String menuName;
    @TableField("type")
    private Integer type;

    @TableField("action")
    private String action;
    @TableField( "parent_id")
    private String parentId;
    @TableField( "sortOrder")
    private Integer sortOrder;
    @TableField
    private String remark;

    /** 子菜单 */
    private transient List<Menu> children = new ArrayList<Menu>();

    private transient List<Menu>  subMenuList;
    @TableField
    private List<Menu> perms;
}
