package com.ncst.vehicle.module.motocycleIllegal.enums;

import com.ncst.vehicle.framework.common.exception.ErrorCode;

/**
 * motocycle 错误码枚举类
 *
 * motocycle 系统，使用 1-006-000-000 段
 */
public interface ErrorCodeConstants {
	ErrorCode ILLEGAL_ACTION_NOT_EXISTS = new ErrorCode(1007001001, "违规行为不存在");
	ErrorCode ILLEGAL_AUDIT_LIST_NOT_EXISTS = new ErrorCode(1007002001, "违规审核不存在");
	ErrorCode ILLEGAL_WARING_TEMPLATE_NOT_EXISTS = new ErrorCode(1007003001, "违规警告模板不存在");
	ErrorCode ILLEGAL_WARING_TYPE_NOT_EXISTS = new ErrorCode(1007004001, "违规处罚力度不存在");
}


