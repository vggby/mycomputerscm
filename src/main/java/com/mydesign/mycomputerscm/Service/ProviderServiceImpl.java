package com.mydesign.mycomputerscm.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mydesign.mycomputerscm.domain.Provider;
import com.mydesign.mycomputerscm.mapper.ProviderMapper;
import org.springframework.stereotype.Service;

@Service
public class ProviderServiceImpl extends ServiceImpl<ProviderMapper, Provider> implements ProviderService{
}
