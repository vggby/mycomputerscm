package com.mydesign.mycomputerscm.Service;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mydesign.mycomputerscm.domain.Model;
import com.mydesign.mycomputerscm.mapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class ModelServiceImpl extends ServiceImpl<ModelMapper, Model> implements ModelService {
}
