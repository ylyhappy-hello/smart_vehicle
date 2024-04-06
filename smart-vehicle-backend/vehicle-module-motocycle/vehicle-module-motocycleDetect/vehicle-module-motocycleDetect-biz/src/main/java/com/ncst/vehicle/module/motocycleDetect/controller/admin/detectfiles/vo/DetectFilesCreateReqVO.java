package com.ncst.vehicle.module.motocycleDetect.controller.admin.detectfiles.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.*;

@Schema(description = "管理后台 - 文件创建 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class DetectFilesCreateReqVO extends DetectFilesBaseVO {

}
