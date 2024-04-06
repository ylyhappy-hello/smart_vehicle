package com.ncst.vehicle.module.motocycleAccident.dal.mysql.accident;

import java.util.*;

import com.ncst.vehicle.framework.common.pojo.PageResult;
import com.ncst.vehicle.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.ncst.vehicle.framework.mybatis.core.mapper.BaseMapperX;
import com.ncst.vehicle.module.motocycleAccident.dal.dataobject.accident.AccidentDO;
import org.apache.ibatis.annotations.Mapper;
import com.ncst.vehicle.module.motocycleAccident.controller.admin.accident.vo.*;

/**
 * 意外 Mapper
 *
 * @author ylyhappy
 */
@Mapper
public interface AccidentMapper extends BaseMapperX<AccidentDO> {

    default PageResult<AccidentDO> selectPage(AccidentPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<AccidentDO>()
                .eqIfPresent(AccidentDO::getPosition, reqVO.getPosition())
                .eqIfPresent(AccidentDO::getReason, reqVO.getReason())
                .eqIfPresent(AccidentDO::getDetail, reqVO.getDetail())
                .betweenIfPresent(AccidentDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(AccidentDO::getId));
    }

    default List<AccidentDO> selectList(AccidentExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<AccidentDO>()
                .eqIfPresent(AccidentDO::getPosition, reqVO.getPosition())
                .eqIfPresent(AccidentDO::getReason, reqVO.getReason())
                .eqIfPresent(AccidentDO::getDetail, reqVO.getDetail())
                .betweenIfPresent(AccidentDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(AccidentDO::getId));
    }

}
