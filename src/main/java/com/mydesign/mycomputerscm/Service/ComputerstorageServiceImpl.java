package com.mydesign.mycomputerscm.Service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mydesign.mycomputerscm.domain.ComputerStorage;
import com.mydesign.mycomputerscm.domain.bus.BusSuptradedetail;
import com.mydesign.mycomputerscm.domain.bus.IMEI;
import com.mydesign.mycomputerscm.domain.sto.StoComstock;
import com.mydesign.mycomputerscm.mapper.ComputerStorageMapper;
import com.mydesign.mycomputerscm.vo.CInportVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ComputerstorageServiceImpl extends ServiceImpl<ComputerStorageMapper, ComputerStorage> implements ComputerstorageService  {

    @Autowired
    private ComputerStorageMapper computerStorageMapper;
    @Autowired
    private com.mydesign.mycomputerscm.mapper.bus.IMEIMapper iMEIMapper;
    @Autowired
    private com.mydesign.mycomputerscm.mapper.sto.StoComstockMapper stoComstockMapper;
    @Autowired
    private com.mydesign.mycomputerscm.mapper.bus.BusSuptradedetailMapper busSuptradedetailMapper;
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean saveall(CInportVo inportVo) {
        ComputerStorage computerStorage = new ComputerStorage();
        IMEI imei = new IMEI();
        //库存表
        StoComstock stoComstock = new StoComstock();
        //供应商交易明细表
        BusSuptradedetail suptradedetail = new BusSuptradedetail();



        String[] imeis = inportVo.getImeis();
        BeanUtils.copyProperties(inportVo,computerStorage);
        BeanUtils.copyProperties(inportVo,stoComstock);
        BeanUtils.copyProperties(inportVo,suptradedetail);
        suptradedetail.setTradetype("入库");
//加入明细表
        busSuptradedetailMapper.insert(suptradedetail);
        computerStorageMapper.insert(computerStorage);
        for (String i:
        imeis) {
           if (StringUtils.isNotBlank(i)){
               imei.setImei(i);
               stoComstock.setImei(i);
               imei.setOrderId(inportVo.getOrderId());
               iMEIMapper.insert(imei);
               //加入//库存表
               stoComstockMapper.insert(stoComstock);

           }
        }


        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateAll(CInportVo inportVo) {
        ComputerStorage computerStorage = new ComputerStorage();
        IMEI imei = new IMEI();
        BeanUtils.copyProperties(inportVo,imei);

        //库存表
        StoComstock stoComstock = new StoComstock();
        //供应商交易明细表
        BusSuptradedetail suptradedetail = new BusSuptradedetail();
        BeanUtils.copyProperties(inportVo,computerStorage);
        BeanUtils.copyProperties(inportVo,suptradedetail);
        BeanUtils.copyProperties(inportVo,stoComstock);
        computerStorageMapper.updateById(computerStorage);
        String[] imeis = inportVo.getImeis();
        suptradedetail.setTradetype("入库");


        LambdaQueryWrapper<IMEI> Wrapper = new LambdaQueryWrapper<>();
        LambdaQueryWrapper<BusSuptradedetail> busdWrapper = new LambdaQueryWrapper<>();
        LambdaQueryWrapper<StoComstock> stoComstockWrapper = new LambdaQueryWrapper<>();
        busdWrapper.eq(BusSuptradedetail::getOrderId,suptradedetail.getOrderId());
        Wrapper.eq(IMEI::getOrderId,imei.getOrderId());
        stoComstockWrapper.eq(StoComstock::getOrderId,stoComstock.getOrderId());
        iMEIMapper.delete(Wrapper);
        busSuptradedetailMapper.delete(busdWrapper);
        stoComstockMapper.delete(stoComstockWrapper);
        for (String i:
                imeis) {
            if (StringUtils.isNotBlank(i)){
                imei.setImei(i);
                imei.setOrderId(inportVo.getOrderId());
                iMEIMapper.insert(imei);
                busSuptradedetailMapper.insert(suptradedetail);
                stoComstockMapper.insert(stoComstock);
            }
        }


        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeAll(Integer id) {
        ComputerStorage computerStorage = computerStorageMapper.selectById(id);
        //库存表
        StoComstock stoComstock = new StoComstock();
        //供应商交易明细表
        BusSuptradedetail suptradedetail = new BusSuptradedetail();
        BeanUtils.copyProperties(computerStorage,suptradedetail);
        BeanUtils.copyProperties(computerStorage,stoComstock);

        LambdaQueryWrapper<BusSuptradedetail> busdWrapper = new LambdaQueryWrapper<>();
        LambdaQueryWrapper<StoComstock> stoComstockWrapper = new LambdaQueryWrapper<>();
        busdWrapper.eq(BusSuptradedetail::getOrderId,suptradedetail.getOrderId());
        stoComstockWrapper.eq(StoComstock::getOrderId,stoComstock.getOrderId());
        busSuptradedetailMapper.delete(busdWrapper);
        stoComstockMapper.delete(stoComstockWrapper);

        computerStorageMapper.deleteById(id);
        return true;
    }
}
