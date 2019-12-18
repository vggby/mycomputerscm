package com.mydesign.mycomputerscm.Controller;

import com.mydesign.mycomputerscm.Service.MenuService;
import com.mydesign.mycomputerscm.ShiroUtils;
import com.mydesign.mycomputerscm.domain.Menu;
import com.mydesign.mycomputerscm.domain.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("")
public class MenuController {
    @Autowired
    private MenuService menuService;
    @GetMapping("/getMenu")
    public  String getMenu(ModelMap mmap){
        SysUser user = ShiroUtils.getSysUser();
        List<Menu> tree = menuService.selectMenusByUser(user);
        mmap.put("menus", tree);
        mmap.put("user", user);

        return "fragments/left";


    }
}
