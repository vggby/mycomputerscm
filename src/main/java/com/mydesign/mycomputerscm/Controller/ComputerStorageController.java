package com.mydesign.mycomputerscm.Controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mydesign.mycomputerscm.Service.ComputerstorageService;
import com.mydesign.mycomputerscm.Service.ProviderService;
import com.mydesign.mycomputerscm.domain.*;
import com.mydesign.mycomputerscm.vo.CInportVo;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;



@Controller
@RequestMapping("computerstorage")
public class ComputerStorageController {
    @Autowired
    private ComputerstorageService computerstorageService;

    @Autowired
    private ProviderService providerService;


    /**
     * 查询
     */
    @PostMapping("loadAllInport")
    @ResponseBody
    public DataGridView loadAllInport(CInportVo inportVo) {
        IPage<ComputerStorage> page = new Page<>(inportVo.getPage(), inportVo.getLimit());
        QueryWrapper<ComputerStorage> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(inportVo.getProviderId()!=null&&inportVo.getProviderId()!=0,"provider_id",inportVo.getProviderId());
        queryWrapper.like(StringUtils.isNotBlank(inportVo.getComputerType()), "computer_type", inportVo.getComputerType());
        queryWrapper.ge(inportVo.getStartTime()!=null, "entry_date", inportVo.getStartTime());
        queryWrapper.le(inportVo.getEndTime()!=null, "entry_date", inportVo.getEndTime());
        computerstorageService.page(page, queryWrapper);
        List<ComputerStorage> records = page.getRecords();
        for (ComputerStorage ComputerStorage : records) {
            Provider provider = this.providerService.getById(ComputerStorage.getProviderId());
            if(null!=provider) {
                ComputerStorage.setProvidername(provider.getProvidername());
            }

        }
        return new DataGridView(page.getTotal(), records);
    }


    /**
     * 添加
     */
    @PostMapping("/addInport")
    @ResponseBody
    public ResultInfo addInport(@RequestBody CInportVo inportVo) {
        ResultInfo resultInfo = new ResultInfo();
        //设置进货日期
        inportVo.setEntryDate(new Date());
        inportVo.setOrderId("DN-"+System.currentTimeMillis());

        String[] imeis = inportVo.getImeis();
        if (ArrayUtils.isEmpty(imeis)){
            inportVo.setNumber(0);
        }else {
            String[] strings = deleteArrayNull(imeis);
            inportVo.setNumber(strings.length);
        }

        inportVo.setAmount(inportVo.getPrice()*inportVo.getNumber());
        //取出user
        ActiverUser activerUser = (ActiverUser) SecurityUtils.getSubject().getPrincipal();
        SysUser user=activerUser.getUser();
        //设置进货人
        inportVo.setEntryOp(user.getUsername());

        boolean falg = computerstorageService.saveall(inportVo);
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
    @PostMapping("/updateInport")
    @ResponseBody
    public ResultInfo updateInport(@RequestBody CInportVo inportVo) {
        ResultInfo resultInfo = new ResultInfo();
        //取出user
        ActiverUser activerUser = (ActiverUser) SecurityUtils.getSubject().getPrincipal();
        SysUser user=activerUser.getUser();
        //设置进货人
        inportVo.setEntryOp(user.getUsername());


        //设置进货日期
        inportVo.setEntryDate(new Date());
        String[] imeis = inportVo.getImeis();
        if (ArrayUtils.isEmpty(imeis)){
            inportVo.setNumber(0);
        }else {
            String[] strings = deleteArrayNull(imeis);
            inportVo.setNumber(strings.length);
        }

        inportVo.setAmount(inportVo.getPrice()*inportVo.getNumber());
        boolean falg = this.computerstorageService.updateAll(inportVo);
        if (falg){
            resultInfo.setFlag(true);
            resultInfo.setErrorMsg("修改成功");

        }else{
            resultInfo.setFlag(false);
            resultInfo.setErrorMsg("修改失败");
        }
        return resultInfo;
    }
/**
 * 删除
 */
@PostMapping("/deleteInport")
@ResponseBody

public ResultInfo deleteInport(Integer id) {
    ResultInfo resultInfo = new ResultInfo();
    boolean falg =  this.computerstorageService.removeAll(id);
    if (falg){
        resultInfo.setFlag(true);
        resultInfo.setErrorMsg("删除成功");

    }else{
        resultInfo.setFlag(false);
        resultInfo.setErrorMsg("删除失败");
    }
    return resultInfo;
}
    /***
     * 去除String数组中的空值
     */
    private String[] deleteArrayNull(String string[]) {
        String strArr[] = string;

        // step1: 定义一个list列表，并循环赋值
        ArrayList<String> strList = new ArrayList<String>();
        for (int i = 0; i < strArr.length; i++) {
            strList.add(strArr[i]);
        }

        // step2: 删除list列表中所有的空值
        while (strList.remove(null));
        while (strList.remove(""));

        // step3: 把list列表转换给一个新定义的中间数组，并赋值给它
        String strArrLast[] = strList.toArray(new String[strList.size()]);

        return strArrLast;
    }
}