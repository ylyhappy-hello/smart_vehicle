package com.ncst.vehicle.module.system.convert.tenant;

import com.ncst.vehicle.framework.common.pojo.PageResult;
import com.ncst.vehicle.module.system.controller.admin.tenant.vo.tenant.TenantCreateReqVO;
import com.ncst.vehicle.module.system.controller.admin.tenant.vo.tenant.TenantExcelVO;
import com.ncst.vehicle.module.system.controller.admin.tenant.vo.tenant.TenantRespVO;
import com.ncst.vehicle.module.system.controller.admin.tenant.vo.tenant.TenantUpdateReqVO;
import com.ncst.vehicle.module.system.controller.admin.user.vo.user.UserCreateReqVO;
import com.ncst.vehicle.module.system.dal.dataobject.tenant.TenantDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 租户 Convert
 *
 * @author 芋道源码
 */
@Mapper
public interface TenantConvert {

    TenantConvert INSTANCE = Mappers.getMapper(TenantConvert.class);

    TenantDO convert(TenantCreateReqVO bean);

    TenantDO convert(TenantUpdateReqVO bean);

    TenantRespVO convert(TenantDO bean);

    List<TenantRespVO> convertList(List<TenantDO> list);

    PageResult<TenantRespVO> convertPage(PageResult<TenantDO> page);

    List<TenantExcelVO> convertList02(List<TenantDO> list);

    default UserCreateReqVO convert02(TenantCreateReqVO bean) {
        UserCreateReqVO reqVO = new UserCreateReqVO();
        reqVO.setUsername(bean.getUsername());
        reqVO.setPassword(bean.getPassword());
        reqVO.setNickname(bean.getContactName()).setMobile(bean.getContactMobile());
        return reqVO;
    }

}
