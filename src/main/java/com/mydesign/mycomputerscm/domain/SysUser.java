package com.mydesign.mycomputerscm.domain;


import lombok.Data;

import org.hibernate.annotations.GenericGenerator;


import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.List;


@Data
@Entity
@Table( name = "sys_users")
public class SysUser {
    @GenericGenerator(name = "jpa-uuid", strategy = "uuid")
    @Id
    private String userid;
    @Column
    private String username;
    @Column
    private String password;
    @Column
    private String remark;
    @Column
    private Integer status;

}
