package com.ncst.vehicle.module.motocycleIllegal.controller.admin.illegalwaringtype.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 违规处罚力度创建 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class IllegalWaringTypeCreateReqVO extends IllegalWaringTypeBaseVO {

}
