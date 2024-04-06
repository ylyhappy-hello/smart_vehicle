package com.ncst.vehicle.module.motocycleIllegal.controller.admin.illegalwaringtype.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import javax.validation.constraints.*;

/**
 * 违规处罚力度 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class IllegalWaringTypeBaseVO {

    @Schema(description = "警告编号", example = "2881")
    private Long waringId;

    @Schema(description = "处罚力度分类", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "处罚力度分类不能为空")
    private Byte type;

    @Schema(description = "处罚金额")
    private Integer penalty;

    @Schema(description = "拘留天数")
    private Integer daysInDetention;

}
