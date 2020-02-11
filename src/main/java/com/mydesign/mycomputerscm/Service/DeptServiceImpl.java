package com.mydesign.mycomputerscm.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mydesign.mycomputerscm.domain.Dept;
import com.mydesign.mycomputerscm.mapper.DeptMapper;
import org.springframework.stereotype.Service;

@Service
public class DeptServiceImpl extends ServiceImpl<DeptMapper, Dept> implements DeptService{

}
