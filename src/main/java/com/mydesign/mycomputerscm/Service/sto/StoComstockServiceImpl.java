package com.mydesign.mycomputerscm.Service.sto;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mydesign.mycomputerscm.domain.sto.StoComstock;
import com.mydesign.mycomputerscm.mapper.sto.StoComstockMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jobob
 * @since 2020-03-14
 */
@Service
public class StoComstockServiceImpl extends ServiceImpl<StoComstockMapper, StoComstock> implements IStoComstockService {

    @Override
    public List<StoComstock> selectstore() {
        return null;
    }
}
