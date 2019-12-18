package com.mydesign.mycomputerscm.Controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mydesign.mycomputerscm.Querydomain.queryRole;
import com.mydesign.mycomputerscm.Service.RoleService;
import com.mydesign.mycomputerscm.domain.ResultInfo;
import com.mydesign.mycomputerscm.domain.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/system")
public class RoleController {

    @Autowired
    private RoleService rolesrvice;

    private Integer[] status;

    @GetMapping("/getrole")
    public String listRole() {
        if (status == null) {
            status = new Integer[]{Role.ROLESTATE_ENABLE, Role.ROLESTATE_DISABLE};

        }
        return "system/form";
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
    public Map queryrolelist(@RequestBody Map<String, Object> params) { /* @RequestBody queryRole qyeryrole*/
        System.out.println(params);
        System.out.println(params.get("status"));
        List<Integer> status  = new ArrayList<>();
        try{
            ArrayList<Integer> status1 = (ArrayList<Integer>) params.get("status");
            status=status1;

        }catch (Exception e){
            Integer a = Integer.parseInt((String) params.get("status")) ;
            status.add(a);
        };

        Integer limit = (Integer) params.get("limit");
        Integer offset = (Integer) params.get("offset");
        String role_name = (String) params.get("role_name");
        queryRole qyeryrole = new queryRole();
        qyeryrole.setStatus(status);
        qyeryrole.setLimit(limit);
        qyeryrole.setOffset(offset);
        qyeryrole.setRole_name(role_name);
        Page<Role> all = rolesrvice.findAll(qyeryrole);

        Map<String,Object> map=new HashMap<String,Object>();
        map.put("code",0);
        map.put("msg","");
        map.put("count",all.getSize());
        map.put("data",all.getRecords());


        return map;

    }

    @GetMapping("/saverole")
    public String  saveroleUI() {

        return "users/saverole";
    }

    @PostMapping("/saverole")
    @ResponseBody
    public ResultInfo  saverole( Role role) {
        int addRole = rolesrvice.addRole(role);
        ResultInfo resultInfo = new ResultInfo();
        if (addRole!=0){
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
        int addRole = rolesrvice.addRole(role);
        ResultInfo resultInfo = new ResultInfo();
        if (addRole!=0){
            resultInfo.setFlag(true);
            resultInfo.setErrorMsg("修改成功");

        }else{
            resultInfo.setFlag(false);
            resultInfo.setErrorMsg("修改失败");
        }
        return resultInfo;
    }

}

