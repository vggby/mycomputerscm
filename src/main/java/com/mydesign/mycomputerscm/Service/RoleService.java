package com.mydesign.mycomputerscm.Service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mydesign.mycomputerscm.Querydomain.queryRole;
import com.mydesign.mycomputerscm.domain.Role;

import java.util.List;

public interface RoleService extends IService<Role> {
    Page<Role> findAll(queryRole queryrole);
    int addRole(Role role);
    void deleteRole (String roleid );
     int updateRole(Role role);
    Role findAllByroleid(String roleid);
    List<String> queryRolePermissionIdsByRid(String roleId);
    void saveRolePermission(String roleId, String[] ids);
    /**
     * 查询当前用户拥有的角色ID集合
     * @param id
     * @return
     */
    List<String> queryUserRoleIdsByUid(String id);

}
