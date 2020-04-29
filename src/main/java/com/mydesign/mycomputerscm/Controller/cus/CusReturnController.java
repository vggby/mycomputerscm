package com.mydesign.mycomputerscm.Controller.cus;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mydesign.mycomputerscm.Service.cus.ICusReturnService;
import com.mydesign.mycomputerscm.domain.DataGridView;
import com.mydesign.mycomputerscm.domain.cus.CusReturn;
import com.mydesign.mycomputerscm.vo.cus.CusSaleReVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("cusReturn")
public class CusReturnController  {
    @Autowired
    private ICusReturnService cusReturnService;
    @PostMapping("loadSaleRe")
    @ResponseBody
    public DataGridView loadSale(CusSaleReVo cusSaleVo) {
        LambdaQueryWrapper<CusReturn> stoWrapper = new LambdaQueryWrapper<>();
        stoWrapper.like(cusSaleVo.getCustomerId()!=null,CusReturn::getCustomerId,cusSaleVo.getCustomerId());
        stoWrapper.like(cusSaleVo.getOrderId()!=null,CusReturn::getOrderId,cusSaleVo.getOrderId());

        IPage<CusReturn> page=new Page<>(cusSaleVo.getPage(), cusSaleVo.getLimit());
        IPage<CusReturn> selectstore = cusReturnService.page(page,stoWrapper);
        return new DataGridView(page.getTotal(), page.getRecords());
    }
}
