package com.ncst.vehicle.module.motocycleAccident.controller.admin.accident.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import com.ncst.vehicle.framework.common.pojo.PageParam;
import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;

import static com.ncst.vehicle.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 意外 Excel 导出 Request VO，参数和 AccidentPageReqVO 是一致的")
@Data
public class AccidentExportReqVO {

    @Schema(description = "事故发生地点")
    private String position;

    @Schema(description = "事故发生原因", example = "酒驾")
    private String reason;

    @Schema(description = "事故情况")
    private String detail;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
