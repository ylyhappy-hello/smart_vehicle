package com.ncst.vehicle.module.motocycleIllegal.controller.admin.illegalaction.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 违规行为 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class IllegalActionRespVO extends IllegalActionBaseVO {

    @Schema(description = "违规行为编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "2148")
    private Long id;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDateTime createTime;

}
