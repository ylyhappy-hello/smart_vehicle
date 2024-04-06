package com.ncst.vehicle.module.motocycleDetect.dal.mysql.detectfilesanalysis;

import java.util.*;

import com.ncst.vehicle.framework.common.pojo.PageResult;
import com.ncst.vehicle.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.ncst.vehicle.framework.mybatis.core.mapper.BaseMapperX;
import com.ncst.vehicle.module.motocycleDetect.dal.dataobject.detectfilesanalysis.DetectFilesAnalysisDO;
import org.apache.ibatis.annotations.Mapper;
import com.ncst.vehicle.module.motocycleDetect.controller.admin.detectfilesanalysis.vo.*;

/**
 * 检测文件结果分析 Mapper
 *
 * @author ylyhappy
 */
@Mapper
public interface DetectFilesAnalysisMapper extends BaseMapperX<DetectFilesAnalysisDO> {

    default PageResult<DetectFilesAnalysisDO> selectPage(DetectFilesAnalysisPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<DetectFilesAnalysisDO>()
                .eqIfPresent(DetectFilesAnalysisDO::getFileId, reqVO.getFileId())
                .eqIfPresent(DetectFilesAnalysisDO::getType, reqVO.getType())
                .eqIfPresent(DetectFilesAnalysisDO::getResult, reqVO.getResult())
                .eqIfPresent(DetectFilesAnalysisDO::getIllegal, reqVO.getIllegal())
                .betweenIfPresent(DetectFilesAnalysisDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(DetectFilesAnalysisDO::getId));
    }

    default List<DetectFilesAnalysisDO> selectList(DetectFilesAnalysisExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<DetectFilesAnalysisDO>()
                .eqIfPresent(DetectFilesAnalysisDO::getFileId, reqVO.getFileId())
                .eqIfPresent(DetectFilesAnalysisDO::getType, reqVO.getType())
                .eqIfPresent(DetectFilesAnalysisDO::getResult, reqVO.getResult())
                .eqIfPresent(DetectFilesAnalysisDO::getIllegal, reqVO.getIllegal())
                .betweenIfPresent(DetectFilesAnalysisDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(DetectFilesAnalysisDO::getId));
    }

}
