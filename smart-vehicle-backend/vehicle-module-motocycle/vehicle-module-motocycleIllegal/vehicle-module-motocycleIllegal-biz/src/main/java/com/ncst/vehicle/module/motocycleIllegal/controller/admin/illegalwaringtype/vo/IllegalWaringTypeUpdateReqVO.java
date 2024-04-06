package com.ncst.vehicle.module.motocycleIllegal.controller.admin.illegalwaringtype.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 违规处罚力度更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class IllegalWaringTypeUpdateReqVO extends IllegalWaringTypeBaseVO {

    @Schema(description = "编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "5169")
    @NotNull(message = "编号不能为空")
    private Long id;

}
