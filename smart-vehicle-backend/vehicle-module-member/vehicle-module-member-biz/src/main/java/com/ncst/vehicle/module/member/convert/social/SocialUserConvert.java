package com.ncst.vehicle.module.member.convert.social;

import com.ncst.vehicle.module.member.controller.app.social.vo.AppSocialUserBindReqVO;
import com.ncst.vehicle.module.member.controller.app.social.vo.AppSocialUserUnbindReqVO;
import com.ncst.vehicle.module.system.api.social.dto.SocialUserBindReqDTO;
import com.ncst.vehicle.module.system.api.social.dto.SocialUserUnbindReqDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SocialUserConvert {

    SocialUserConvert INSTANCE = Mappers.getMapper(SocialUserConvert.class);

    SocialUserBindReqDTO convert(Long userId, Integer userType, AppSocialUserBindReqVO reqVO);

    SocialUserUnbindReqDTO convert(Long userId, Integer userType, AppSocialUserUnbindReqVO reqVO);

}
