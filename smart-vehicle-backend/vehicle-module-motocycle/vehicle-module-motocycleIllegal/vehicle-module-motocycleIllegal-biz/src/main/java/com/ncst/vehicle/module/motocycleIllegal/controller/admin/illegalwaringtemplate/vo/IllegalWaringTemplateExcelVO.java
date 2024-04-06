package com.ncst.vehicle.module.motocycleIllegal.controller.admin.illegalwaringtemplate.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * 违规警告模板 Excel VO
 *
 * @author ylyhappy
 */
@Data
public class IllegalWaringTemplateExcelVO {

    @ExcelProperty("编号")
    private Long id;

    @ExcelProperty("短信签名")
    private Byte type;

    @ExcelProperty("开启状态")
    private Byte status;

    @ExcelProperty("模板编码")
    private String code;

    @ExcelProperty("模板名称")
    private String name;

    @ExcelProperty("模板内容")
    private String content;

    @ExcelProperty("参数数组")
    private String params;

    @ExcelProperty("备注")
    private String remark;

    @ExcelProperty("短信 API 的模板编号")
    private String apiTemplateId;

    @ExcelProperty("短信渠道编号")
    private Long channelId;

    @ExcelProperty("短信渠道编码")
    private String channelCode;

    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}
