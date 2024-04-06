package com.ncst.vehicle.module.motocycleDetect.controller.admin.detectfilesanalysis.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 检测文件结果分析创建 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class DetectFilesAnalysisCreateReqVO extends DetectFilesAnalysisBaseVO {

}
