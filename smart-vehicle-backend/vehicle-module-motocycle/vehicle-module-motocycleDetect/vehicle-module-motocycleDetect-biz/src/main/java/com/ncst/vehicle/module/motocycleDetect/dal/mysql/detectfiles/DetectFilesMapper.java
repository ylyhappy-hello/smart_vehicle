package com.ncst.vehicle.module.motocycleDetect.dal.mysql.detectfiles;

import java.util.*;

import com.ncst.vehicle.framework.common.pojo.PageResult;
import com.ncst.vehicle.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.ncst.vehicle.framework.mybatis.core.mapper.BaseMapperX;
import com.ncst.vehicle.module.motocycleDetect.dal.dataobject.detectfiles.DetectFilesDO;
import org.apache.ibatis.annotations.Mapper;
import com.ncst.vehicle.module.motocycleDetect.controller.admin.detectfiles.vo.*;

/**
 * 文件 Mapper
 *
 * @author ylyhappy
 */
@Mapper
public interface DetectFilesMapper extends BaseMapperX<DetectFilesDO> {

    default PageResult<DetectFilesDO> selectPage(DetectFilesPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<DetectFilesDO>()
                .likeIfPresent(DetectFilesDO::getName, reqVO.getName())
                .eqIfPresent(DetectFilesDO::getPath, reqVO.getPath())
                .eqIfPresent(DetectFilesDO::getUrl, reqVO.getUrl())
                .likeIfPresent(DetectFilesDO::getLocation, reqVO.getLocation())
                .eqIfPresent(DetectFilesDO::getType, reqVO.getType())
                .eqIfPresent(DetectFilesDO::getStatus, reqVO.getStatus())
                .eqIfPresent(DetectFilesDO::getSize, reqVO.getSize())
                .betweenIfPresent(DetectFilesDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(DetectFilesDO::getId));
    }

    default List<DetectFilesDO> selectAll() {
        return selectList(new LambdaQueryWrapperX<DetectFilesDO>()
                .orderByDesc(DetectFilesDO::getId));
    }

    default List<DetectFilesDO> selectList(DetectFilesExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<DetectFilesDO>()
                .likeIfPresent(DetectFilesDO::getName, reqVO.getName())
                .eqIfPresent(DetectFilesDO::getPath, reqVO.getPath())
                .eqIfPresent(DetectFilesDO::getUrl, reqVO.getUrl())
                .likeIfPresent(DetectFilesDO::getLocation, reqVO.getLocation())
                .eqIfPresent(DetectFilesDO::getType, reqVO.getType())
                .eqIfPresent(DetectFilesDO::getSize, reqVO.getSize())
                .betweenIfPresent(DetectFilesDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(DetectFilesDO::getId));
    }
    List<DetectFilesDO> analysisList();

}
