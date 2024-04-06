package com.ncst.vehicle.module.motocycleIllegal.controller.admin.illegalwaringtemplate.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import com.ncst.vehicle.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static com.ncst.vehicle.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 违规警告模板分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class IllegalWaringTemplatePageReqVO extends PageParam {

    @Schema(description = "短信签名", example = "2")
    private Byte type;

    @Schema(description = "开启状态", example = "2")
    private Byte status;

    @Schema(description = "模板编码")
    private String code;

    @Schema(description = "模板名称", example = "李四")
    private String name;

    @Schema(description = "模板内容")
    private String content;

    @Schema(description = "参数数组")
    private String params;

    @Schema(description = "备注", example = "你说的对")
    private String remark;

    @Schema(description = "短信 API 的模板编号", example = "5886")
    private String apiTemplateId;

    @Schema(description = "短信渠道编号", example = "30479")
    private Long channelId;

    @Schema(description = "短信渠道编码")
    private String channelCode;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
