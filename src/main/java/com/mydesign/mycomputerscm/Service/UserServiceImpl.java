package com.mydesign.mycomputerscm.Service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mydesign.mycomputerscm.domain.SysUser;
import com.mydesign.mycomputerscm.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper usermapper;

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

}
