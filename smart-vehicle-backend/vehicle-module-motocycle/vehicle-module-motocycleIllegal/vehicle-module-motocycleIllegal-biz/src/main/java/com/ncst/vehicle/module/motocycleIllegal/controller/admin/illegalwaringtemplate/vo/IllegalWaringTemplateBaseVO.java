package com.ncst.vehicle.module.motocycleIllegal.controller.admin.illegalwaringtemplate.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import javax.validation.constraints.*;

/**
 * 违规警告模板 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class IllegalWaringTemplateBaseVO {

    @Schema(description = "短信签名", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @NotNull(message = "短信签名不能为空")
    private Byte type;

    @Schema(description = "开启状态", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @NotNull(message = "开启状态不能为空")
    private Byte status;

    @Schema(description = "模板编码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "模板编码不能为空")
    private String code;

    @Schema(description = "模板名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "李四")
    @NotNull(message = "模板名称不能为空")
    private String name;

    @Schema(description = "模板内容", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "模板内容不能为空")
    private String content;

    @Schema(description = "参数数组", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "参数数组不能为空")
    private String params;

    @Schema(description = "备注", example = "你说的对")
    private String remark;

    @Schema(description = "短信 API 的模板编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "5886")
    @NotNull(message = "短信 API 的模板编号不能为空")
    private String apiTemplateId;

    @Schema(description = "短信渠道编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "30479")
    @NotNull(message = "短信渠道编号不能为空")
    private Long channelId;

    @Schema(description = "短信渠道编码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "短信渠道编码不能为空")
    private String channelCode;

}
