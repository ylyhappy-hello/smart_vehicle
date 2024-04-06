package com.ncst.vehicle.module.motocycleIllegal.controller.admin.illegalaction.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import com.ncst.vehicle.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static com.ncst.vehicle.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 违规行为分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class IllegalActionPageReqVO extends PageParam {

    @Schema(description = "模型文件名")
    private String name;
    @Schema(description = "模型类型")
    private String modelType;
    @Schema(description = "模型文件地址")
    private String modelUrl;
    @Schema(description = "模型训练参数地址")
    private String hyprUrl;
    @Schema(description = "数据集地址")
    private String datasetUrl;
    @Schema(description = "数据集类型")
    private String datasetType;
    @Schema(description = "mAP@0.5")
    private String point5Map;
    @Schema(description = "训练轮次")
    private String epoch;
    @Schema(description = "违规类型", example = "1")
    private String type;
    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
