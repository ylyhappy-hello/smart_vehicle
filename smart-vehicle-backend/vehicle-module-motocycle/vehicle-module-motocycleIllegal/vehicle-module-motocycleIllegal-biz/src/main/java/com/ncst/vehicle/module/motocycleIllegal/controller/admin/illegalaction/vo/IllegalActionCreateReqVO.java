package com.ncst.vehicle.module.motocycleIllegal.controller.admin.illegalaction.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 违规行为创建 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class IllegalActionCreateReqVO extends IllegalActionBaseVO {

}
