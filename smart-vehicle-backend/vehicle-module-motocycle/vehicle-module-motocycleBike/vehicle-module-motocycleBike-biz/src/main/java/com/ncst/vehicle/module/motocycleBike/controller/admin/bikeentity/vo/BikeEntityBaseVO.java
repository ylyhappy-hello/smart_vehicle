package com.ncst.vehicle.module.motocycleBike.controller.admin.bikeentity.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import javax.validation.constraints.*;

/**
 * 电车实体 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class BikeEntityBaseVO {

    @Schema(description = "违规编号", example = "18185")
    private String illeageActionId;

    @Schema(description = "设备组", example = "西北A区")
    private String deviceGroup;

    @Schema(description = "设备区域编码", example = "241023180941")
    private Long deviceRegionCode;

    @Schema(description = "设备区域", example = "北京市")
    private String deviceRegion;

    @Schema(description = "设备经纬度", example = "123,122")
    private String devicePosition;

    @Schema(description = "设备软件版本", example = "v1.0.1")
    private String deviceOs;

    @Schema(description = "设备ip地址", example = "46.156.12.8")
    private String deviceIp;

    @Schema(description = "审核状态", example = "2")
    private String status;

}
