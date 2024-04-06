package com.ncst.vehicle.module.motocycleDetect.controller.admin.detectfilesanalysis.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * 检测文件结果分析 Excel VO
 *
 * @author ylyhappy
 */
@Data
public class DetectFilesAnalysisExcelVO {

    @ExcelProperty("检测文件结果编号")
    private Long id;

    @ExcelProperty("文件编号")
    private Long fileId;

    @ExcelProperty("检测类型")
    private String type;

    @ExcelProperty("检测结果")
    private String result;

    @ExcelProperty("是否违规")
    private String illegal;

    @ExcelProperty("检测时间")
    private LocalDateTime createTime;

}
