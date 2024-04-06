package com.ncst.vehicle.module.motocycleIllegal.dal.mysql.illegalwaringtemplate;

import java.util.*;

import com.ncst.vehicle.framework.common.pojo.PageResult;
import com.ncst.vehicle.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.ncst.vehicle.framework.mybatis.core.mapper.BaseMapperX;
import com.ncst.vehicle.module.motocycleIllegal.dal.dataobject.illegalwaringtemplate.IllegalWaringTemplateDO;
import org.apache.ibatis.annotations.Mapper;
import com.ncst.vehicle.module.motocycleIllegal.controller.admin.illegalwaringtemplate.vo.*;

/**
 * 违规警告模板 Mapper
 *
 * @author ylyhappy
 */
@Mapper
public interface IllegalWaringTemplateMapper extends BaseMapperX<IllegalWaringTemplateDO> {

    default PageResult<IllegalWaringTemplateDO> selectPage(IllegalWaringTemplatePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<IllegalWaringTemplateDO>()
                .eqIfPresent(IllegalWaringTemplateDO::getType, reqVO.getType())
                .eqIfPresent(IllegalWaringTemplateDO::getStatus, reqVO.getStatus())
                .eqIfPresent(IllegalWaringTemplateDO::getCode, reqVO.getCode())
                .likeIfPresent(IllegalWaringTemplateDO::getName, reqVO.getName())
                .eqIfPresent(IllegalWaringTemplateDO::getContent, reqVO.getContent())
                .eqIfPresent(IllegalWaringTemplateDO::getParams, reqVO.getParams())
                .eqIfPresent(IllegalWaringTemplateDO::getRemark, reqVO.getRemark())
                .eqIfPresent(IllegalWaringTemplateDO::getApiTemplateId, reqVO.getApiTemplateId())
                .eqIfPresent(IllegalWaringTemplateDO::getChannelId, reqVO.getChannelId())
                .eqIfPresent(IllegalWaringTemplateDO::getChannelCode, reqVO.getChannelCode())
                .betweenIfPresent(IllegalWaringTemplateDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(IllegalWaringTemplateDO::getId));
    }

    default List<IllegalWaringTemplateDO> selectList(IllegalWaringTemplateExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<IllegalWaringTemplateDO>()
                .eqIfPresent(IllegalWaringTemplateDO::getType, reqVO.getType())
                .eqIfPresent(IllegalWaringTemplateDO::getStatus, reqVO.getStatus())
                .eqIfPresent(IllegalWaringTemplateDO::getCode, reqVO.getCode())
                .likeIfPresent(IllegalWaringTemplateDO::getName, reqVO.getName())
                .eqIfPresent(IllegalWaringTemplateDO::getContent, reqVO.getContent())
                .eqIfPresent(IllegalWaringTemplateDO::getParams, reqVO.getParams())
                .eqIfPresent(IllegalWaringTemplateDO::getRemark, reqVO.getRemark())
                .eqIfPresent(IllegalWaringTemplateDO::getApiTemplateId, reqVO.getApiTemplateId())
                .eqIfPresent(IllegalWaringTemplateDO::getChannelId, reqVO.getChannelId())
                .eqIfPresent(IllegalWaringTemplateDO::getChannelCode, reqVO.getChannelCode())
                .betweenIfPresent(IllegalWaringTemplateDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(IllegalWaringTemplateDO::getId));
    }

}
