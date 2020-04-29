package com.mydesign.mycomputerscm.Service.report;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mydesign.mycomputerscm.domain.report.Report;
import com.mydesign.mycomputerscm.mapper.report.ReportMapper;
import com.mydesign.mycomputerscm.vo.report.ReportVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jobob
 * @since 2020-04-27
 */
@Service
public class ReportServiceImpl extends ServiceImpl<ReportMapper, Report> implements IReportService {
    @Autowired
    private ReportMapper reportMapper;
    @Override
    public ArrayList<Report> loadReport(ReportVo reportVo) {
        ArrayList<Report> reports = reportMapper.loadReport();
        System.out.println(reports);
        return reports;
    }
}
