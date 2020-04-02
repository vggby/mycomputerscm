package com.mydesign.mycomputerscm.Service;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mydesign.mycomputerscm.domain.Brand;
import com.mydesign.mycomputerscm.mapper.BrandMapper;
import org.springframework.stereotype.Service;

@Service
public class BrandServiceImpl extends ServiceImpl<BrandMapper, Brand> implements BrandService {
}
