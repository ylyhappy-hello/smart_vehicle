package com.ncst.vehicle.module.motocycleBike.dal.mysql.bikereportthelossof;

import java.util.*;

import com.ncst.vehicle.framework.common.pojo.PageResult;
import com.ncst.vehicle.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.ncst.vehicle.framework.mybatis.core.mapper.BaseMapperX;
import com.ncst.vehicle.module.motocycleBike.dal.dataobject.bikereportthelossof.BikeReportTheLossOfDO;
import org.apache.ibatis.annotations.Mapper;
import com.ncst.vehicle.module.motocycleBike.controller.admin.bikereportthelossof.vo.*;

/**
 * 电车挂失信息 Mapper
 *
 * @author ylyhappy
 */
@Mapper
public interface BikeReportTheLossOfMapper extends BaseMapperX<BikeReportTheLossOfDO> {

    default PageResult<BikeReportTheLossOfDO> selectPage(BikeReportTheLossOfPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<BikeReportTheLossOfDO>()
                .eqIfPresent(BikeReportTheLossOfDO::getReportTheLossOfUserid, reqVO.getReportTheLossOfUserid())
                .eqIfPresent(BikeReportTheLossOfDO::getBikeCard, reqVO.getBikeCard())
                .eqIfPresent(BikeReportTheLossOfDO::getStatus, reqVO.getStatus())
                .betweenIfPresent(BikeReportTheLossOfDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(BikeReportTheLossOfDO::getId));
    }

    default List<BikeReportTheLossOfDO> selectList(BikeReportTheLossOfExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<BikeReportTheLossOfDO>()
                .eqIfPresent(BikeReportTheLossOfDO::getReportTheLossOfUserid, reqVO.getReportTheLossOfUserid())
                .eqIfPresent(BikeReportTheLossOfDO::getReportTheLossOfAuditid, reqVO.getReportTheLossOfAuditid())
                .eqIfPresent(BikeReportTheLossOfDO::getStatus, reqVO.getStatus())
                .betweenIfPresent(BikeReportTheLossOfDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(BikeReportTheLossOfDO::getId));
    }

}
