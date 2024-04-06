package com.ncst.vehicle.module.motocycleAccident.controller.admin.accident.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 意外更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class AccidentUpdateReqVO extends AccidentBaseVO {

    @Schema(description = "编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "11212")
    @NotNull(message = "编号不能为空")
    private Long id;

    @Schema(description = "事故资料")
    private String resourceUrls;

}
