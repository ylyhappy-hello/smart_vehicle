package com.ncst.vehicle.module.motocycleIllegal.dal.mysql.illegalaction;

import java.util.*;

import com.ncst.vehicle.framework.common.pojo.PageResult;
import com.ncst.vehicle.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.ncst.vehicle.framework.mybatis.core.mapper.BaseMapperX;
import com.ncst.vehicle.module.motocycleIllegal.dal.dataobject.illegalaction.IllegalActionDO;
import org.apache.ibatis.annotations.Mapper;
import com.ncst.vehicle.module.motocycleIllegal.controller.admin.illegalaction.vo.*;

/**
 * 违规行为 Mapper
 *
 * @author ylyhappy
 */
@Mapper
public interface IllegalActionMapper extends BaseMapperX<IllegalActionDO> {

    default PageResult<IllegalActionDO> selectPage(IllegalActionPageReqVO reqVO) {

        return selectPage(reqVO, new LambdaQueryWrapperX<IllegalActionDO>()
                .likeIfPresent(IllegalActionDO::getName, reqVO.getName())
                .likeIfPresent(IllegalActionDO::getModelUrl, reqVO.getModelUrl())
                .eqIfPresent(IllegalActionDO::getModelType, reqVO.getModelType())
                .likeIfPresent(IllegalActionDO::getDatasetUrl, reqVO.getDatasetUrl())
                .eqIfPresent(IllegalActionDO::getDatasetType, reqVO.getDatasetType())
                .likeIfPresent(IllegalActionDO::getHyprUrl, reqVO.getHyprUrl())
                .betweenIfPresent(IllegalActionDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(IllegalActionDO::getId));
    }

    default List<IllegalActionDO> selectList(IllegalActionExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<IllegalActionDO>()
                .betweenIfPresent(IllegalActionDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(IllegalActionDO::getId));
    }

}
