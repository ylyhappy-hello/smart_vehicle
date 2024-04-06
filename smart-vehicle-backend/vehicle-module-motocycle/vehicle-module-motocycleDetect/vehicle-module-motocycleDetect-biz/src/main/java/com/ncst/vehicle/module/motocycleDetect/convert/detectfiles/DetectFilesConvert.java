package com.ncst.vehicle.module.motocycleDetect.convert.detectfiles;

import java.util.*;

import com.ncst.vehicle.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.ncst.vehicle.module.motocycleDetect.controller.admin.detectfiles.vo.*;
import com.ncst.vehicle.module.motocycleDetect.dal.dataobject.detectfiles.DetectFilesDO;

/**
 * 文件 Convert
 *
 * @author ylyhappy
 */
@Mapper
public interface DetectFilesConvert {

    DetectFilesConvert INSTANCE = Mappers.getMapper(DetectFilesConvert.class);

    DetectFilesDO convert(DetectFilesCreateReqVO bean);

    DetectFilesDO convert(DetectFilesUpdateReqVO bean);

    DetectFilesRespVO convert(DetectFilesDO bean);

    List<DetectFilesRespVO> convertList(List<DetectFilesDO> list);

    PageResult<DetectFilesRespVO> convertPage(PageResult<DetectFilesDO> page);

    List<DetectFilesExcelVO> convertList02(List<DetectFilesDO> list);

}
