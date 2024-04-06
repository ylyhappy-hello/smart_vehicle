package com.ncst.vehicle.module.motocycleDetect.controller.admin.detectfiles;

import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.annotation.security.PermitAll;

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

import com.ncst.vehicle.module.motocycleDetect.controller.admin.detectfiles.vo.*;
import com.ncst.vehicle.module.motocycleDetect.dal.dataobject.detectfiles.DetectFilesDO;
import com.ncst.vehicle.module.motocycleDetect.convert.detectfiles.DetectFilesConvert;
import com.ncst.vehicle.module.motocycleDetect.service.detectfiles.DetectFilesService;

@Tag(name = "管理后台 - 文件")
@RestController
@RequestMapping("/motocycleDetect/detect-files")
@Validated
public class DetectFilesController {

    @Resource
    private DetectFilesService detectFilesService;

    @PostMapping("/create")
    @Operation(summary = "创建文件")
    @PermitAll
    public CommonResult<Long> createDetectFiles(@Valid @RequestBody DetectFilesCreateReqVO createReqVO) {
        return success(detectFilesService.createDetectFiles(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新文件")
    @PreAuthorize("@ss.hasPermission('motocycleDetect:detect-files:update')")
    public CommonResult<Boolean> updateDetectFiles(@Valid @RequestBody DetectFilesUpdateReqVO updateReqVO) {
        detectFilesService.updateDetectFiles(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除文件")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('motocycleDetect:detect-files:delete')")
    public CommonResult<Boolean> deleteDetectFiles(@RequestParam("id") Long id) {
        detectFilesService.deleteDetectFiles(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得文件")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('motocycleDetect:detect-files:query')")
    public CommonResult<DetectFilesRespVO> getDetectFiles(@RequestParam("id") Long id) {
        DetectFilesDO detectFiles = detectFilesService.getDetectFiles(id);
        return success(DetectFilesConvert.INSTANCE.convert(detectFiles));
    }

    @GetMapping("/list")
    @Operation(summary = "获得文件列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('motocycleDetect:detect-files:query')")
    public CommonResult<List<DetectFilesRespVO>> getDetectFilesList(@RequestParam("ids") Collection<Long> ids) {
        List<DetectFilesDO> list = detectFilesService.getDetectFilesList(ids);
        return success(DetectFilesConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/list-analysis")
    @Operation(summary = "获取文件分析数据")
    public CommonResult<List<DetectFilesRespVO>> getDetectAnalysisFilesList() {
        List<DetectFilesDO> list = detectFilesService.getDetectAnalysisFilesList();
        return success(DetectFilesConvert.INSTANCE.convertList(list));
    }


    @GetMapping("/all")
    @Operation(summary = "获取所有文件")
    @PreAuthorize("@ss.hasPermission('motocycleDetect:detect-files:query')")
    public CommonResult<List<DetectFilesRespVO>> getDetectFilesList() {
        List<DetectFilesDO> list = detectFilesService.getAllDetectFilesList();
        return success(DetectFilesConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得文件分页")
    @PreAuthorize("@ss.hasPermission('motocycleDetect:detect-files:query')")
    public CommonResult<PageResult<DetectFilesRespVO>> getDetectFilesPage(@Valid DetectFilesPageReqVO pageVO) {
        PageResult<DetectFilesDO> pageResult = detectFilesService.getDetectFilesPage(pageVO);
        return success(DetectFilesConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出文件 Excel")
    @PreAuthorize("@ss.hasPermission('motocycleDetect:detect-files:export')")
    @OperateLog(type = EXPORT)
    public void exportDetectFilesExcel(@Valid DetectFilesExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<DetectFilesDO> list = detectFilesService.getDetectFilesList(exportReqVO);
        // 导出 Excel
        List<DetectFilesExcelVO> datas = DetectFilesConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "文件.xls", "数据", DetectFilesExcelVO.class, datas);
    }

}
