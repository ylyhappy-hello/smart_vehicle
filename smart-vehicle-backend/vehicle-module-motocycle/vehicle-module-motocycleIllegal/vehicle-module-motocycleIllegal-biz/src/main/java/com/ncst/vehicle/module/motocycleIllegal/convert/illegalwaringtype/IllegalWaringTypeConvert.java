package com.ncst.vehicle.module.motocycleIllegal.convert.illegalwaringtype;

import java.util.*;

import com.ncst.vehicle.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.ncst.vehicle.module.motocycleIllegal.controller.admin.illegalwaringtype.vo.*;
import com.ncst.vehicle.module.motocycleIllegal.dal.dataobject.illegalwaringtype.IllegalWaringTypeDO;

/**
 * 违规处罚力度 Convert
 *
 * @author ylyhappy
 */
@Mapper
public interface IllegalWaringTypeConvert {

    IllegalWaringTypeConvert INSTANCE = Mappers.getMapper(IllegalWaringTypeConvert.class);

    IllegalWaringTypeDO convert(IllegalWaringTypeCreateReqVO bean);

    IllegalWaringTypeDO convert(IllegalWaringTypeUpdateReqVO bean);

    IllegalWaringTypeRespVO convert(IllegalWaringTypeDO bean);

    List<IllegalWaringTypeRespVO> convertList(List<IllegalWaringTypeDO> list);

    PageResult<IllegalWaringTypeRespVO> convertPage(PageResult<IllegalWaringTypeDO> page);

    List<IllegalWaringTypeExcelVO> convertList02(List<IllegalWaringTypeDO> list);

}
