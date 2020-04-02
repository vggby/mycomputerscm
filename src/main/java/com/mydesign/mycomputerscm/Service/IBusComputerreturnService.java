package com.mydesign.mycomputerscm.Service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mydesign.mycomputerscm.domain.BusComputerreturn;
import com.mydesign.mycomputerscm.vo.COutportVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jobob
 * @since 2020-03-29
 */
public interface IBusComputerreturnService extends IService<BusComputerreturn> {

    boolean thremove(COutportVo inportVo);
}
