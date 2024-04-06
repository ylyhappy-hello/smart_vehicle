package com.ncst.vehicle.module.motocycleIllegal.dal.dataobject.illegalwaringtemplate;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import com.ncst.vehicle.framework.mybatis.core.dataobject.BaseDO;

/**
 * 违规警告模板 DO
 *
 * @author ylyhappy
 */
@TableName("motocycle_illegal_waring_template")
@KeySequence("motocycle_illegal_waring_template_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IllegalWaringTemplateDO extends BaseDO {

    /**
     * 编号
     */
    @TableId
    private Long id;
    /**
     * 短信签名
     */
    private Byte type;
    /**
     * 开启状态
     */
    private Byte status;
    /**
     * 模板编码
     */
    private String code;
    /**
     * 模板名称
     */
    private String name;
    /**
     * 模板内容
     */
    private String content;
    /**
     * 参数数组
     */
    private String params;
    /**
     * 备注
     */
    private String remark;
    /**
     * 短信 API 的模板编号
     */
    private String apiTemplateId;
    /**
     * 短信渠道编号
     */
    private Long channelId;
    /**
     * 短信渠道编码
     */
    private String channelCode;

}
