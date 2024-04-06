package com.ncst.vehicle.module.motocycleAccident.convert.accident;

import java.util.*;

import com.ncst.vehicle.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.ncst.vehicle.module.motocycleAccident.controller.admin.accident.vo.*;
import com.ncst.vehicle.module.motocycleAccident.dal.dataobject.accident.AccidentDO;

/**
 * 意外 Convert
 *
 * @author ylyhappy
 */
@Mapper
public interface AccidentConvert {

    AccidentConvert INSTANCE = Mappers.getMapper(AccidentConvert.class);

    AccidentDO convert(AccidentCreateReqVO bean);

    AccidentDO convert(AccidentUpdateReqVO bean);

    AccidentRespVO convert(AccidentDO bean);

    List<AccidentRespVO> convertList(List<AccidentDO> list);

    PageResult<AccidentRespVO> convertPage(PageResult<AccidentDO> page);

    List<AccidentExcelVO> convertList02(List<AccidentDO> list);

}
