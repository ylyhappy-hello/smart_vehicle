package com.ncst.vehicle.module.motocycleBike.controller.admin.bikeentity.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import com.ncst.vehicle.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static com.ncst.vehicle.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 电车实体分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class BikeEntityPageReqVO extends PageParam {

    @Schema(description = "违规编号", example = "18185")
    private Long illeageActionId;

    @Schema(description = "审核状态", example = "2")
    private String status;

    @Schema(description = "设备组", example = "西北A区")
    private String deviceGroup;

    @Schema(description = "设备区域编码", example = "241023180941")
    private Long devieRegionCode;

    @Schema(description = "设备区域", example = "北京市")
    private String devieRegion;

    @Schema(description = "设备经纬度", example = "123,122")
    private String devicePosition;

    @Schema(description = "设备软件版本", example = "v1.0.1")
    private String deviceOs;

    @Schema(description = "设备ip地址", example = "46.156.12.8")
    private String deviceIp;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
