package com.mydesign.mycomputerscm.Service.report;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mydesign.mycomputerscm.domain.report.Report;
import com.mydesign.mycomputerscm.vo.report.ReportVo;

import java.util.ArrayList;

public interface IReportService extends IService<Report>{
    ArrayList<Report> loadReport(ReportVo reportVo);
}
