package com.ncst.vehicle.module.motocycleBike.convert.bikereportthelossof;

import java.util.*;

import com.ncst.vehicle.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.ncst.vehicle.module.motocycleBike.controller.admin.bikereportthelossof.vo.*;
import com.ncst.vehicle.module.motocycleBike.dal.dataobject.bikereportthelossof.BikeReportTheLossOfDO;

/**
 * 电车挂失信息 Convert
 *
 * @author ylyhappy
 */
@Mapper
public interface BikeReportTheLossOfConvert {

    BikeReportTheLossOfConvert INSTANCE = Mappers.getMapper(BikeReportTheLossOfConvert.class);

    BikeReportTheLossOfDO convert(BikeReportTheLossOfCreateReqVO bean);

    BikeReportTheLossOfDO convert(BikeReportTheLossOfUpdateReqVO bean);

    BikeReportTheLossOfRespVO convert(BikeReportTheLossOfDO bean);

    List<BikeReportTheLossOfRespVO> convertList(List<BikeReportTheLossOfDO> list);

    PageResult<BikeReportTheLossOfRespVO> convertPage(PageResult<BikeReportTheLossOfDO> page);

    List<BikeReportTheLossOfExcelVO> convertList02(List<BikeReportTheLossOfDO> list);

}
