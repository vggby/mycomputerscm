package com.mydesign.mycomputerscm.Service;

import com.mydesign.mycomputerscm.domain.SysUser;

public interface UserService {
   /* User UserLogin(User user);*/
   int SaveUser(SysUser user);
    SysUser findbyUsername(String username);

}
