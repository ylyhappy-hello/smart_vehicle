package com.ncst.vehicle.module.motocycleIllegal.convert.illegalauditlist;

import java.util.*;

import com.ncst.vehicle.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.ncst.vehicle.module.motocycleIllegal.controller.admin.illegalauditlist.vo.*;
import com.ncst.vehicle.module.motocycleIllegal.dal.dataobject.illegalauditlist.IllegalAuditListDO;

/**
 * 违规审核 Convert
 *
 * @author ylyhappy
 */
@Mapper
public interface IllegalAuditListConvert {

    IllegalAuditListConvert INSTANCE = Mappers.getMapper(IllegalAuditListConvert.class);

    IllegalAuditListDO convert(IllegalAuditListCreateReqVO bean);

    IllegalAuditListDO convert(IllegalAuditListUpdateReqVO bean);

    IllegalAuditListRespVO convert(IllegalAuditListDO bean);

    List<IllegalAuditListRespVO> convertList(List<IllegalAuditListDO> list);

    PageResult<IllegalAuditListRespVO> convertPage(PageResult<IllegalAuditListDO> page);

    List<IllegalAuditListExcelVO> convertList02(List<IllegalAuditListDO> list);

}
