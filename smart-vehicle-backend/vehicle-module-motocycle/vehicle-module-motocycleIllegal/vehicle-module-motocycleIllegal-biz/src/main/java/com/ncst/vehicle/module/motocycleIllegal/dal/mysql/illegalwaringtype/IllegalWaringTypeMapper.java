package com.ncst.vehicle.module.motocycleIllegal.dal.mysql.illegalwaringtype;

import java.util.*;

import com.ncst.vehicle.framework.common.pojo.PageResult;
import com.ncst.vehicle.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.ncst.vehicle.framework.mybatis.core.mapper.BaseMapperX;
import com.ncst.vehicle.module.motocycleIllegal.dal.dataobject.illegalwaringtype.IllegalWaringTypeDO;
import org.apache.ibatis.annotations.Mapper;
import com.ncst.vehicle.module.motocycleIllegal.controller.admin.illegalwaringtype.vo.*;

/**
 * 违规处罚力度 Mapper
 *
 * @author ylyhappy
 */
@Mapper
public interface IllegalWaringTypeMapper extends BaseMapperX<IllegalWaringTypeDO> {

    default PageResult<IllegalWaringTypeDO> selectPage(IllegalWaringTypePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<IllegalWaringTypeDO>()
                .eqIfPresent(IllegalWaringTypeDO::getWaringId, reqVO.getWaringId())
                .eqIfPresent(IllegalWaringTypeDO::getType, reqVO.getType())
                .eqIfPresent(IllegalWaringTypeDO::getPenalty, reqVO.getPenalty())
                .eqIfPresent(IllegalWaringTypeDO::getDaysInDetention, reqVO.getDaysInDetention())
                .betweenIfPresent(IllegalWaringTypeDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(IllegalWaringTypeDO::getId));
    }

    default List<IllegalWaringTypeDO> selectList(IllegalWaringTypeExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<IllegalWaringTypeDO>()
                .eqIfPresent(IllegalWaringTypeDO::getWaringId, reqVO.getWaringId())
                .eqIfPresent(IllegalWaringTypeDO::getType, reqVO.getType())
                .eqIfPresent(IllegalWaringTypeDO::getPenalty, reqVO.getPenalty())
                .eqIfPresent(IllegalWaringTypeDO::getDaysInDetention, reqVO.getDaysInDetention())
                .betweenIfPresent(IllegalWaringTypeDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(IllegalWaringTypeDO::getId));
    }

}
