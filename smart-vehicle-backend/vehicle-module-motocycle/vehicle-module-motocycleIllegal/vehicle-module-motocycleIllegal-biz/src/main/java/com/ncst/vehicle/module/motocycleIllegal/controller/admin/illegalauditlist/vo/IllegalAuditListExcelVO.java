package com.ncst.vehicle.module.motocycleIllegal.controller.admin.illegalauditlist.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * 违规审核 Excel VO
 *
 * @author ylyhappy
 */
@Data
public class IllegalAuditListExcelVO {

    @ExcelProperty("编号")
    private Long id;

    @ExcelProperty("违规编号")
    private Long illeageActionId;

    @ExcelProperty("审核状态")
    private String status;

    @ExcelProperty("申请者的电话号码")
    private String applicantMobile;

    @ExcelProperty("审核者的电话号码")
    private String inspectorMobile;

    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}
