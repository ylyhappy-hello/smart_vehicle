package com.ncst.vehicle.module.motocycleDetect.controller.admin.detectfilesanalysis.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 检测文件结果分析 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class DetectFilesAnalysisRespVO extends DetectFilesAnalysisBaseVO {

    @Schema(description = "检测文件结果编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "13662")
    private Long id;

    @Schema(description = "检测时间", requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDateTime createTime;

}
