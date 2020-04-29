package com.mydesign.mycomputerscm.vo.report;

import com.mydesign.mycomputerscm.domain.report.Report;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


@Data
public class ReportVo extends Report {
    private Integer page=1;
    private Integer limit=10;
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date startTime;

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date endTime;
}
