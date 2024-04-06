package com.ncst.vehicle.module.motocycleDetect.controller.admin.detectfilesanalysis.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 检测文件结果分析更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class DetectFilesAnalysisUpdateReqVO extends DetectFilesAnalysisBaseVO {

    @Schema(description = "检测文件结果编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "13662")
    @NotNull(message = "检测文件结果编号不能为空")
    private Long id;

}
