package com.ncst.vehicle.module.motocycleAccident.dal.dataobject.accident;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import com.ncst.vehicle.framework.mybatis.core.dataobject.BaseDO;

/**
 * 意外 DO
 *
 * @author ylyhappy
 */
@TableName("motocycle_accident")
@KeySequence("motocycle_accident_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccidentDO extends BaseDO {

    /**
     * 编号
     */
    @TableId
    private Long id;
    /**
     * 事故发生地点
     */
    private String position;
    /**
     * 事故发生原因
     */
    private String reason;
    /**
     * 事故情况
     */
    private String detail;
    /**
     * 事故资料
     */
    private String resourceUrls;

}
