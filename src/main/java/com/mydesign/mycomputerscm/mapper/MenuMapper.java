package com.mydesign.mycomputerscm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mydesign.mycomputerscm.domain.Menu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MenuMapper extends BaseMapper<Menu> {
    /**
     * 根据角色ID查询菜单
     *
     * @param roleId 角色ID
     * @return 菜单列表
     */
    public List<Menu>  selectMenusByUserId(@Param("user_id")String userId);
}
