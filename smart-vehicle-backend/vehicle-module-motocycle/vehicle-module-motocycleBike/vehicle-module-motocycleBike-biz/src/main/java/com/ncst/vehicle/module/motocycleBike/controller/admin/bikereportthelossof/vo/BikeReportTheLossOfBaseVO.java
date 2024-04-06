package com.ncst.vehicle.module.motocycleBike.controller.admin.bikereportthelossof.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import javax.validation.constraints.*;

/**
 * 电车挂失信息 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class BikeReportTheLossOfBaseVO {

    @Schema(description = "挂失人id", requiredMode = Schema.RequiredMode.REQUIRED, example = "21030")
    private Long reportTheLossOfUserid;

    @Schema(description = "挂失审核人id", requiredMode = Schema.RequiredMode.REQUIRED, example = "25328")
    private Long reportTheLossOfAuditid;

    @Schema(description = "挂失电车照片url", requiredMode = Schema.RequiredMode.REQUIRED, example = "25328")
    private String bikeUrl;

    @Schema(description = "挂失电车车牌号", requiredMode = Schema.RequiredMode.REQUIRED, example = "25328")
    private String bikeCard;

    @Schema(description = "挂失电车状态",  example = "25328")
    private String status;

}
