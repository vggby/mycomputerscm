package com.mydesign.mycomputerscm.Controller.sto;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mydesign.mycomputerscm.Service.sto.IStoComstockService;
import com.mydesign.mycomputerscm.domain.Brand;
import com.mydesign.mycomputerscm.domain.DataGridView;
import com.mydesign.mycomputerscm.domain.sto.StoComstock;
import com.mydesign.mycomputerscm.vo.st.StoreVo;
import org.apache.commons.lang3.StringUtils;
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
    /**
     * 查询
     */
    @PostMapping("loadStore")
    @ResponseBody
    public DataGridView loadStore(@RequestBody StoreVo toComstock) {
        IPage<StoComstock> page=new Page<>(toComstock.getPage(), toComstock.getLimit());
        stoComstockService.selectstore();
        List<Brand> list = page.getRecords();
        return new DataGridView(page.getTotal(), page.getRecords());
    }
}
