package com.ncst.vehicle.module.motocycleIllegal.dal.dataobject.illegalwaringtype;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import com.ncst.vehicle.framework.mybatis.core.dataobject.BaseDO;

/**
 * 违规处罚力度 DO
 *
 * @author ylyhappy
 */
@TableName("motocycle_illegal_waring_type")
@KeySequence("motocycle_illegal_waring_type_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IllegalWaringTypeDO extends BaseDO {

    /**
     * 编号
     */
    @TableId
    private Long id;
    /**
     * 警告编号
     */
    private Long waringId;
    /**
     * 处罚力度分类
     */
    private Byte type;
    /**
     * 处罚金额
     */
    private Integer penalty;
    /**
     * 拘留天数
     */
    private Integer daysInDetention;

}
