package com.ncst.vehicle.module.motocycleIllegal.controller.admin.illegalaction.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import javax.validation.constraints.*;

/**
 * 违规行为 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class IllegalActionBaseVO {


    @Schema(description = "模型文件名")
    private String name;
    @Schema(description = "模型类型")
    private String modelType;
    @Schema(description = "模型文件地址")
    private String modelUrl;
    @Schema(description = "模型训练参数地址")
    private String hyprUrl;
    @Schema(description = "数据集地址")
    private String datasetUrl;
    @Schema(description = "数据集类型")
    private String datasetType;
    @Schema(description = "mAP@0.5")
    private String point5Map;
    @Schema(description = "训练轮次")
    private String epoch;

}
