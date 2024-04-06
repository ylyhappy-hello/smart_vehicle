package com.ncst.vehicle.module.infra.controller.admin.logger;

import com.ncst.vehicle.framework.common.pojo.CommonResult;
import com.ncst.vehicle.framework.common.pojo.PageResult;
import com.ncst.vehicle.framework.excel.core.util.ExcelUtils;
import com.ncst.vehicle.framework.operatelog.core.annotations.OperateLog;
import com.ncst.vehicle.module.infra.controller.admin.logger.vo.apierrorlog.ApiErrorLogExcelVO;
import com.ncst.vehicle.module.infra.controller.admin.logger.vo.apierrorlog.ApiErrorLogExportReqVO;
import com.ncst.vehicle.module.infra.controller.admin.logger.vo.apierrorlog.ApiErrorLogPageReqVO;
import com.ncst.vehicle.module.infra.controller.admin.logger.vo.apierrorlog.ApiErrorLogRespVO;
import com.ncst.vehicle.module.infra.convert.logger.ApiErrorLogConvert;
import com.ncst.vehicle.module.infra.dal.dataobject.logger.ApiErrorLogDO;
import com.ncst.vehicle.module.infra.service.logger.ApiErrorLogService;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

import static com.ncst.vehicle.framework.common.pojo.CommonResult.success;
import static com.ncst.vehicle.framework.operatelog.core.enums.OperateTypeEnum.EXPORT;
import static com.ncst.vehicle.framework.security.core.util.SecurityFrameworkUtils.getLoginUserId;

@Tag(name = "管理后台 - API 错误日志")
@RestController
@RequestMapping("/infra/api-error-log")
@Validated
public class ApiErrorLogController {

    @Resource
    private ApiErrorLogService apiErrorLogService;

    @PutMapping("/update-status")
    @Operation(summary = "更新 API 错误日志的状态")
    @Parameters({
            @Parameter(name = "id", description = "编号", required = true, example = "1024"),
            @Parameter(name = "processStatus", description = "处理状态", required = true, example = "1")
    })
    @PreAuthorize("@ss.hasPermission('infra:api-error-log:update-status')")
    public CommonResult<Boolean> updateApiErrorLogProcess(@RequestParam("id") Long id,
                                                          @RequestParam("processStatus") Integer processStatus) {
        apiErrorLogService.updateApiErrorLogProcess(id, processStatus, getLoginUserId());
        return success(true);
    }

    @GetMapping("/page")
    @Operation(summary = "获得 API 错误日志分页")
    @PreAuthorize("@ss.hasPermission('infra:api-error-log:query')")
    public CommonResult<PageResult<ApiErrorLogRespVO>> getApiErrorLogPage(@Valid ApiErrorLogPageReqVO pageVO) {
        PageResult<ApiErrorLogDO> pageResult = apiErrorLogService.getApiErrorLogPage(pageVO);
        return success(ApiErrorLogConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出 API 错误日志 Excel")
    @PreAuthorize("@ss.hasPermission('infra:api-error-log:export')")
    @OperateLog(type = EXPORT)
    public void exportApiErrorLogExcel(@Valid ApiErrorLogExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<ApiErrorLogDO> list = apiErrorLogService.getApiErrorLogList(exportReqVO);
        // 导出 Excel
        List<ApiErrorLogExcelVO> datas = ApiErrorLogConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "API 错误日志.xls", "数据", ApiErrorLogExcelVO.class, datas);
    }

}
