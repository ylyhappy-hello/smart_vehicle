package com.ncst.vehicle.module.motocycleAccident.controller.admin.accident.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * 意外 Excel VO
 *
 * @author ylyhappy
 */
@Data
public class AccidentExcelVO {

    @ExcelProperty("编号")
    private Long id;

    @ExcelProperty("事故发生地点")
    private String position;

    @ExcelProperty("事故发生原因")
    private String reason;

    @ExcelProperty("事故情况")
    private String detail;

    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}
