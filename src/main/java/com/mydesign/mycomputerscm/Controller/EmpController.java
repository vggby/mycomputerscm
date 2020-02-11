package com.mydesign.mycomputerscm.Controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mydesign.mycomputerscm.Service.DeptService;
import com.mydesign.mycomputerscm.Service.RoleService;
import com.mydesign.mycomputerscm.Service.UserService;
import com.mydesign.mycomputerscm.domain.*;
import com.mydesign.mycomputerscm.vo.SysUserVo;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("emp")
public class EmpController {
    @Autowired
    private UserService empService;
    @Autowired
    private DeptService deptService;
    @Autowired
    private RoleService roleService;
    /**
     * 查询
     */
    @PostMapping("loadAllemp")
    @ResponseBody
    public DataGridView loadAllEmp(@RequestBody SysUserVo sysUserVo) {
        IPage<SysUser> page=new Page<>(sysUserVo.getPage(), sysUserVo.getLimit());
        LambdaQueryWrapper<SysUser> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.eq(sysUserVo.getDid()!=null,SysUser::getDid, sysUserVo.getDid());
        queryWrapper.like(StringUtils.isNotBlank(sysUserVo.getDid()), SysUser::getDid, sysUserVo.getDid());
        queryWrapper.like(StringUtils.isNotBlank(sysUserVo.getEmpName()), SysUser::getEmpName, sysUserVo.getEmpName());
        this.empService.page(page, queryWrapper);
        List<SysUser> list = page.getRecords();
        for (SysUser user : list) {
            String deptid = user.getDid();
            if(StringUtils.isNotBlank(deptid)) {
                LambdaQueryWrapper<Dept> deptqueryWrapper=new LambdaQueryWrapper<>();
                deptqueryWrapper.eq(Dept::getDeptId, deptid);
                Dept one = deptService.getOne(deptqueryWrapper);
                user.setDeptname(one.getDeptName());
            }

        }


        return new DataGridView(page.getTotal(), page.getRecords());
    }
    /**
     * 删除
     */
    @PostMapping("/deleteEmp")
    @ResponseBody
    public ResultInfo deleteEmp(String userId) {
        LambdaQueryWrapper<SysUser> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.eq(SysUser::getUserid,userId);
        empService.remove(queryWrapper);
        ResultInfo resultInfo = new ResultInfo();
        resultInfo.setFlag(true);
        resultInfo.setErrorMsg("删除成功");
        return resultInfo;
    }
    /**
     * 添加
     */
    @PostMapping("/addEmp")
    @ResponseBody
    public ResultInfo  SaveEmp( SysUser emp) {

        boolean falg = empService.save(emp);
        ResultInfo resultInfo = new ResultInfo();
        if (falg){
            resultInfo.setFlag(true);
            resultInfo.setErrorMsg("添加成功");

        }else{
            resultInfo.setFlag(false);
            resultInfo.setErrorMsg("添加失败");
        }
        return resultInfo;
    }
    /**
     * 修改
     */
    @PostMapping("/editEmp")
    @ResponseBody
    public ResultInfo EditEmp(SysUser emp) {

        LambdaQueryWrapper<SysUser> menuLambdaQueryWrapper = new LambdaQueryWrapper<>();
        menuLambdaQueryWrapper.eq(SysUser::getUserid,emp.getUserid());
        boolean flag = empService.update(emp,menuLambdaQueryWrapper);
        ResultInfo resultInfo = new ResultInfo();
        if (flag){
            resultInfo.setFlag(true);
            resultInfo.setErrorMsg("修改成功");

        }else{
            resultInfo.setFlag(false);
            resultInfo.setErrorMsg("修改失败");
        }
        return resultInfo;
    }
    /**
     * creat
     */
    @PostMapping("/create")
    @ResponseBody
    public ResultInfo creat(SysUser emp) {
        String pw = new SimpleHash("MD5",emp.getPassword(),emp.getUsername() + "salt",2).toString();
        emp.setPassword(pw);
        LambdaQueryWrapper<SysUser> menuLambdaQueryWrapper = new LambdaQueryWrapper<>();
        menuLambdaQueryWrapper.eq(SysUser::getUserid,emp.getUserid());
        boolean flag = empService.update(emp,menuLambdaQueryWrapper);
        ResultInfo resultInfo = new ResultInfo();
        if (flag){
            resultInfo.setFlag(true);
            resultInfo.setErrorMsg("创建成功");

        }else{
            resultInfo.setFlag(false);
            resultInfo.setErrorMsg("创建失败");
        }
        return resultInfo;
    }
    /**
     * 加载菜单管理左边的菜单树的json
     */     @ResponseBody
            @PostMapping("loadMenuManagerLeftTreeJson")
            public DataGridView loadMenuManagerLeftTreeJson() {
                LambdaQueryWrapper<Dept> queryWrapper=new LambdaQueryWrapper<>();
                List<Dept> list = deptService.list(queryWrapper);
                List<TreeNode> treeNodes=new ArrayList<>();
                for (Dept dept : list) {
                        treeNodes.add(new TreeNode(dept.getDeptId(), dept.getDeptName(),true));
                    }
        DataGridView dataGridView = new DataGridView(treeNodes);
        dataGridView.setMsg("操作成功");
        return dataGridView;
    }

    /**
     * 根据用户ID查询角色并选中已拥有的角色
     */
    @RequestMapping("initRoleByUserId")
    @ResponseBody
    public DataGridView initRoleByUserId(String userId) {
        //1,查询所有可用的角色
        List<Map<String, Object>> listMaps = this.roleService.listMaps();
        //2,查询当前用户拥有的角色ID集合
        List<String> currentUserRoleIds=this.roleService.queryUserRoleIdsByUid(userId);
        for (Map<String, Object> map : listMaps) {
            Boolean LAY_CHECKED=false;
            String roleId=(String) map.get("role_id");
            for (String rid : currentUserRoleIds) {
                if(rid.equals(roleId)) {
                    LAY_CHECKED=true;
                    break;
                }
            }
            map.put("LAY_CHECKED", LAY_CHECKED);
        }
        return new DataGridView(Long.valueOf(listMaps.size()), listMaps);
    }
    /**
     * 保存用户和角色的关系
     */
    @RequestMapping("saveUserRole")
    @ResponseBody
    public ResultInfo saveUserRole(String uid,String[] ids) {
        ResultInfo resultInfo = new ResultInfo();
        try {
            this.empService.saveUserRole(uid,ids);
            resultInfo.setFlag(true);
            resultInfo.setErrorMsg("分配成功");
            return resultInfo;
        } catch (Exception e) {
            e.printStackTrace();
            resultInfo.setFlag(false);
            resultInfo.setErrorMsg("分配失败");
            return resultInfo;
        }

    }

}
