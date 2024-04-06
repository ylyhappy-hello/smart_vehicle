package com.ncst.vehicle.module.motocycleIllegal.convert.illegalwaringtemplate;

import java.util.*;

import com.ncst.vehicle.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.ncst.vehicle.module.motocycleIllegal.controller.admin.illegalwaringtemplate.vo.*;
import com.ncst.vehicle.module.motocycleIllegal.dal.dataobject.illegalwaringtemplate.IllegalWaringTemplateDO;

/**
 * 违规警告模板 Convert
 *
 * @author ylyhappy
 */
@Mapper
public interface IllegalWaringTemplateConvert {

    IllegalWaringTemplateConvert INSTANCE = Mappers.getMapper(IllegalWaringTemplateConvert.class);

    IllegalWaringTemplateDO convert(IllegalWaringTemplateCreateReqVO bean);

    IllegalWaringTemplateDO convert(IllegalWaringTemplateUpdateReqVO bean);

    IllegalWaringTemplateRespVO convert(IllegalWaringTemplateDO bean);

    List<IllegalWaringTemplateRespVO> convertList(List<IllegalWaringTemplateDO> list);

    PageResult<IllegalWaringTemplateRespVO> convertPage(PageResult<IllegalWaringTemplateDO> page);

    List<IllegalWaringTemplateExcelVO> convertList02(List<IllegalWaringTemplateDO> list);

}
