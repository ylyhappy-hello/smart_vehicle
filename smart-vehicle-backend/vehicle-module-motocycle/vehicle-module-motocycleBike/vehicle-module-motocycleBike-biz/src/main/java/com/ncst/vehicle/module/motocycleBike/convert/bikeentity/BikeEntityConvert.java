package com.ncst.vehicle.module.motocycleBike.convert.bikeentity;

import java.util.*;

import com.ncst.vehicle.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.ncst.vehicle.module.motocycleBike.controller.admin.bikeentity.vo.*;
import com.ncst.vehicle.module.motocycleBike.dal.dataobject.bikeentity.BikeEntityDO;

/**
 * 电车实体 Convert
 *
 * @author ylyhappy
 */
@Mapper
public interface BikeEntityConvert {

    BikeEntityConvert INSTANCE = Mappers.getMapper(BikeEntityConvert.class);

    BikeEntityDO convert(BikeEntityCreateReqVO bean);

    BikeEntityDO convert(BikeEntityUpdateReqVO bean);

    BikeEntityRespVO convert(BikeEntityDO bean);

    List<BikeEntityRespVO> convertList(List<BikeEntityDO> list);

    PageResult<BikeEntityRespVO> convertPage(PageResult<BikeEntityDO> page);

    List<BikeEntityExcelVO> convertList02(List<BikeEntityDO> list);

}
