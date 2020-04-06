package com.mydesign.mycomputerscm.Service.sto;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mydesign.mycomputerscm.domain.sto.StoComstock;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jobob
 * @since 2020-03-14
 */
public interface IStoComstockService extends IService<StoComstock> {

    List<StoComstock> selectstore();
}
