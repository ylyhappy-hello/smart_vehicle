package com.ncst.vehicle.module.motocycleAccident.enums;

import com.ncst.vehicle.framework.common.exception.ErrorCode;

/**
 * motocycle 错误码枚举类
 *
 * motocycle 系统，使用 1-006-000-000 段
 */
public interface ErrorCodeConstants {
	ErrorCode ACCIDENT_NOT_EXISTS = new ErrorCode(1006001001,"事故不存在");
}


