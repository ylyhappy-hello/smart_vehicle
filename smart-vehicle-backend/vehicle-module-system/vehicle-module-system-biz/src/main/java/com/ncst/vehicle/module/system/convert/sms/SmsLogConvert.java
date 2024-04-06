package com.ncst.vehicle.module.system.convert.sms;

import com.ncst.vehicle.module.system.controller.admin.sms.vo.log.SmsLogExcelVO;
import com.ncst.vehicle.module.system.controller.admin.sms.vo.log.SmsLogRespVO;
import com.ncst.vehicle.module.system.dal.dataobject.sms.SmsLogDO;
import com.ncst.vehicle.framework.common.pojo.PageResult;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 短信日志 Convert
 *
 * @author 芋道源码
 */
@Mapper
public interface SmsLogConvert {

    SmsLogConvert INSTANCE = Mappers.getMapper(SmsLogConvert.class);

    SmsLogRespVO convert(SmsLogDO bean);

    List<SmsLogRespVO> convertList(List<SmsLogDO> list);

    PageResult<SmsLogRespVO> convertPage(PageResult<SmsLogDO> page);

    List<SmsLogExcelVO> convertList02(List<SmsLogDO> list);

}