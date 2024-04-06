package com.ncst.vehicle.module.motocycleBike.enums;

import com.ncst.vehicle.framework.common.exception.ErrorCode;

/**
 * motocycle 错误码枚举类
 *
 * motocycle 系统，使用 1-006-000-000 段
 */
public interface ErrorCodeConstants {
	ErrorCode BIKE_REPORT_THE_LOSS_OF_NOT_EXISTS = new ErrorCode(1007001001, "电车挂失信息不存在");
	ErrorCode BIKE_ENTITY_NOT_EXISTS = new ErrorCode(1007001002,"电车实体不存在");
}

