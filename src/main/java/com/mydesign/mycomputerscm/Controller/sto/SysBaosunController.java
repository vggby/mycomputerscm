package com.mydesign.mycomputerscm.Controller.sto;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mydesign.mycomputerscm.Service.sto.ISysBaosunService;
import com.mydesign.mycomputerscm.domain.DataGridView;
import com.mydesign.mycomputerscm.domain.sto.SysBaosun;
import com.mydesign.mycomputerscm.vo.st.BaosunVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("bfs")
public class SysBaosunController {
    @Autowired
    private ISysBaosunService baosunService;
    /**
     * 查询
     */
    @PostMapping("Loadbfs")
    @ResponseBody
    public DataGridView loadStore(@RequestBody BaosunVo toComstock) {
        LambdaQueryWrapper<SysBaosun> stoWrapper = new LambdaQueryWrapper<>();
        stoWrapper.like(StringUtils.isNotBlank(toComstock.getImei()),SysBaosun::getImei,toComstock.getImei());
        IPage<SysBaosun> page=new Page<>(toComstock.getPage(), toComstock.getLimit());
         baosunService.page(page, stoWrapper);

        return new DataGridView(page.getTotal(), page.getRecords());
    }

}
