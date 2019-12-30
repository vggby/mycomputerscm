package com.mydesign.mycomputerscm.Controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mydesign.mycomputerscm.Querydomain.queryRole;
import com.mydesign.mycomputerscm.Service.MenuService;
import com.mydesign.mycomputerscm.Service.RoleService;
import com.mydesign.mycomputerscm.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService rolesrvice;
    @Autowired
    private MenuService menuService;
    private Integer[] status;





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
        }

        Integer limit = (Integer) params.get("limit");
        Integer offset = (Integer) params.get("offset");
        String role_name = (String) params.get("roleName");
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
    public ResultInfo  deleteRole( String roleId) {
        rolesrvice.deleteRole(roleId);
        ResultInfo resultInfo = new ResultInfo();
        resultInfo.setFlag(true);
        resultInfo.setErrorMsg("删除成功");
        return resultInfo;
    }

    @PostMapping("/editrole")
    @ResponseBody
    public ResultInfo editRole(Role role) {
        int addRole = rolesrvice.updateRole(role);
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
    /**
     * 保存角色和菜单权限之间的关系
     */
    @RequestMapping("saveRolePermission")
    @ResponseBody
    public ResultInfo saveRolePermission(String roleId,String[] ids) {
        ResultInfo resultInfo = new ResultInfo();

        try {
            rolesrvice.saveRolePermission(roleId,ids);
            resultInfo.setFlag(true);
            resultInfo.setErrorMsg("保存成功");
            return resultInfo;
        } catch (Exception e) {
            e.printStackTrace();
            resultInfo.setErrorMsg("保存错误");
            resultInfo.setFlag(false);
            return resultInfo;

        }
    }
    /**
     * 根据角色ID加载菜单和权限的树的json串
     */
    @GetMapping("initPermissionByRoleId")
    @ResponseBody
    public DataGridView initPermissionByRoleId(String roleId) {
        /*1查询所有菜单和权限*/
        LambdaQueryWrapper<Menu> queryWrapper=new LambdaQueryWrapper<>();

        List<Menu> allPermissions = menuService.list();
        /**
         * 2,根据角色ID查询当前角色拥有的所有的权限或菜单ID
         */
        List<String> currentRolePermissions=rolesrvice.queryRolePermissionIdsByRid(roleId);
        List<Menu> menulist=new ArrayList<>();
        if (currentRolePermissions!=null && currentRolePermissions.size()>0){
            queryWrapper.in(Menu::getMenuId, currentRolePermissions);
            menulist = menuService.list(queryWrapper);
        }
        //构造 List<TreeNode>
        List<TreeNode> nodes=new ArrayList<>();
        for (Menu p1 : allPermissions) {
            String checkArr="0";
            for (Menu p2 : menulist) {
                if(p1.getMenuId().equals(p2.getMenuId())) {
                    checkArr="1";
                    break;
                }
            }
            Boolean spread=true;
            nodes.add(new TreeNode(p1.getMenuId(), p1.getParentId(), p1.getMenuName(), spread, checkArr));
        }
        return new DataGridView(nodes);
    }

}

