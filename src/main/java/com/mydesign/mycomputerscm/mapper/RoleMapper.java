package com.mydesign.mycomputerscm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mydesign.mycomputerscm.domain.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMapper extends BaseMapper<Role> {
    /**
     * 根据角色ID查询当前角色拥有的所有的菜单ID
     * @param roleId
     * @return
     */
    List<String> queryRolePermissionIdsByRid(String roleId);

    /**
     * 根据角色ID删除sys_role_permission
     * @param id
     */
    void deleteRoleMenuByRid(String id);
    /**
     * 保存角色和菜单权限之间的关系
     *
     */
    void saveRoleMenu(@Param("role_id")String rid, @Param("menu_id")String pid);
    /**
     * 查询当前用户拥有的角色ID集合
     * @param id
     * @return
     */
    List<String> queryUserRoleIdsByUid(String id);

    void deleteRoleUserByUid(String uid);

    void insertUserRole(String uid, String rid);
}
