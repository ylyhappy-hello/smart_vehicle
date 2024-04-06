package com.ncst.vehicle.module.motocycleBike.controller.admin.bikereportthelossof.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import com.ncst.vehicle.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static com.ncst.vehicle.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 电车挂失信息分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class BikeReportTheLossOfPageReqVO extends PageParam {

    @Schema(description = "挂失人id", requiredMode = Schema.RequiredMode.REQUIRED, example = "21030")
    private Long reportTheLossOfUserid;

    @Schema(description = "挂失审核人id", requiredMode = Schema.RequiredMode.REQUIRED, example = "25328")
    private Long reportTheLossOfAuditid;

    @Schema(description = "挂失电车照片url", requiredMode = Schema.RequiredMode.REQUIRED, example = "25328")
    private String bikeUrl;

    @Schema(description = "挂失电车车牌号", requiredMode = Schema.RequiredMode.REQUIRED, example = "25328")
    private String bikeCard;

    @Schema(description = "挂失电车状态",  example = "25328")
    private String status;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
