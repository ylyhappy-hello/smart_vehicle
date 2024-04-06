package com.ncst.vehicle.module.motocycleBike.dal.mysql.bikeentity;

import java.util.*;

import com.ncst.vehicle.framework.common.pojo.PageResult;
import com.ncst.vehicle.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.ncst.vehicle.framework.mybatis.core.mapper.BaseMapperX;
import com.ncst.vehicle.module.motocycleBike.dal.dataobject.bikeentity.BikeEntityDO;
import org.apache.ibatis.annotations.Mapper;
import com.ncst.vehicle.module.motocycleBike.controller.admin.bikeentity.vo.*;

/**
 * 电车实体 Mapper
 *
 * @author ylyhappy
 */
@Mapper
public interface BikeEntityMapper extends BaseMapperX<BikeEntityDO> {

    default PageResult<BikeEntityDO> selectPage(BikeEntityPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<BikeEntityDO>()
                .eqIfPresent(BikeEntityDO::getIlleageActionId, reqVO.getIlleageActionId())
                .eqIfPresent(BikeEntityDO::getDeviceRegionCode, reqVO.getDevieRegionCode())
                .eqIfPresent(BikeEntityDO::getDeviceRegion, reqVO.getDevieRegion())
                .eqIfPresent(BikeEntityDO::getDevicePosition, reqVO.getDevicePosition())
                .eqIfPresent(BikeEntityDO::getDeviceIp, reqVO.getDeviceIp())
                .eqIfPresent(BikeEntityDO::getDeviceGroup, reqVO.getDeviceGroup())
                .eqIfPresent(BikeEntityDO::getStatus, reqVO.getStatus())
                .betweenIfPresent(BikeEntityDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(BikeEntityDO::getId));
    }

    default List<BikeEntityDO> selectList(BikeEntityExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<BikeEntityDO>()
                .eqIfPresent(BikeEntityDO::getIlleageActionId, reqVO.getIlleageActionId())
                .eqIfPresent(BikeEntityDO::getStatus, reqVO.getStatus())
                .betweenIfPresent(BikeEntityDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(BikeEntityDO::getId));
    }

}
