package com.ncst.vehicle.module.motocycleAccident.controller.admin.accident.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import javax.validation.constraints.*;

/**
 * 意外 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class AccidentBaseVO {

    @Schema(description = "事故发生地点")
    private String position;

    @Schema(description = "事故发生原因", example = "酒驾")
    private String reason;

    @Schema(description = "事故情况")
    private String detail;

}
