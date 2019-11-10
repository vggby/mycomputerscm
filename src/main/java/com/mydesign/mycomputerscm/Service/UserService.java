package com.mydesign.mycomputerscm.Service;

import com.mydesign.mycomputerscm.domain.User;

public interface UserService {
    User UserLogin(User user);
    int SaveUser(User user);
}
