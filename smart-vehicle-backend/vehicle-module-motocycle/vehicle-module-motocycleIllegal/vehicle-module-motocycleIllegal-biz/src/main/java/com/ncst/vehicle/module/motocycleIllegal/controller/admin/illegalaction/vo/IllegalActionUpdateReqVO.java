package com.ncst.vehicle.module.motocycleIllegal.controller.admin.illegalaction.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 违规行为更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class IllegalActionUpdateReqVO extends IllegalActionBaseVO {

    @Schema(description = "违规行为编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "2148")
    @NotNull(message = "违规行为编号不能为空")
    private Long id;

}
