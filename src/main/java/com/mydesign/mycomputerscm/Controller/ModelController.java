package com.mydesign.mycomputerscm.Controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mydesign.mycomputerscm.Service.ModelService;
import com.mydesign.mycomputerscm.domain.DataGridView;
import com.mydesign.mycomputerscm.domain.Model;
import com.mydesign.mycomputerscm.domain.ResultInfo;
import com.mydesign.mycomputerscm.vo.ModelVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("model")
public class ModelController {
    @Autowired
    private ModelService modelService;

    /**
     * 查询
     */
    @PostMapping("loadAllModel")
    @ResponseBody
    public DataGridView loadAllModel(@RequestBody ModelVo modelVo) {
        IPage<Model> page=new Page<>(modelVo.getPage(), modelVo.getLimit());
        LambdaQueryWrapper<Model> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(modelVo.getBrand()),  Model::getBrand, modelVo.getBrand());


        this.modelService.page(page, queryWrapper);
        List<Model> list = page.getRecords();

        return new DataGridView(page.getTotal(), page.getRecords());
    }
    /**
     * 删除
     */
    @PostMapping("/deleteModel")
    @ResponseBody
    public ResultInfo deleteModel(String id) {
        LambdaQueryWrapper<Model> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.eq(Model::getId,id);
        modelService.remove(queryWrapper);
        ResultInfo resultInfo = new ResultInfo();
        resultInfo.setFlag(true);
        resultInfo.setErrorMsg("删除成功");
        return resultInfo;
    }
    /**
     * 添加
     */
    @PostMapping("/addModel")
    @ResponseBody
    public ResultInfo  SaveEmp( Model model) {

        boolean falg = modelService.save(model);
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
    @PostMapping("/editModel")
    @ResponseBody
    public ResultInfo editModel(Model model) {

        LambdaQueryWrapper<Model> menuLambdaQueryWrapper = new LambdaQueryWrapper<>();
        menuLambdaQueryWrapper.eq(Model::getId,model.getId());
        boolean flag = modelService.update(model,menuLambdaQueryWrapper);
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
     * 加载所有可用的
     */
    @PostMapping("loadAllModelForSelect")
    @ResponseBody
    public DataGridView loadAllBrandForSelect() {
        QueryWrapper<Model> queryWrapper=new QueryWrapper<>();
        List<Model> list = this.modelService.list(queryWrapper);
        return new DataGridView(list);
    }

}
