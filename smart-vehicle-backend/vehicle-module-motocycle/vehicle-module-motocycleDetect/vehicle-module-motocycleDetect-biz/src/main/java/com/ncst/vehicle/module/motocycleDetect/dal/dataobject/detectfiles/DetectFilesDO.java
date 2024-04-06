package com.ncst.vehicle.module.motocycleDetect.dal.dataobject.detectfiles;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import com.ncst.vehicle.framework.mybatis.core.dataobject.BaseDO;

/**
 * 文件 DO
 *
 * @author ylyhappy
 */
@TableName("motocycle_detect_files")
@KeySequence("motocycle_detect_files_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DetectFilesDO extends BaseDO {

    /**
     * 文件编号
     */
    @TableId
    private Long id;
    /**
     * 文件名
     */
    private String name;
    /**
     * 文件路径
     */
    private String path;
    /**
     * 文件 URL
     */
    private String url;
    /**
     * 文件类型
     */
    private String type;
    /**
     * 检测地点
     */
    private String location;
    /**
     * 是否误判
     */
    private String status;
    /**
     * 地区编码
     */
    private Integer size;

}
