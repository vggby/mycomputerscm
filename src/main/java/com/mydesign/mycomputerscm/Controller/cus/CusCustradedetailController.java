package com.mydesign.mycomputerscm.Controller.cus;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mydesign.mycomputerscm.Service.cus.ICusCustradedetailService;
import com.mydesign.mycomputerscm.domain.DataGridView;
import com.mydesign.mycomputerscm.domain.cus.CusCustradedetail;
import com.mydesign.mycomputerscm.vo.cus.CusCustradedetailVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("pcsaledetail")
public class CusCustradedetailController {
    @Autowired
    private ICusCustradedetailService cusCustradedetailService;
    @PostMapping("loadPcsale")
    @ResponseBody
    public DataGridView loadPcsale(@RequestBody CusCustradedetailVo cusCustradedetailVo) {
        LambdaQueryWrapper<CusCustradedetail> stoWrapper = new LambdaQueryWrapper<>();
        stoWrapper.like(StringUtils.isNotBlank(cusCustradedetailVo.getEntryOp()),CusCustradedetail::getEntryOp,cusCustradedetailVo.getEntryOp());
        stoWrapper.like(StringUtils.isNotBlank(cusCustradedetailVo.getOrderId()),CusCustradedetail::getOrderId,cusCustradedetailVo.getOrderId());

        IPage<CusCustradedetail> page=new Page<>(cusCustradedetailVo.getPage(), cusCustradedetailVo.getLimit());
        IPage<CusCustradedetail> selectstore = cusCustradedetailService.page(page,stoWrapper);
        return new DataGridView(page.getTotal(), page.getRecords());
    }
}
