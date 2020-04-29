package com.mydesign.mycomputerscm.mapper.sto;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mydesign.mycomputerscm.domain.sto.StoComstock;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author jobob
 * @since 2020-03-14
 */
public interface StoComstockMapper extends BaseMapper<StoComstock> {
    @Select("select computer_type,count(imei) as count,price,avg(lossamount) AS lossamount from sto_comstock  ${ew.customSqlSegment} GROUP BY computer_type")
    Page<StoComstock> selectstore(IPage<StoComstock> page, @Param(Constants.WRAPPER) Wrapper<StoComstock> stoComstock);


    @Select("select  *,COUNT(*) as count from sto_comstock  ${ew.customSqlSegment} GROUP BY computer_type")
    Page<StoComstock> selectAllstoreGroup(IPage<StoComstock> page, @Param(Constants.WRAPPER) LambdaQueryWrapper<StoComstock> stoComstock);

}
