package com.mydesign.mycomputerscm.mapper;

import com.mydesign.mycomputerscm.Querydomain.queryRole;
import com.mydesign.mycomputerscm.domain.Menu;
import com.mydesign.mycomputerscm.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface RoleManaRoleMapper extends JpaRepository<Role, String>, JpaSpecificationExecutor<Role> {

    void deleteByroleid(String roleid);

    Role findAllByroleid(String roleid);
}
