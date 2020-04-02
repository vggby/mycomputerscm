package com.mydesign.mycomputerscm.Service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mydesign.mycomputerscm.domain.ComputerStorage;
import com.mydesign.mycomputerscm.vo.CInportVo;

public interface ComputerstorageService extends IService<ComputerStorage> {
    boolean saveall(CInportVo inportVo);

    boolean updateAll(CInportVo inportVo);

    boolean removeAll(Integer id);
}
