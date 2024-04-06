package com.ncst.vehicle.module.motocycleIllegal.dal.dataobject.illegalauditlist;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import com.ncst.vehicle.framework.mybatis.core.dataobject.BaseDO;

/**
 * 违规审核 DO
 *
 * @author ylyhappy
 */
@TableName("motocycle_illegal_audit_list")
@KeySequence("motocycle_illegal_audit_list_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IllegalAuditListDO extends BaseDO {

    /**
     * 编号
     */
    @TableId
    private Long id;
    /**
     * 违规编号
     */
    private Long illeageActionId;
    /**
     * 审核状态
     */
    private String status;
    /**
     * 申请者的电话号码
     */
    private String applicantMobile;
    /**
     * 审核者的电话号码
     */
    private String inspectorMobile;

}
