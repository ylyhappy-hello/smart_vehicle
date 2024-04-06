package com.ncst.vehicle.module.motocycleIllegal.controller.admin.illegalwaringtype.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import com.ncst.vehicle.framework.common.pojo.PageParam;
import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;

import static com.ncst.vehicle.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 违规处罚力度 Excel 导出 Request VO，参数和 IllegalWaringTypePageReqVO 是一致的")
@Data
public class IllegalWaringTypeExportReqVO {

    @Schema(description = "警告编号", example = "2881")
    private Long waringId;

    @Schema(description = "处罚力度分类", example = "1")
    private Byte type;

    @Schema(description = "处罚金额")
    private Integer penalty;

    @Schema(description = "拘留天数")
    private Integer daysInDetention;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
