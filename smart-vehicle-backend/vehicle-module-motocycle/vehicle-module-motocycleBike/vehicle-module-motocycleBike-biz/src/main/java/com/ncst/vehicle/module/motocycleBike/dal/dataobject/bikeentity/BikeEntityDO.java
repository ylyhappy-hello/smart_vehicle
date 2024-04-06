package com.ncst.vehicle.module.motocycleBike.dal.dataobject.bikeentity;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import com.ncst.vehicle.framework.mybatis.core.dataobject.BaseDO;

/**
 * 电车实体 DO
 *
 * @author ylyhappy
 */
@TableName("motocycle_bike_entity")
@KeySequence("motocycle_bike_entity_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BikeEntityDO extends BaseDO {

    /**
     * 编号
     */
    @TableId
    private Long id;
    /**
     * 设备编号
     */
    private String illeageActionId;

    /**
     * 设备组
     */
    private String deviceGroup;

    /**
     * 设备区域编码
     */
    private Long deviceRegionCode;

    /**
     * 设备区域名
     */
    private String deviceRegion;

    /**
     * 设备经纬度
     */
    private String devicePosition;

    /**
     * 设备软件版本
     */
    private String deviceOs;

    /**
     * 设备ip地址
     */
    private String deviceIp;

    /**
     * 审核状态
     */
    private String status;

}
