package com.mydesign.mycomputerscm.Service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mydesign.mycomputerscm.domain.Menu;
import com.mydesign.mycomputerscm.domain.SysUser;

import java.util.List;

public interface MenuService extends IService<Menu>{

    /**
     * 根据用户ID查询菜单
     *
     * @param user 用户信息
     * @return 菜单列表
     */
    public List<Menu> selectMenusByUser(SysUser user) ;
    public List<Menu> selectpermissionByUserId(SysUser user);
}
