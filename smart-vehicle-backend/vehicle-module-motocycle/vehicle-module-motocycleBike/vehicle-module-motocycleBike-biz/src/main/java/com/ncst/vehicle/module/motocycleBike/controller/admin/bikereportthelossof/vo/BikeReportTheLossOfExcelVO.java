package com.ncst.vehicle.module.motocycleBike.controller.admin.bikereportthelossof.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * 电车挂失信息 Excel VO
 *
 * @author ylyhappy
 */
@Data
public class BikeReportTheLossOfExcelVO {

    @ExcelProperty("编号")
    private Long id;

    @ExcelProperty("挂失人id")
    private Long reportTheLossOfUserid;

    @ExcelProperty("挂失审核人id")
    private Long reportTheLossOfAuditid;

    @ExcelProperty("挂失电车id")
    private Long bikeId;

    @ExcelProperty("挂失状态")
    private String status;

    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}
