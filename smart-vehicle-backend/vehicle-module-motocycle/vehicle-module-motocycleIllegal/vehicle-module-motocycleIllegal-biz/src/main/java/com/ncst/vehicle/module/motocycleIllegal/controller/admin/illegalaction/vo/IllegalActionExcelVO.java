package com.ncst.vehicle.module.motocycleIllegal.controller.admin.illegalaction.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * 违规行为 Excel VO
 *
 * @author ylyhappy
 */
@Data
public class IllegalActionExcelVO {

    @ExcelProperty("违规行为编号")
    private Long id;

    @ExcelProperty("违规发生地点")
    private String position;

    @ExcelProperty("违规类型")
    private String type;

    @ExcelProperty("违规原因")
    private String reason;

    @ExcelProperty("申请取消违规结果")
    private String auditResult;

    @ExcelProperty("违规处罚")
    private String punish;

    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}
