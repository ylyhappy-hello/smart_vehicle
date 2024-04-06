package com.ncst.vehicle.module.motocycleDetect.controller.admin.detectfiles.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import javax.validation.constraints.*;

/**
 * 文件 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class DetectFilesBaseVO {

    @Schema(description = "配置编号", example = "28942")
    private Long configId;

    @Schema(description = "文件名", example = "芋艿")
    private String name;

    @Schema(description = "文件路径", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "文件路径不能为空")
    private String path;

    @Schema(description = "文件 URL", requiredMode = Schema.RequiredMode.REQUIRED, example = "https://www.iocoder.cn")
    @NotNull(message = "文件 URL不能为空")
    private String url;

    @Schema(description = "检测地点", requiredMode = Schema.RequiredMode.REQUIRED, example = "https://www.iocoder.cn")
    @NotNull(message = "检测地点不能为空")
    private String location;

    @Schema(description = "是否误判")
    private String status;

    @Schema(description = "文件类型", example = "2")
    private String type;

    @Schema(description = "地区编码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "地区编码不能为空")
    private Integer size;

}
