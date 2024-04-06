package com.ncst.vehicle.module.motocycleDetect.enums;

import com.ncst.vehicle.framework.common.exception.ErrorCode;

/**
 * motocycle 错误码枚举类
 *
 * motocycle 系统，使用 1-006-000-000 段
 */
public interface ErrorCodeConstants {
	ErrorCode DETECT_FILES_NOT_EXISTS = new ErrorCode(1006004004, "检测文件不存在");
	ErrorCode DETECT_FILES_ANALYSIS_NOT_EXISTS = new ErrorCode(1006005004, "检测文件不存在");

}


