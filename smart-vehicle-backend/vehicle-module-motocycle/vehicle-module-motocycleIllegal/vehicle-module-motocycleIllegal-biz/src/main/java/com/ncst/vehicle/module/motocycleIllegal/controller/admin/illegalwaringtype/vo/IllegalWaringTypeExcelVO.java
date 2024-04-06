package com.ncst.vehicle.module.motocycleIllegal.controller.admin.illegalwaringtype.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * 违规处罚力度 Excel VO
 *
 * @author ylyhappy
 */
@Data
public class IllegalWaringTypeExcelVO {

    @ExcelProperty("编号")
    private Long id;

    @ExcelProperty("警告编号")
    private Long waringId;

    @ExcelProperty("处罚力度分类")
    private Byte type;

    @ExcelProperty("处罚金额")
    private Integer penalty;

    @ExcelProperty("拘留天数")
    private Integer daysInDetention;

    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}
