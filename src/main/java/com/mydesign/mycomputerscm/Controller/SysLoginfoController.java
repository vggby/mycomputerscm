package com.mydesign.mycomputerscm.Controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mydesign.mycomputerscm.Service.ISysLoginfoService;
import com.mydesign.mycomputerscm.domain.DataGridView;
import com.mydesign.mycomputerscm.domain.ResultInfo;
import com.mydesign.mycomputerscm.domain.SysLoginfo;
import com.mydesign.mycomputerscm.vo.LoginInfoVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jobob
 * @since 2020-04-28
 */

@Controller
@RequestMapping("/loginfo")
public class SysLoginfoController {
    @Autowired
    private ISysLoginfoService sysLoginfoService;
    /**
     * 查询
     */
    @PostMapping("loadAllloginfo")
    @ResponseBody
    public DataGridView loadAllLoginfo(LoginInfoVo loginfoVo) {
        IPage<SysLoginfo> page=new Page<>(loginfoVo.getPage(), loginfoVo.getLimit());
        QueryWrapper<SysLoginfo> queryWrapper=new QueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(loginfoVo.getLoginname()),"loginname", loginfoVo.getLoginname());
        queryWrapper.like(StringUtils.isNotBlank(loginfoVo.getLoginip()), "loginip",loginfoVo.getLoginip());
        queryWrapper.ge(loginfoVo.getStartTime()!=null, "logintime", loginfoVo.getStartTime());
        queryWrapper.le(loginfoVo.getEndTime()!=null, "logintime", loginfoVo.getEndTime());
        queryWrapper.orderByDesc("logintime");
        this.sysLoginfoService.page(page, queryWrapper);
        return new DataGridView(page.getTotal(), page.getRecords());
    }

    /**
     * 删除
     */
    @PostMapping("deleteLoginfo")
    @ResponseBody
    public ResultInfo deleteLoginfo(Integer id) {
        ResultInfo resultInfo = new ResultInfo();
        try {
            sysLoginfoService.removeById(id);
            resultInfo.setFlag(true);
            resultInfo.setErrorMsg("删除成功");
            return resultInfo;
        }catch (Exception e){
            e.printStackTrace();
            resultInfo.setFlag(false);
             resultInfo.setErrorMsg("删除失败");
            return resultInfo;
        }


    }

}
