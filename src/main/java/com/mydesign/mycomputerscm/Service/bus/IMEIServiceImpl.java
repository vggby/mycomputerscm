package com.mydesign.mycomputerscm.Service.bus;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mydesign.mycomputerscm.domain.bus.IMEI;
import com.mydesign.mycomputerscm.mapper.bus.IMEIMapper;
import org.springframework.stereotype.Service;

@Service
public class IMEIServiceImpl extends ServiceImpl<IMEIMapper, IMEI> implements  IMEIService {
}
