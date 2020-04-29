package com.mydesign.mycomputerscm.mapper.report;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mydesign.mycomputerscm.domain.report.Report;

import java.util.ArrayList;

public interface ReportMapper extends BaseMapper<Report> {
    ArrayList<Report> loadReport();
}
