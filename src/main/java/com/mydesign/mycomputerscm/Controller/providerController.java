package com.mydesign.mycomputerscm.Controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mydesign.mycomputerscm.Service.ProviderService;
import com.mydesign.mycomputerscm.domain.DataGridView;
import com.mydesign.mycomputerscm.domain.Provider;
import com.mydesign.mycomputerscm.domain.ResultInfo;
import com.mydesign.mycomputerscm.vo.ProviderVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
@Controller
@RequestMapping("provider")
public class providerController {
    @Autowired
    private ProviderService providerService;
    /**
     * 查询
     */
    @PostMapping("loadAllProvider")
    @ResponseBody
    public DataGridView loadAllProvider( ProviderVo providerVo) {
        IPage<Provider> page = new Page<>(providerVo.getPage(), providerVo.getLimit());
        QueryWrapper<Provider> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(providerVo.getProvidername()), "providername",
                providerVo.getProvidername());
        queryWrapper.like(StringUtils.isNotBlank(providerVo.getTelephone()), "telephone", providerVo.getTelephone());
        queryWrapper.like(StringUtils.isNotBlank(providerVo.getConnectionperson()), "connectionperson",
                providerVo.getConnectionperson());
        this.providerService.page(page, queryWrapper);
        return new DataGridView(page.getTotal(), page.getRecords());
    }

    /**
     * 添加
     */
    @PostMapping("addProvider")
    @ResponseBody
    public ResultInfo addProvider(ProviderVo providerVo) {
        ResultInfo resultInfo = new ResultInfo();
        try {
            this.providerService.save(providerVo);
            resultInfo.setErrorMsg("添加成功");
            resultInfo.setFlag(true);
            return resultInfo;
        } catch (Exception e) {
            resultInfo.setErrorMsg("添加失败");
            resultInfo.setFlag(false);
            return resultInfo;
        }
    }

    /**
     * 修改
     */
    @PostMapping("updateProvider")
    @ResponseBody
    public ResultInfo updateProvider(ProviderVo providerVo) {
        ResultInfo resultInfo = new ResultInfo();
        try {
            this.providerService.updateById(providerVo);
            resultInfo.setErrorMsg("修改成功");
            resultInfo.setFlag(true);
            return resultInfo;
        } catch (Exception e) {
            resultInfo.setErrorMsg("添加失败");
            resultInfo.setFlag(false);
            return resultInfo;
        }
    }

    /**
     * 删除
     */
    @PostMapping("deleteProvider")
    @ResponseBody
    public ResultInfo deleteProvider(Integer id) {
        ResultInfo resultInfo = new ResultInfo();

        try {
            this.providerService.removeById(id);
            resultInfo.setErrorMsg("删除成功");
            resultInfo.setFlag(true);
            return resultInfo;
        } catch (Exception e) {
            resultInfo.setErrorMsg("添加失败");
            resultInfo.setFlag(false);
            return resultInfo;
        }
    }



    /**
     * 加载所有可用的供应商
     */
    @PostMapping("loadAllProviderForSelect")
    @ResponseBody
    public DataGridView loadAllProviderForSelect() {
        QueryWrapper<Provider> queryWrapper=new QueryWrapper<>();
        List<Provider> list = this.providerService.list(queryWrapper);
        return new DataGridView(list);
    }
}
