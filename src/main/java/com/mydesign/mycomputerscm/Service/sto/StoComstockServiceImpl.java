package com.mydesign.mycomputerscm.Service.sto;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mydesign.mycomputerscm.domain.sto.StoComstock;
import com.mydesign.mycomputerscm.mapper.sto.StoComstockMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    @Autowired
    private  StoComstockMapper stoComstockMapper;


    @Override
    public IPage<StoComstock> selectstore(IPage<StoComstock> page, Wrapper<StoComstock> queryWrapper) {

        Page<StoComstock> selectstore = stoComstockMapper.selectstore(page,queryWrapper);
        return selectstore;
    }

    @Override
    public IPage<StoComstock> selectstoregroup(IPage<StoComstock> page, LambdaQueryWrapper<StoComstock> queryWrapper) {
        queryWrapper.notIn(StoComstock::getRemark,"报废","已售");
        Page<StoComstock> selectstore = stoComstockMapper.selectAllstoreGroup(page,queryWrapper);
        return selectstore;
    }
}
