package com.mydesign.mycomputerscm.Controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mydesign.mycomputerscm.Service.BrandService;
import com.mydesign.mycomputerscm.domain.Brand;
import com.mydesign.mycomputerscm.domain.DataGridView;
import com.mydesign.mycomputerscm.domain.ResultInfo;
import com.mydesign.mycomputerscm.vo.BrandVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("brand")
public class BrandController {
    @Autowired
    private BrandService brandservice;

    /**
     * 查询
     */
    @PostMapping("loadAllBrand")
    @ResponseBody
    public DataGridView loadAllBrand(@RequestBody BrandVo brandVo) {
        IPage<Brand> page=new Page<>(brandVo.getPage(), brandVo.getLimit());
        LambdaQueryWrapper<Brand> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(brandVo.getBrand()),Brand::getBrand , brandVo.getBrand());
        brandservice.page(page, queryWrapper);
        List<Brand> list = page.getRecords();
        return new DataGridView(page.getTotal(), page.getRecords());
    }
    /**
     * 删除
     */
    @PostMapping("/deleteBrand")
    @ResponseBody
    public ResultInfo deleteBrand(String id) {
        LambdaQueryWrapper<Brand> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.eq(Brand::getId,id);
        brandservice.remove(queryWrapper);
        ResultInfo resultInfo = new ResultInfo();
        resultInfo.setFlag(true);
        resultInfo.setErrorMsg("删除成功");
        return resultInfo;
    }
    /**
     * 添加
     */
    @PostMapping("/addBrand")
    @ResponseBody
    public ResultInfo  SaveBrand( Brand brand) {

        boolean falg = brandservice.save(brand);
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
     * 加载所有可用的Brand
     */
    @PostMapping("loadAllBrandForSelect")
    @ResponseBody
    public DataGridView loadAllBrandForSelect() {
        QueryWrapper<Brand> queryWrapper=new QueryWrapper<>();
        List<Brand> list = this.brandservice.list(queryWrapper);
        return new DataGridView(list);
    }



}
