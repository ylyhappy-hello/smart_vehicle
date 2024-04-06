package com.ncst.vehicle.module.motocycleBike.dal.dataobject.bikereportthelossof;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import com.ncst.vehicle.framework.mybatis.core.dataobject.BaseDO;

/**
 * 电车挂失信息 DO
 *
 * @author ylyhappy
 */
@TableName("motocycle_bike_report_the_loss_of")
@KeySequence("motocycle_bike_report_the_loss_of_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BikeReportTheLossOfDO extends BaseDO {

    /**
     * 编号
     */
    @TableId
    private Long id;
    /**
     * 挂失人id
     */
    private Long reportTheLossOfUserid;
    /**
     * 挂失审核人id
     */
    private Long reportTheLossOfAuditid;

    /**
     * 挂失电车照片url
     */
    private String bikeUrl;

    /**
     * 挂失电车车牌号
     */
    private String bikeCard;

    /**
     * 挂失电车状态
     */
    private String status;

}
