package com.ncst.vehicle.module.motocycleBike.controller.admin.bikeentity.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 电车实体创建 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class BikeEntityCreateReqVO extends BikeEntityBaseVO {

}
