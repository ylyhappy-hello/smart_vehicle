package com.ncst.vehicle.module.infra.convert.job;

import com.ncst.vehicle.framework.common.pojo.PageResult;
import com.ncst.vehicle.module.infra.controller.admin.job.vo.job.JobCreateReqVO;
import com.ncst.vehicle.module.infra.controller.admin.job.vo.job.JobExcelVO;
import com.ncst.vehicle.module.infra.controller.admin.job.vo.job.JobRespVO;
import com.ncst.vehicle.module.infra.controller.admin.job.vo.job.JobUpdateReqVO;
import com.ncst.vehicle.module.infra.dal.dataobject.job.JobDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 定时任务 Convert
 *
 * @author 芋道源码
 */
@Mapper
public interface JobConvert {

    JobConvert INSTANCE = Mappers.getMapper(JobConvert.class);

    JobDO convert(JobCreateReqVO bean);

    JobDO convert(JobUpdateReqVO bean);

    JobRespVO convert(JobDO bean);

    List<JobRespVO> convertList(List<JobDO> list);

    PageResult<JobRespVO> convertPage(PageResult<JobDO> page);

    List<JobExcelVO> convertList02(List<JobDO> list);

}
