package com.ncst.vehicle.module.motocycleDetect.convert.detectfilesanalysis;

import java.util.*;

import com.ncst.vehicle.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.ncst.vehicle.module.motocycleDetect.controller.admin.detectfilesanalysis.vo.*;
import com.ncst.vehicle.module.motocycleDetect.dal.dataobject.detectfilesanalysis.DetectFilesAnalysisDO;

/**
 * 检测文件结果分析 Convert
 *
 * @author ylyhappy
 */
@Mapper
public interface DetectFilesAnalysisConvert {

    DetectFilesAnalysisConvert INSTANCE = Mappers.getMapper(DetectFilesAnalysisConvert.class);

    DetectFilesAnalysisDO convert(DetectFilesAnalysisCreateReqVO bean);

    DetectFilesAnalysisDO convert(DetectFilesAnalysisUpdateReqVO bean);

    DetectFilesAnalysisRespVO convert(DetectFilesAnalysisDO bean);

    List<DetectFilesAnalysisRespVO> convertList(List<DetectFilesAnalysisDO> list);

    PageResult<DetectFilesAnalysisRespVO> convertPage(PageResult<DetectFilesAnalysisDO> page);

    List<DetectFilesAnalysisExcelVO> convertList02(List<DetectFilesAnalysisDO> list);

}
