package com.ncst.vehicle.module.motocycleDetect.controller.admin.detectfiles.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 文件更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class DetectFilesUpdateReqVO extends DetectFilesBaseVO {

    @Schema(description = "文件编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "30464")
    @NotNull(message = "文件编号不能为空")
    private Long id;

}
