package com.mydesign.mycomputerscm.vo;

import com.mydesign.mycomputerscm.domain.SysLoginfo;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


@Data
public class LoginInfoVo extends SysLoginfo {
    private static final long serialVersionUID = 1L;


    private Integer page=1;
    private Integer limit=10;

    private Integer[] ids;//接收多个ID


    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date startTime;

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date endTime;
}
