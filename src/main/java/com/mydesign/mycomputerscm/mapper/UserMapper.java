package com.mydesign.mycomputerscm.mapper;


import com.mydesign.mycomputerscm.domain.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserMapper  extends JpaRepository<SysUser,String> {




    SysUser findByUsername(String username);
}
