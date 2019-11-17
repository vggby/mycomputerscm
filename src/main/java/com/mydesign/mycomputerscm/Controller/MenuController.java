package com.mydesign.mycomputerscm.Controller;

import com.mydesign.mycomputerscm.domain.Menu;
import org.springframework.web.bind.annotation.RequestMapping;

public class MenuController {
    @RequestMapping("/getMenu")
    public void getMenu(){
            Menu rootMenu = new Menu();
        rootMenu.setMenuId("root");
        rootMenu.setMenuName("系统");

    }
}
