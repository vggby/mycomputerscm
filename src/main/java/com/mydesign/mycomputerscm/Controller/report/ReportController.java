package com.mydesign.mycomputerscm.Controller.report;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mydesign.mycomputerscm.Service.report.IReportService;
import com.mydesign.mycomputerscm.domain.DataGridView;
import com.mydesign.mycomputerscm.domain.report.Report;
import com.mydesign.mycomputerscm.vo.report.ReportVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

@Controller
@RequestMapping("report")
public class ReportController {
    @Autowired
    private IReportService reportService;

    /**
     * 查询
     */
    @PostMapping("loadReport")
    @ResponseBody
    public DataGridView loadAllReport( ReportVo reportVo) {
        IPage<Report> page=new Page<>(reportVo.getPage(), reportVo.getLimit());
        ArrayList<Report> reports = this.reportService.loadReport(reportVo);
        Report report = new Report();
        Double talcost=0.0;
        Double amount=0.0;
        Double grossprofit=0.0;

        for (Report re:
             reports) {
            talcost = talcost+re.getTotalcost();
            amount=amount+re.getAmount();
            grossprofit=grossprofit+re.getGrossprofit();

        }
        report.setComputerType("总计");
        report.setGrossprofit(grossprofit);
        report.setAmount(amount);
        report.setTotalcost(talcost);
        reports.add(report);
        return new DataGridView(page.getTotal(), reports);
    }

    /**
     * 查询
     */
    @PostMapping("loadReporttu")
    @ResponseBody
    public ArrayList<Report>  loadReporttu( ReportVo reportVo) {
        IPage<Report> page=new Page<>(reportVo.getPage(), reportVo.getLimit());
        ArrayList<Report> reports = this.reportService.loadReport(reportVo);

        return reports;
    }


}
