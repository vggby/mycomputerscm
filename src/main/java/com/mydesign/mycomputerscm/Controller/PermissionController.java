package com.mydesign.mycomputerscm.Controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mydesign.mycomputerscm.Service.MenuService;
import com.mydesign.mycomputerscm.domain.DataGridView;
import com.mydesign.mycomputerscm.domain.Menu;
import com.mydesign.mycomputerscm.domain.ResultInfo;
import com.mydesign.mycomputerscm.domain.TreeNode;
import com.mydesign.mycomputerscm.vo.MenuVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("permission")
public class PermissionController {
    @Autowired
    private MenuService menuService;
    /**
     * 查询
     */
    @PostMapping("loadAllMenu")
    @ResponseBody
    public DataGridView loadAllMenu(@RequestBody MenuVo menuVo) {
        IPage<Menu> page=new Page<>(menuVo.getPage(), menuVo.getLimit());
        LambdaQueryWrapper<Menu> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.eq(menuVo.getMenuId()!=null, Menu::getMenuId, menuVo.getMenuId()).or().eq(menuVo.getMenuId()!=null,Menu::getParentId, menuVo.getMenuId());
        queryWrapper.eq(Menu::getMenuType,"permission");//只能查询菜单
        queryWrapper.like(StringUtils.isNotBlank(menuVo.getMenuName()), Menu::getMenuName, menuVo.getMenuName());
        queryWrapper.orderByAsc( Menu::getSortOrder);
        this.menuService.page(page, queryWrapper);
        return new DataGridView(page.getTotal(), page.getRecords());
    }
    /**
     * 删除
     */
    @PostMapping("/deletepermission")
    @ResponseBody
    public ResultInfo deleteMission(String menuId) {
        LambdaQueryWrapper<Menu> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.eq(Menu::getMenuId,menuId);
        menuService.remove(queryWrapper);
        ResultInfo resultInfo = new ResultInfo();
        resultInfo.setFlag(true);
        resultInfo.setErrorMsg("删除成功");
        return resultInfo;
    }
    /**
     * 添加
     */
    @PostMapping("/addPermission")
    @ResponseBody
    public ResultInfo  SavePermission( Menu menu) {
        menu.setMenuType("permission");
        boolean falg = menuService.save(menu);
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
    @PostMapping("/editPermission")
    @ResponseBody
    public ResultInfo EditPermission(Menu menu) {

        LambdaQueryWrapper<Menu> menuLambdaQueryWrapper = new LambdaQueryWrapper<>();
        menuLambdaQueryWrapper.eq(Menu::getMenuId,menu.getMenuId());
        boolean flag = menuService.update(menu,menuLambdaQueryWrapper);
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
     * 加载菜单管理左边的菜单树的json
     */     @ResponseBody
            @PostMapping("loadMenuManagerLeftTreeJson")
            public DataGridView loadMenuManagerLeftTreeJson(MenuVo permissionVo) {
                LambdaQueryWrapper<Menu> queryWrapper=new LambdaQueryWrapper<>();
                queryWrapper.eq(Menu::getMenuType, "menu");
                List<Menu> list = menuService.list(queryWrapper);
                List<TreeNode> treeNodes=new ArrayList<>();
                for (Menu menu : list) {
                        treeNodes.add(new TreeNode(menu.getMenuId(), menu.getParentId(), menu.getMenuName(),true));
                    }
        DataGridView dataGridView = new DataGridView(treeNodes);
        dataGridView.setMsg("操作成功");
        return dataGridView;
    }

    /**
     * 加载最大的排序码
     *
     */
    @GetMapping("loadMaxOrderNum")
    @ResponseBody
    public Map<String,Object> loadMenuMaxOrderNum(){
        Map<String, Object> map=new HashMap<String, Object>();

        QueryWrapper<Menu> queryWrapper=new QueryWrapper<>();
        queryWrapper.orderByDesc("sortOrder");
        IPage<Menu> page=new Page<>(1, 1);
        List<Menu> list = this.menuService.page(page, queryWrapper).getRecords();
        if(list.size()>0) {
            map.put("value", list.get(0).getSortOrder()+1);
        }else {
            map.put("value", 1);
        }
        return map;
    }

}
