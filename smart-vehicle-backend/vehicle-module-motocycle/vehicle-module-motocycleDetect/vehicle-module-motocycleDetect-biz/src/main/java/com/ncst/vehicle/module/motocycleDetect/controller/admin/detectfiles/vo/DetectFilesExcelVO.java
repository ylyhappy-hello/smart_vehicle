package com.ncst.vehicle.module.motocycleDetect.controller.admin.detectfiles.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * 文件 Excel VO
 *
 * @author ylyhappy
 */
@Data
public class DetectFilesExcelVO {

    @ExcelProperty("文件编号")
    private Long id;

    @ExcelProperty("配置编号")
    private Long configId;

    @ExcelProperty("文件名")
    private String name;

    @ExcelProperty("文件路径")
    private String path;

    @ExcelProperty("文件 URL")
    private String url;

    @Schema(description = "检测地点")
    private String location;

    @ExcelProperty("文件类型")
    private String type;

    @ExcelProperty("地区编码")
    private Integer size;

    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}
