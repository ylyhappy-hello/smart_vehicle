package com.ncst.vehicle.module.motocycleIllegal.dal.dataobject.illegalaction;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import com.ncst.vehicle.framework.mybatis.core.dataobject.BaseDO;

/**
 * 违规行为 DO
 *
 * @author ylyhappy
 */
@TableName("motocycle_illegal_action")
@KeySequence("motocycle_illegal_action_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IllegalActionDO extends BaseDO {

    /**
     * 模型使用统计
     */
    @TableId
    private Long id;
    /**
     * 模型文件名
     */
    private String name;
    /**
     * 模型类型
     */
    private String modelType;
    /**
     * 模型文件地址
     */
    private String modelUrl;
    /**
     * 模型训练参数地址
     */
    private String hyprUrl;
    /**
     * 数据集地址
     */
    private String datasetUrl;
    /**
     * 数据集类型
     */
    private String datasetType;
    /**
     * mAP@0.5
     */
    private String point5Map;
    /**
     * 训练论次
     */
    private String epoch;
}
