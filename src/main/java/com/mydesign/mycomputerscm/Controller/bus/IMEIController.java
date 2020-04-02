package com.mydesign.mycomputerscm.Controller.bus;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mydesign.mycomputerscm.Service.bus.IMEIService;
import com.mydesign.mycomputerscm.domain.DataGridView;
import com.mydesign.mycomputerscm.domain.ResultInfo;
import com.mydesign.mycomputerscm.domain.bus.IMEI;
import com.mydesign.mycomputerscm.vo.bus.IMEIVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
@Controller
@RequestMapping("IMEI")
public class IMEIController {
    @Autowired
    private IMEIService iMEIservice;
    /**
     * 查询
     */
    @PostMapping("loadAllIMEI")
    @ResponseBody
    public DataGridView loadAllIMEI( IMEIVo iMEIVo) {
        IPage<IMEI> page=new Page<>(iMEIVo.getPage(), iMEIVo.getLimit());
        LambdaQueryWrapper<IMEI> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.like((StringUtils.isNotBlank(iMEIVo.getOrderId())),IMEI::getOrderId , iMEIVo.getOrderId());
        iMEIservice.page(page, queryWrapper);
        List<IMEI> list = page.getRecords();
        return new DataGridView(page.getTotal(), page.getRecords());
    }
    /**
     * 删除
     */
    @PostMapping("/deleteIMEI")
    @ResponseBody
    public ResultInfo deleteIMEI(String iMEI) {
        LambdaQueryWrapper<IMEI> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.eq(IMEI::getImei,iMEI);
        iMEIservice.remove(queryWrapper);
        ResultInfo resultInfo = new ResultInfo();
        resultInfo.setFlag(true);
        resultInfo.setErrorMsg("删除成功");
        return resultInfo;
    }
    /**
     * 添加
     */
    @PostMapping("/addIMEI")
    @ResponseBody
    public ResultInfo  SaveBrand( IMEI iMEI) {

        boolean falg = iMEIservice.save(iMEI);
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
}