package com.mydesign.mycomputerscm.Service;

import com.mydesign.mycomputerscm.domain.SysUser;
import com.mydesign.mycomputerscm.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper usermapper;

    @Override
    public SysUser SaveUser(SysUser user) {
        user.setPassword(user.getPassword());
        user.setUserid(UUID.randomUUID().toString());
        SysUser save = usermapper.save(user);
        return save;
    }

    @Override
    public SysUser findbyUsername(String username) {
        return usermapper.findByUsername(username);
    }

}
