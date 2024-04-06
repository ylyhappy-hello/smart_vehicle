package com.ncst.vehicle.module.system.convert.notify;

import java.util.*;

import com.ncst.vehicle.framework.common.pojo.PageResult;

import com.ncst.vehicle.module.system.controller.admin.notify.vo.template.NotifyTemplateCreateReqVO;
import com.ncst.vehicle.module.system.controller.admin.notify.vo.template.NotifyTemplateRespVO;
import com.ncst.vehicle.module.system.controller.admin.notify.vo.template.NotifyTemplateUpdateReqVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.ncst.vehicle.module.system.dal.dataobject.notify.NotifyTemplateDO;

/**
 * 站内信模版 Convert
 *
 * @author xrcoder
 */
@Mapper
public interface NotifyTemplateConvert {

    NotifyTemplateConvert INSTANCE = Mappers.getMapper(NotifyTemplateConvert.class);

    NotifyTemplateDO convert(NotifyTemplateCreateReqVO bean);

    NotifyTemplateDO convert(NotifyTemplateUpdateReqVO bean);

    NotifyTemplateRespVO convert(NotifyTemplateDO bean);

    List<NotifyTemplateRespVO> convertList(List<NotifyTemplateDO> list);

    PageResult<NotifyTemplateRespVO> convertPage(PageResult<NotifyTemplateDO> page);

}
