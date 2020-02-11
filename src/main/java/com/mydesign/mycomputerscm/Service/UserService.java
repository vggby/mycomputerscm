package com.mydesign.mycomputerscm.Service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mydesign.mycomputerscm.domain.SysUser;

public interface UserService extends IService<SysUser> {
   /* User UserLogin(User user);*/
   int SaveUser(SysUser user);
    SysUser findbyUsername(String username);

    void saveUserRole(String uid, String[] ids);
}
