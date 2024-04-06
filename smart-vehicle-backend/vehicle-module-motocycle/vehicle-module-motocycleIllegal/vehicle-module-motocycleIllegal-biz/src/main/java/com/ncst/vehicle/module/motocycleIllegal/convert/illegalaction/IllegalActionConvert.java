package com.ncst.vehicle.module.motocycleIllegal.convert.illegalaction;

import java.util.*;

import com.ncst.vehicle.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.ncst.vehicle.module.motocycleIllegal.controller.admin.illegalaction.vo.*;
import com.ncst.vehicle.module.motocycleIllegal.dal.dataobject.illegalaction.IllegalActionDO;

/**
 * 违规行为 Convert
 *
 * @author ylyhappy
 */
@Mapper
public interface IllegalActionConvert {

    IllegalActionConvert INSTANCE = Mappers.getMapper(IllegalActionConvert.class);

    IllegalActionDO convert(IllegalActionCreateReqVO bean);

    IllegalActionDO convert(IllegalActionUpdateReqVO bean);

    IllegalActionRespVO convert(IllegalActionDO bean);

    List<IllegalActionRespVO> convertList(List<IllegalActionDO> list);

    PageResult<IllegalActionRespVO> convertPage(PageResult<IllegalActionDO> page);

    List<IllegalActionExcelVO> convertList02(List<IllegalActionDO> list);

}
