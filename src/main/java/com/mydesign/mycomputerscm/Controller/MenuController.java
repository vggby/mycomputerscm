package com.mydesign.mycomputerscm.Controller;

import com.mydesign.mycomputerscm.Querydomain.queryMenu;
import com.mydesign.mycomputerscm.Service.MenuService;
import com.mydesign.mycomputerscm.domain.Menu;
import com.mydesign.mycomputerscm.domain.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("")
public class MenuController {
    @Autowired
    private MenuService menuService;
    @GetMapping("/getMenu")
    @ResponseBody
    public  List<Menu> getMenu(){
        Menu rootMenu = new Menu();
        rootMenu.setMenuId("root");
        rootMenu.setMenuName("系统");
        Role role = new Role();

        role.setRoleId("1");

        List<Role>rolelist  = new ArrayList<>();
        rolelist.add(role);


        queryMenu queryMeny = new queryMenu();
        queryMeny.setMenuid("1");
        List<Menu> menuTree = menuService.getMenuTree(queryMeny, rolelist);

        return menuTree;

    }
}
