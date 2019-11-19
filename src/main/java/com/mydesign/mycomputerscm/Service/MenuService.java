package com.mydesign.mycomputerscm.Service;

import com.mydesign.mycomputerscm.Querydomain.queryMenu;
import com.mydesign.mycomputerscm.domain.Menu;
import com.mydesign.mycomputerscm.domain.Role;

import java.util.List;

public interface MenuService {
    List<Menu> getMenuTree(queryMenu querymenu, List<Role> roleList);
}
