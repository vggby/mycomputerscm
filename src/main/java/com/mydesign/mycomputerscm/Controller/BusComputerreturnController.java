package com.mydesign.mycomputerscm.Controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mydesign.mycomputerscm.Service.ComputerstorageService;
import com.mydesign.mycomputerscm.Service.IBusComputerreturnService;
import com.mydesign.mycomputerscm.domain.ActiverUser;
import com.mydesign.mycomputerscm.domain.ComputerStorage;
import com.mydesign.mycomputerscm.domain.ResultInfo;
import com.mydesign.mycomputerscm.domain.SysUser;
import com.mydesign.mycomputerscm.vo.COutportVo;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Date;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jobob
 * @since 2020-03-29
 */

@Controller
@RequestMapping("computerreturn")
public class BusComputerreturnController{
    @Autowired
    private IBusComputerreturnService computerreturnService;
    @Autowired
    private ComputerstorageService computerstorageService;
    /**
     * 添加
     */
    @PostMapping("/Outport")
    @ResponseBody
    public ResultInfo Outport(@RequestBody COutportVo inportVo) {

        LambdaQueryWrapper<ComputerStorage> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ComputerStorage::getOrderId,inportVo.getOrderId());
        ComputerStorage one = computerstorageService.getOne(wrapper);
        BeanUtils.copyProperties(one,inportVo);
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

        boolean falg = computerreturnService.thremove(inportVo);
        if (falg){
            resultInfo.setFlag(true);
            resultInfo.setErrorMsg("添加成功");

        }else{
            resultInfo.setFlag(false);
            resultInfo.setErrorMsg("添加失败");
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
