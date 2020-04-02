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
@RequestMapping("/system")
public class SystemController {
    @Autowired
    private MenuService menuService;
/*跳转到登录界面*/
    @GetMapping("/login")
    public String loginUI() {

        return "system/index/userlogin";
    }
    /*跳转到主界面*/
    @GetMapping("/center")
    public String centerUI(ModelMap mmap) {
        SysUser user = ShiroUtils.getSysUser();
        List<Menu> tree = menuService.selectMenusByUser(user);
        mmap.put("menus", tree);
        mmap.put("user", user);

        return "system/index/index";
    }
    /*跳转到role管理界面*/
    @GetMapping("/roleManager")
    public String listRole() {

        return "system/role/roleManager";
    }
    /*跳转到role管理界面*/
    @GetMapping("/permissionManager")
    public String permission() {

        return "system/permission/permissionManager";
    }
    @GetMapping("/menuManager")
    public  String Menu(){
        return "system/menu/menuManager";
    }
    /*跳转到dept管理界面*/
    @GetMapping("/deptManager")
    public String godept() {

        return "system/dept/deptManager";
    }
    /*跳转到emp管理界面*/
    @GetMapping("/empManager")
    public String goemp() {
        return "system/emp/empManager";
    }
    /*跳转到emp管理界面*/
    @GetMapping("/providerManager")
    public String gosupplier() {
        return "system/provider/providerManager";
    }
    /*跳转到emp管理界面*/
    @GetMapping("/customerManager")
    public String gocustomer() {
        return "system/customer/customerManager";
    }
    /*跳转到入库管理界面*/
    @GetMapping("/computerStorageManager")
    public String gocomputerStorageManager() {
        return "bus/computerstorage/computerStorageManager";
    }
    /*跳转到品牌管理界面*/
    @GetMapping("/brandManager")
    public String gobrandManager() {
        return "system/brand/brandManager";
    }
    @GetMapping("/modelManager")
    public String gomodelManager() {
        return "system/model/modelManager";
    }

    @GetMapping("/computerReturnManager")
    public String gocomputerReturnManager() {
        return "bus/computerreturn/computerReturnManager";
    }
}
