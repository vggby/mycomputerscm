package com.mydesign.mycomputerscm.Service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mydesign.mycomputerscm.Querydomain.queryRole;
import com.mydesign.mycomputerscm.domain.Role;
import com.mydesign.mycomputerscm.mapper.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RoleServiceImpl implements RoleService{
    @Autowired
    private RoleMapper RoleManaRoleMapper;
    @Override
    public Page<Role> findAll(queryRole queryrole) {
        List<Integer> status = queryrole.getStatus();
        String role_name = queryrole.getRole_name();
        Integer[] statusstrs=null;
        boolean staflaged= false;
        boolean rname=true;
        if(status!=null){
            final int size = status.size();
            statusstrs = status.toArray(new Integer[size]);
            staflaged=true;
        }
        if(role_name==null||"".equals(role_name)){
            rname=false;
        }

        int  page = 0;
        int limit = 5 ;
        if (queryrole.getOffset()!=null &&queryrole.getOffset()!=0){
            page=queryrole.getOffset()-1;
        }
        if (queryrole.getLimit()!=null&& queryrole.getLimit()!=0){
            limit=queryrole.getLimit();
        }
        LambdaQueryWrapper<Role> roleLambdaQueryWrapper = new LambdaQueryWrapper<>();
        roleLambdaQueryWrapper.eq(rname,Role::getRoleName,role_name).or().eq(staflaged,Role::getStatus,statusstrs);


        Page<Role> rolePage = new Page<Role>(page,limit);

        return  RoleManaRoleMapper.selectPage(rolePage, roleLambdaQueryWrapper);
    }

    @Override
    public int addRole(Role role){
        int save = RoleManaRoleMapper.insert(role);
        return save;
    }

    @Override
    public int updateRole(Role role){
        LambdaQueryWrapper<Role> roleLambdaQueryWrapper = new LambdaQueryWrapper<>();
        roleLambdaQueryWrapper.eq(Role::getRoleId,role.getRoleId());
        int save = RoleManaRoleMapper.update(role,roleLambdaQueryWrapper);
        return save;
    }


    @Override

    public void deleteRole (String roleid ){
        LambdaQueryWrapper<Role> roleLambdaQueryWrapper = new LambdaQueryWrapper<>();
        roleLambdaQueryWrapper.eq(Role::getRoleId,roleid);
        RoleManaRoleMapper.delete(roleLambdaQueryWrapper);
    }

    @Override
    public Role findAllByroleid(String roleid) {
        LambdaQueryWrapper<Role> roleLambdaQueryWrapper = new LambdaQueryWrapper<>();
        roleLambdaQueryWrapper.eq(Role::getRoleId,roleid);
        return RoleManaRoleMapper.selectOne(roleLambdaQueryWrapper);
    }

    @Override
    public List<String> queryRolePermissionIdsByRid(String roleId) {


        return RoleManaRoleMapper.queryRolePermissionIdsByRid(roleId);
    }

    @Override
    public void saveRolePermission(String roleId, String[] ids) {

        //根据rid删除sys_role_permission
        RoleManaRoleMapper.deleteRoleMenuByRid(roleId);
        if(ids!=null&&ids.length>0) {
            for (String pid : ids) {
                RoleManaRoleMapper.saveRoleMenu(roleId,pid);
            }
        }
    }

}
