package com.mydesign.mycomputerscm.Service.sto;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mydesign.mycomputerscm.domain.sto.StoComstock;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jobob
 * @since 2020-03-14
 */
public interface IStoComstockService extends IService<StoComstock> {

/*
    List<StoComstock> selectstore(StoreVo toComstock);
*/
    IPage<StoComstock> selectstore(IPage<StoComstock> page, @Param(Constants.WRAPPER) Wrapper<StoComstock> queryWrapper);

    IPage<StoComstock> selectstoregroup(IPage<StoComstock> page, @Param(Constants.WRAPPER) LambdaQueryWrapper<StoComstock> queryWrapper);

}
