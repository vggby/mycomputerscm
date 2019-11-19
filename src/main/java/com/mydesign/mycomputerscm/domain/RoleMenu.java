package com.mydesign.mycomputerscm.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table( name = "sys_rolemenu")
public class RoleMenu {
    @Id
    private String id;
    @Column(name = "role_id")
    private String roleId;
    @Column(name = "menu_id")
    private  String menuId;

}
