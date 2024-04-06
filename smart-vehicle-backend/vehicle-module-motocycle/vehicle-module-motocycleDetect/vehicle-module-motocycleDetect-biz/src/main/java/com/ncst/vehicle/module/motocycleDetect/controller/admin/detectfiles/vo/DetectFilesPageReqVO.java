package com.ncst.vehicle.module.motocycleDetect.controller.admin.detectfiles.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import com.ncst.vehicle.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static com.ncst.vehicle.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 文件分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class DetectFilesPageReqVO extends PageParam {

    @Schema(description = "配置编号", example = "28942")
    private Long configId;

    @Schema(description = "文件名", example = "芋艿")
    private String name;

    @Schema(description = "文件路径")
    private String path;

    @Schema(description = "检测地点")
    private String location;

    @Schema(description = "文件 URL", example = "https://www.iocoder.cn")
    private String url;

    @Schema(description = "文件类型", example = "2")
    private String type;

    @Schema(description = "是否误判")
    private String status;

    @Schema(description = "地区编码")
    private Integer size;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
