package com.mydesign.mycomputerscm.Service;

import com.mydesign.mycomputerscm.domain.Role;
import com.mydesign.mycomputerscm.domain.SysUser;

public interface UserService {
   /* User UserLogin(User user);*/
    SysUser SaveUser(SysUser user);
    SysUser findbyUsername(String username);

}
