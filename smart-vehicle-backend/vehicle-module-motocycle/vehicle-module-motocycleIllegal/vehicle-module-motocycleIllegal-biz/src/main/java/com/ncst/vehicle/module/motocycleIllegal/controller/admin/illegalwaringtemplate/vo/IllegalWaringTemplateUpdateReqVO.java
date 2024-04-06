package com.ncst.vehicle.module.motocycleIllegal.controller.admin.illegalwaringtemplate.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 违规警告模板更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class IllegalWaringTemplateUpdateReqVO extends IllegalWaringTemplateBaseVO {

    @Schema(description = "编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "8691")
    @NotNull(message = "编号不能为空")
    private Long id;

}
