package com.ncst.vehicle.module.motocycleIllegal.controller.admin.illegalaction.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import com.ncst.vehicle.framework.common.pojo.PageParam;
import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;

import static com.ncst.vehicle.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 违规行为 Excel 导出 Request VO，参数和 IllegalActionPageReqVO 是一致的")
@Data
public class IllegalActionExportReqVO {

    @Schema(description = "违规发生地点")
    private String position;

    @Schema(description = "违规类型", example = "1")
    private String type;

    @Schema(description = "违规原因", example = "不喜欢")
    private String reason;

    @Schema(description = "申请取消违规结果")
    private String auditResult;

    @Schema(description = "违规处罚")
    private String punish;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
