package com.mydesign.mycomputerscm.Controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mydesign.mycomputerscm.Service.DeptService;
import com.mydesign.mycomputerscm.domain.DataGridView;
import com.mydesign.mycomputerscm.domain.Dept;
import com.mydesign.mycomputerscm.domain.ResultInfo;
import com.mydesign.mycomputerscm.vo.DeptVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("dept")
public class DeptController {
    @Autowired
    private DeptService deptService;
    /**
     * 查询
     */
    @PostMapping("loadAllDept")
    @ResponseBody
    public DataGridView loadAllMenu(@RequestBody DeptVo deptVo) {
        IPage<Dept> page=new Page<>(deptVo.getPage(), deptVo.getLimit());
        LambdaQueryWrapper<Dept> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.eq(deptVo.getDeptId()!=null, Dept::getDeptId,deptVo.getDeptId());
        queryWrapper.like(StringUtils.isNotBlank(deptVo.getDeptName()), Dept::getDeptName, deptVo.getDeptName());
        deptService.page(page, queryWrapper);
        return new DataGridView(page.getTotal(), page.getRecords());
    }
    /**
     * 删除
     */
    @PostMapping("/deleteDept")
    @ResponseBody
    public ResultInfo deleteDept(String deptId) {
        LambdaQueryWrapper<Dept> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.eq(Dept::getDeptId,deptId);
        boolean flag = deptService.remove(queryWrapper);
        if(flag){
            ResultInfo resultInfo = new ResultInfo();
            resultInfo.setFlag(true);
            resultInfo.setErrorMsg("删除成功");
            return resultInfo;
        }else{
            ResultInfo resultInfo = new ResultInfo();
            resultInfo.setFlag(false);
            resultInfo.setErrorMsg("删除失败");
            return resultInfo;
        }

    }
    /**
     * 添加
     */
    @PostMapping("/addDept")
    @ResponseBody
    public ResultInfo  saverole( Dept dept) {

        boolean falg = deptService.save(dept);
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
    @PostMapping("/editDept")
    @ResponseBody
    public ResultInfo editRole(Dept dept) {

        LambdaQueryWrapper<Dept> deptLambdaQueryWrapper = new LambdaQueryWrapper<>();
        deptLambdaQueryWrapper.eq(Dept::getDeptId,dept.getDeptId());
        boolean flag = deptService.update(dept,deptLambdaQueryWrapper);
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


}
