package com.mydesign.mycomputerscm.Service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mydesign.mycomputerscm.domain.BusComputerreturn;
import com.mydesign.mycomputerscm.domain.bus.BusSuptradedetail;
import com.mydesign.mycomputerscm.domain.bus.IMEI;
import com.mydesign.mycomputerscm.domain.sto.StoComstock;
import com.mydesign.mycomputerscm.mapper.BusComputerreturnMapper;
import com.mydesign.mycomputerscm.vo.COutportVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jobob
 * @since 2020-03-29
 */
@Service
public class BusComputerreturnServiceImpl extends ServiceImpl<BusComputerreturnMapper, BusComputerreturn> implements IBusComputerreturnService {
    @Autowired
    private com.mydesign.mycomputerscm.mapper.bus.IMEIMapper iMEIMapper;
    @Autowired
    private BusComputerreturnMapper computerreturnMapper;
    @Autowired
    private com.mydesign.mycomputerscm.mapper.sto.StoComstockMapper stoComstockMapper;
    @Autowired
    private com.mydesign.mycomputerscm.mapper.bus.BusSuptradedetailMapper busSuptradedetailMapper;
    @Override
    public boolean thremove(COutportVo inportVo) {
        BusComputerreturn computerreturn = new BusComputerreturn();
        IMEI imei = new IMEI();
        //库存表
        StoComstock stoComstock = new StoComstock();
        //供应商交易明细表
        BusSuptradedetail suptradedetail = new BusSuptradedetail();



        String[] imeis = inportVo.getImeis();
        BeanUtils.copyProperties(inportVo,computerreturn);
        BeanUtils.copyProperties(inportVo,stoComstock);
        BeanUtils.copyProperties(inportVo,suptradedetail);
        suptradedetail.setTradetype("退货");

        computerreturnMapper.insert(computerreturn);
        for (String i:
                imeis) {
            if (StringUtils.isNotBlank(i)){
                imei.setImei(i);
                stoComstock.setImei(i);
                imei.setOrderId(inportVo.getOrderId());
                LambdaQueryWrapper<IMEI> iMEIWrapper = new LambdaQueryWrapper<>();
                iMEIWrapper.eq(IMEI::getImei,i);
                LambdaQueryWrapper<StoComstock> Wrapper = new LambdaQueryWrapper<>();
                Wrapper.eq(StoComstock::getImei,i);
                stoComstockMapper.delete(Wrapper);
                iMEIMapper.delete(iMEIWrapper);
                //加入明细表
                busSuptradedetailMapper.insert(suptradedetail);
            }
        }


        return true;
    }
}
