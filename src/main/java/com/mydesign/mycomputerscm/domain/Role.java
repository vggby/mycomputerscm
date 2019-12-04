package com.mydesign.mycomputerscm.domain;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@Entity
@Table( name = "sys_role")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class Role {
    /*角色状态 启用*/
    @Transient
    public static final int ROLESTATE_ENABLE=1;
    /*角色状态 停用*/
    @Transient
    public static final int ROLESTATE_DISABLE=2;
    /*角色状态 删除*/
    @Transient
    public static final int ROLESTATE_DEL=3;

    @Id
    @GeneratedValue(generator = "jpa-uuid")
    @Column(name = "roleid")
    private String roleid;
    @Column(name = "role_name")
    private String roleName;
    @Column(name = "remark")
    private String remark;
    @Column(name = "status")
    private Integer status;
}
