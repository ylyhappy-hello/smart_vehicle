package com.ncst.vehicle.module.motocycleAccident.controller.admin.accident.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 意外创建 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class AccidentCreateReqVO extends AccidentBaseVO {

    @Schema(description = "事故资料")
    private String resourceUrls;

}
