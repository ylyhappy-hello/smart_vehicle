package com.ncst.vehicle.module.motocycleBike.controller.admin.bikeentity.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * 电车实体 Excel VO
 *
 * @author ylyhappy
 */
@Data
public class BikeEntityExcelVO {

    @ExcelProperty("编号")
    private Long id;

    @ExcelProperty("违规编号")
    private Long illeageActionId;

    @ExcelProperty("审核状态")
    private String status;

    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}
