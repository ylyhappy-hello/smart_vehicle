package com.ncst.vehicle.module.motocycleAccident.controller.admin.accident;

import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;

import javax.validation.constraints.*;
import javax.validation.*;
import javax.servlet.http.*;
import java.util.*;
import java.io.IOException;

import com.ncst.vehicle.framework.common.pojo.PageResult;
import com.ncst.vehicle.framework.common.pojo.CommonResult;
import static com.ncst.vehicle.framework.common.pojo.CommonResult.success;

import com.ncst.vehicle.framework.excel.core.util.ExcelUtils;

import com.ncst.vehicle.framework.operatelog.core.annotations.OperateLog;
import static com.ncst.vehicle.framework.operatelog.core.enums.OperateTypeEnum.*;

import com.ncst.vehicle.module.motocycleAccident.controller.admin.accident.vo.*;
import com.ncst.vehicle.module.motocycleAccident.dal.dataobject.accident.AccidentDO;
import com.ncst.vehicle.module.motocycleAccident.convert.accident.AccidentConvert;
import com.ncst.vehicle.module.motocycleAccident.service.accident.AccidentService;

@Tag(name = "管理后台 - 意外")
@RestController
@RequestMapping("/motocycleAccident/accident")
@Validated
public class AccidentController {

    @Resource
    private AccidentService accidentService;

    @PostMapping("/create")
    @Operation(summary = "创建意外")
    @PreAuthorize("@ss.hasPermission('motocycleAccident:accident:create')")
    public CommonResult<Long> createAccident(@Valid @RequestBody AccidentCreateReqVO createReqVO) {
        return success(accidentService.createAccident(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新意外")
    @PreAuthorize("@ss.hasPermission('motocycleAccident:accident:update')")
    public CommonResult<Boolean> updateAccident(@Valid @RequestBody AccidentUpdateReqVO updateReqVO) {
        accidentService.updateAccident(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除意外")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('motocycleAccident:accident:delete')")
    public CommonResult<Boolean> deleteAccident(@RequestParam("id") Long id) {
        accidentService.deleteAccident(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得意外")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('motocycleAccident:accident:query')")
    public CommonResult<AccidentRespVO> getAccident(@RequestParam("id") Long id) {
        AccidentDO accident = accidentService.getAccident(id);
        return success(AccidentConvert.INSTANCE.convert(accident));
    }

    @GetMapping("/list")
    @Operation(summary = "获得意外列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('motocycleAccident:accident:query')")
    public CommonResult<List<AccidentRespVO>> getAccidentList(@RequestParam("ids") Collection<Long> ids) {
        List<AccidentDO> list = accidentService.getAccidentList(ids);
        return success(AccidentConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得意外分页")
    @PreAuthorize("@ss.hasPermission('motocycleAccident:accident:query')")
    public CommonResult<PageResult<AccidentRespVO>> getAccidentPage(@Valid AccidentPageReqVO pageVO) {
        PageResult<AccidentDO> pageResult = accidentService.getAccidentPage(pageVO);
        return success(AccidentConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出意外 Excel")
    @PreAuthorize("@ss.hasPermission('motocycleAccident:accident:export')")
    @OperateLog(type = EXPORT)
    public void exportAccidentExcel(@Valid AccidentExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<AccidentDO> list = accidentService.getAccidentList(exportReqVO);
        // 导出 Excel
        List<AccidentExcelVO> datas = AccidentConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "意外.xls", "数据", AccidentExcelVO.class, datas);
    }

}
