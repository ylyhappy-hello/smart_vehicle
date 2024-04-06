package com.ncst.vehicle.module.motocycleDetect.controller.admin.detectfilesanalysis.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import javax.validation.constraints.*;

/**
 * 检测文件结果分析 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class DetectFilesAnalysisBaseVO {

    @Schema(description = "文件编号", example = "9765")
    private Long fileId;

    @Schema(description = "检测类型", example = "2")
    private String type;

    @Schema(description = "检测结果")
    private String result;

    @Schema(description = "是否违规")
    private String illegal;

}
