package com.mydesign.mycomputerscm.Service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mydesign.mycomputerscm.domain.SysUser;
import com.mydesign.mycomputerscm.mapper.RoleMapper;
import com.mydesign.mycomputerscm.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserServiceImpl   extends ServiceImpl<UserMapper, SysUser> implements UserService {

    @Autowired
    private UserMapper usermapper;
    @Autowired
    private RoleMapper roleMapper;
    @Override
    public int SaveUser(SysUser user) {
        user.setPassword(user.getPassword());
        user.setUserid(UUID.randomUUID().toString());
        int save = usermapper.insert(user);
        return save;
    }

    @Override
    public SysUser findbyUsername(String username) {
        LambdaQueryWrapper<SysUser> sysUserLambdaQueryWrapper = new LambdaQueryWrapper<>();
        sysUserLambdaQueryWrapper.eq(SysUser::getUsername,username);
        return usermapper.selectOne(sysUserLambdaQueryWrapper);
    }

    @Override
    public void saveUserRole(String uid, String[] ids) {
        //根据用户ID删除sys_role_user里面的数据
        this.roleMapper.deleteRoleUserByUid(uid);
        if(null!=ids&&ids.length>0) {
            for (String rid : ids) {
                this.roleMapper.insertUserRole(uid,rid);
            }
        }
    }

}
