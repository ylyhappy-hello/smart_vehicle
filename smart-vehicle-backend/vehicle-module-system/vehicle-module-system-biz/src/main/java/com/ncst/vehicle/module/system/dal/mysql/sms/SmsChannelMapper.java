package com.ncst.vehicle.module.system.dal.mysql.sms;

import com.ncst.vehicle.framework.common.pojo.PageResult;
import com.ncst.vehicle.framework.mybatis.core.mapper.BaseMapperX;
import com.ncst.vehicle.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.ncst.vehicle.module.system.controller.admin.sms.vo.channel.SmsChannelPageReqVO;
import com.ncst.vehicle.module.system.dal.dataobject.sms.SmsChannelDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SmsChannelMapper extends BaseMapperX<SmsChannelDO> {

    default PageResult<SmsChannelDO> selectPage(SmsChannelPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<SmsChannelDO>()
                .likeIfPresent(SmsChannelDO::getSignature, reqVO.getSignature())
                .eqIfPresent(SmsChannelDO::getStatus, reqVO.getStatus())
                .betweenIfPresent(SmsChannelDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(SmsChannelDO::getId));
    }

}
