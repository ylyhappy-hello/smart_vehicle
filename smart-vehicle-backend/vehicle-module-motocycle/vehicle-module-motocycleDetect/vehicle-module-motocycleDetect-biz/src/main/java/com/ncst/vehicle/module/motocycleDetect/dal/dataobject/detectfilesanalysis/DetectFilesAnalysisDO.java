package com.ncst.vehicle.module.motocycleDetect.dal.dataobject.detectfilesanalysis;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import com.ncst.vehicle.framework.mybatis.core.dataobject.BaseDO;

/**
 * 检测文件结果分析 DO
 *
 * @author ylyhappy
 */
@TableName("motocycle_detect_files_analysis")
@KeySequence("motocycle_detect_files_analysis_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DetectFilesAnalysisDO extends BaseDO {

    /**
     * 检测文件结果编号
     */
    @TableId
    private Long id;
    /**
     * 文件编号
     */
    private Long fileId;
    /**
     * 检测类型
     */
    private String type;
    /**
     * 检测结果
     */
    private String result;
    /**
     * 是否违规
     */
    private String illegal;

}
