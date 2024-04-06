package com.ncst.vehicle.module.motocycleIllegal.controller.admin.illegalauditlist.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import com.ncst.vehicle.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static com.ncst.vehicle.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 违规审核分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class IllegalAuditListPageReqVO extends PageParam {

    @Schema(description = "违规编号", example = "5489")
    private Long illeageActionId;

    @Schema(description = "审核状态", example = "1")
    private String status;

    @Schema(description = "申请者的电话号码")
    private String applicantMobile;

    @Schema(description = "审核者的电话号码")
    private String inspectorMobile;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
