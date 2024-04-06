package com.ncst.vehicle.module.motocycleBike.controller.admin.bikeentity.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 电车实体更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class BikeEntityUpdateReqVO extends BikeEntityBaseVO {

    @Schema(description = "编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "13735")
    @NotNull(message = "编号不能为空")
    private Long id;

}
