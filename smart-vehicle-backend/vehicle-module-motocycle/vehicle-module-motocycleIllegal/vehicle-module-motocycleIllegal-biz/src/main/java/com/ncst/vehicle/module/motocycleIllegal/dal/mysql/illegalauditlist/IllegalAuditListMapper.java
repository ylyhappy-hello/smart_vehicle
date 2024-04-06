package com.ncst.vehicle.module.motocycleIllegal.dal.mysql.illegalauditlist;

import java.util.*;

import com.ncst.vehicle.framework.common.pojo.PageResult;
import com.ncst.vehicle.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.ncst.vehicle.framework.mybatis.core.mapper.BaseMapperX;
import com.ncst.vehicle.module.motocycleIllegal.dal.dataobject.illegalauditlist.IllegalAuditListDO;
import org.apache.ibatis.annotations.Mapper;
import com.ncst.vehicle.module.motocycleIllegal.controller.admin.illegalauditlist.vo.*;

/**
 * 违规审核 Mapper
 *
 * @author ylyhappy
 */
@Mapper
public interface IllegalAuditListMapper extends BaseMapperX<IllegalAuditListDO> {

    default PageResult<IllegalAuditListDO> selectPage(IllegalAuditListPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<IllegalAuditListDO>()
                .eqIfPresent(IllegalAuditListDO::getIlleageActionId, reqVO.getIlleageActionId())
                .eqIfPresent(IllegalAuditListDO::getStatus, reqVO.getStatus())
                .eqIfPresent(IllegalAuditListDO::getApplicantMobile, reqVO.getApplicantMobile())
                .eqIfPresent(IllegalAuditListDO::getInspectorMobile, reqVO.getInspectorMobile())
                .betweenIfPresent(IllegalAuditListDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(IllegalAuditListDO::getId));
    }

    default List<IllegalAuditListDO> selectList(IllegalAuditListExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<IllegalAuditListDO>()
                .eqIfPresent(IllegalAuditListDO::getIlleageActionId, reqVO.getIlleageActionId())
                .eqIfPresent(IllegalAuditListDO::getStatus, reqVO.getStatus())
                .eqIfPresent(IllegalAuditListDO::getApplicantMobile, reqVO.getApplicantMobile())
                .eqIfPresent(IllegalAuditListDO::getInspectorMobile, reqVO.getInspectorMobile())
                .betweenIfPresent(IllegalAuditListDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(IllegalAuditListDO::getId));
    }

}
