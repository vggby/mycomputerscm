package com.mydesign.mycomputerscm.Service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mydesign.mycomputerscm.Querydomain.queryRole;
import com.mydesign.mycomputerscm.domain.Role;

public interface RoleService {
    Page<Role> findAll(queryRole queryrole);
    int addRole(Role role);
    void deleteRole (String roleid );

    Role findAllByroleid(String roleid);
}
