package com.mydesign.mycomputerscm.Controller;

import com.mydesign.mycomputerscm.Querydomain.queryRole;
import com.mydesign.mycomputerscm.Service.RoleService;
import com.mydesign.mycomputerscm.domain.ResultInfo;
import com.mydesign.mycomputerscm.domain.Role;
import com.mydesign.mycomputerscm.mapper.RoleManaRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("")
public class RoleController {

    @Autowired
    private RoleService rolesrvice;

    private Integer[] status;

    @GetMapping("/getrole")
    public String listRole() {
        if (status == null) {
            status = new Integer[]{Role.ROLESTATE_ENABLE, Role.ROLESTATE_DISABLE};

        }
        return "users/RoleManalist";
    }


    @GetMapping("/editrole/{roleid}")
    public ModelAndView editRole(@PathVariable("roleid") String roleid) {
        System.out.println(roleid);
        ModelAndView mv=new ModelAndView();
        mv.setViewName("users/editrole");
        Role role = rolesrvice.findAllByroleid(roleid);
        mv.addObject("role", role);
        return mv;

    }
    @PostMapping("/queryrolelist")
    @ResponseBody
    public Page<Role> queryrolelist(@RequestBody queryRole qyeryrole) {
        Page<Role> all = rolesrvice.findAll(qyeryrole);
        return all;
    }

    @GetMapping("/saverole")
    public String  saveroleUI() {

        return "users/saverole";
    }

    @PostMapping("/saverole")
    @ResponseBody
    public ResultInfo  saverole( Role role) {
        Role addRole = rolesrvice.addRole(role);
        ResultInfo resultInfo = new ResultInfo();
        if (addRole!=null){
            resultInfo.setFlag(true);
            resultInfo.setErrorMsg("添加成功");

        }else{
            resultInfo.setFlag(false);
            resultInfo.setErrorMsg("添加失败");
        }
        return resultInfo;
    }

    @PostMapping("/deleterole")
    @ResponseBody
    public ResultInfo  deleteRole( String roleid) {
        rolesrvice.deleteRole(roleid);
        ResultInfo resultInfo = new ResultInfo();
        resultInfo.setFlag(true);
        resultInfo.setErrorMsg("删除成功");
        return resultInfo;
    }

    @PostMapping("/editrole")
    @ResponseBody
    public ResultInfo editRole(Role role) {
        Role addRole = rolesrvice.addRole(role);
        ResultInfo resultInfo = new ResultInfo();
        if (addRole!=null){
            resultInfo.setFlag(true);
            resultInfo.setErrorMsg("修改成功");

        }else{
            resultInfo.setFlag(false);
            resultInfo.setErrorMsg("修改失败");
        }
        return resultInfo;
    }

}

