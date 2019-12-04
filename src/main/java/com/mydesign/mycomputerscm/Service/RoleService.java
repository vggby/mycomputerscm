package com.mydesign.mycomputerscm.Service;

import com.mydesign.mycomputerscm.Querydomain.queryRole;
import com.mydesign.mycomputerscm.domain.Role;
import org.springframework.data.domain.Page;

public interface RoleService {
    Page<Role> findAll(queryRole queryrole);
    Role addRole(Role role);
    void deleteRole (String roleid );

    Role findAllByroleid(String roleid);
}
