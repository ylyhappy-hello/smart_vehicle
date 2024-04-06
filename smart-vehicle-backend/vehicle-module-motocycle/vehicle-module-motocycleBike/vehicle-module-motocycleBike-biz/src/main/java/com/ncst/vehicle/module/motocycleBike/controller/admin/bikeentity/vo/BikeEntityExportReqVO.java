package com.ncst.vehicle.module.motocycleBike.controller.admin.bikeentity.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import com.ncst.vehicle.framework.common.pojo.PageParam;
import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;

import static com.ncst.vehicle.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 电车实体 Excel 导出 Request VO，参数和 BikeEntityPageReqVO 是一致的")
@Data
public class BikeEntityExportReqVO {

    @Schema(description = "违规编号", example = "18185")
    private Long illeageActionId;

    @Schema(description = "审核状态", example = "2")
    private String status;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
