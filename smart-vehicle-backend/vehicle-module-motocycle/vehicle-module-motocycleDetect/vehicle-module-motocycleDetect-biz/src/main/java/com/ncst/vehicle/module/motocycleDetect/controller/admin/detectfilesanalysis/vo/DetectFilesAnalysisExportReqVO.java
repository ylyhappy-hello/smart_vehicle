package com.ncst.vehicle.module.motocycleDetect.controller.admin.detectfilesanalysis.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import com.ncst.vehicle.framework.common.pojo.PageParam;
import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;

import static com.ncst.vehicle.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 检测文件结果分析 Excel 导出 Request VO，参数和 DetectFilesAnalysisPageReqVO 是一致的")
@Data
public class DetectFilesAnalysisExportReqVO {

    @Schema(description = "文件编号", example = "9765")
    private Long fileId;

    @Schema(description = "检测类型", example = "2")
    private String type;

    @Schema(description = "检测结果")
    private String result;

    @Schema(description = "是否违规")
    private String illegal;

    @Schema(description = "检测时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
