package com.ncst.vehicle.module.motocycleIllegal.controller.admin.illegalauditlist.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import javax.validation.constraints.*;

/**
 * 违规审核 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class IllegalAuditListBaseVO {

    @Schema(description = "违规编号", example = "5489")
    private Long illeageActionId;

    @Schema(description = "审核状态", example = "1")
    private String status;

    @Schema(description = "申请者的电话号码")
    private String applicantMobile;

    @Schema(description = "审核者的电话号码")
    private String inspectorMobile;

}
