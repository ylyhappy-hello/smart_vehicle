package com.ncst.vehicle.module.motocycleBike.controller.admin.bikereportthelossof.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import com.ncst.vehicle.framework.common.pojo.PageParam;
import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;

import static com.ncst.vehicle.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 电车挂失信息 Excel 导出 Request VO，参数和 BikeReportTheLossOfPageReqVO 是一致的")
@Data
public class BikeReportTheLossOfExportReqVO {

    @Schema(description = "挂失人id", example = "21030")
    private Long reportTheLossOfUserid;

    @Schema(description = "挂失审核人id", example = "25328")
    private Long reportTheLossOfAuditid;

    @Schema(description = "挂失电车id", example = "976")
    private Long bikeId;

    @Schema(description = "挂失状态", example = "1")
    private String status;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
