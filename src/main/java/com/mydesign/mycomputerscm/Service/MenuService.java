package com.mydesign.mycomputerscm.Service;

import com.mydesign.mycomputerscm.domain.Menu;
import com.mydesign.mycomputerscm.domain.SysUser;

import java.util.List;

public interface MenuService {

    /**
     * 根据用户ID查询菜单
     *
     * @param user 用户信息
     * @return 菜单列表
     */
    public List<Menu> selectMenusByUser(SysUser user);
}
