package com.mydesign.mycomputerscm.Controller.sto;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mydesign.mycomputerscm.Service.sto.IStoComstockService;
import com.mydesign.mycomputerscm.Service.sto.ISysBaosunService;
import com.mydesign.mycomputerscm.domain.DataGridView;
import com.mydesign.mycomputerscm.domain.ResultInfo;
import com.mydesign.mycomputerscm.domain.sto.StoComstock;
import com.mydesign.mycomputerscm.domain.sto.SysBaosun;
import com.mydesign.mycomputerscm.vo.bus.IMEIVo;
import com.mydesign.mycomputerscm.vo.st.StoreVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


@Controller
@RequestMapping("store")
public class StoComstockController {
    @Autowired
    private IStoComstockService stoComstockService;
    @Autowired
    private ISysBaosunService baosunService;
    /**
     * 查询
     */
    @PostMapping("loadStore")
    @ResponseBody
    public DataGridView loadStore(@RequestBody StoreVo toComstock) {
        LambdaQueryWrapper<StoComstock> stoWrapper = new LambdaQueryWrapper<>();
        stoWrapper.like(StringUtils.isNotBlank(toComstock.getComputerType()),StoComstock::getComputerType,toComstock.getComputerType());
        IPage<StoComstock> page=new Page<>(toComstock.getPage(), toComstock.getLimit());
        IPage<StoComstock> selectstore = stoComstockService.selectstore(page,stoWrapper);
         return new DataGridView(page.getTotal(), page.getRecords());
    }



    @PostMapping("FindStore")
    @ResponseBody
    public DataGridView FindStore( StoreVo toComstock) {
        IPage<StoComstock> page=new Page<>(toComstock.getPage(), toComstock.getLimit());
        LambdaQueryWrapper<StoComstock> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(toComstock.getComputerType()),  StoComstock::getComputerType, toComstock.getComputerType());


         stoComstockService.page(page, queryWrapper);
        List<StoComstock> list = page.getRecords();

        return new DataGridView(page.getTotal(), page.getRecords());
    }


    @PostMapping("LoadScrapStore")
    @ResponseBody
    public DataGridView LoadScrapStore(@RequestBody StoreVo toComstock) {
        IPage<StoComstock> page=new Page<>(toComstock.getPage(), toComstock.getLimit());
        LambdaQueryWrapper<StoComstock> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(toComstock.getComputerType()),  StoComstock::getComputerType, toComstock.getComputerType());
        queryWrapper.like(StringUtils.isNotBlank(toComstock.getImei()),  StoComstock::getImei, toComstock.getImei());

        stoComstockService.page(page, queryWrapper);
        List<StoComstock> list = page.getRecords();

        return new DataGridView(page.getTotal(), page.getRecords());
    }

    @PostMapping("baofei")
    @ResponseBody
    public ResultInfo baofei(StoreVo toComstock) {
        ResultInfo resultInfo = new ResultInfo();
        StoComstock toComstock1 = stoComstockService.getById(toComstock.getId());

        BeanUtils.copyProperties(toComstock1,toComstock);
        if("报废".equals(toComstock.getRemark())){
            resultInfo.setFlag(false);
            resultInfo.setErrorMsg("已报废");
        }

        SysBaosun sysBaosun = new SysBaosun();
        sysBaosun.setImei(toComstock.getImei());
        sysBaosun.setLossprice(toComstock.getLossamount());
        sysBaosun.setNumber(1);
        sysBaosun.setLossamount(toComstock.getLossamount());
        boolean falg = baosunService.save(sysBaosun);
        toComstock.setRemark("报废");
        toComstock.setLossamount(toComstock.getPrice());
        LambdaQueryWrapper<StoComstock>Wrapper = new LambdaQueryWrapper<>();
        Wrapper.eq(StoComstock::getOrderId,toComstock.getOrderId());

        boolean update = stoComstockService.update(toComstock,Wrapper);

        if (falg&&update){
            resultInfo.setFlag(true);
            resultInfo.setErrorMsg("报废成功");

        }else{
            resultInfo.setFlag(false);
            resultInfo.setErrorMsg("报废失败");
        }
        return resultInfo;
    }


    @PostMapping("loadAllIMEI")
    @ResponseBody
    public DataGridView loadAllIMEI( IMEIVo iMEIVo) {
        IPage<StoComstock> page=new Page<>(iMEIVo.getPage(), iMEIVo.getLimit());
        LambdaQueryWrapper<StoComstock> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.like((org.apache.commons.lang3.StringUtils.isNotBlank(iMEIVo.getOrderId())),StoComstock::getOrderId , iMEIVo.getOrderId());
        stoComstockService.page(page, queryWrapper);
        List<StoComstock> list = page.getRecords();
        return new DataGridView(page.getTotal(), page.getRecords());
    }

    @PostMapping("baosun")
    @ResponseBody
    public ResultInfo baosun(StoreVo toComstock) {
        ResultInfo resultInfo = new ResultInfo();
        SysBaosun sysBaosun = new SysBaosun();
        StoComstock toComstock1 = stoComstockService.getById(toComstock.getId());
        toComstock1.setLossamount(toComstock.getLossamount());
        BeanUtils.copyProperties(toComstock1,toComstock);
        if("报废".equals(toComstock.getRemark())){
            resultInfo.setFlag(false);
            resultInfo.setErrorMsg("已报废不能报损");
        }
        sysBaosun.setImei(toComstock.getImei());
        sysBaosun.setLossprice(toComstock.getLossamount());
        sysBaosun.setNumber(1);
        sysBaosun.setLossamount(toComstock.getLossamount());

        LambdaQueryWrapper<SysBaosun>baosunswrapper = new LambdaQueryWrapper<>();
        boolean falg=false;
        baosunswrapper.eq(SysBaosun::getImei,toComstock.getImei());
        int count = baosunService.count(baosunswrapper);
        if(count>0){
            falg= baosunService.update(sysBaosun,baosunswrapper);
        }else {
            falg= baosunService.save(sysBaosun);
        }


        toComstock.setRemark("报损");
        toComstock.setLossamount(toComstock.getLossamount());
        LambdaQueryWrapper<StoComstock>Wrapper = new LambdaQueryWrapper<>();
        Wrapper.eq(StoComstock::getOrderId,toComstock.getOrderId());

        stoComstockService.update(toComstock,Wrapper);

        if (falg){
            resultInfo.setFlag(true);
            resultInfo.setErrorMsg("报损成功");

        }else{
            resultInfo.setFlag(false);
            resultInfo.setErrorMsg("报损失败");
        }
        return resultInfo;
    }
}
