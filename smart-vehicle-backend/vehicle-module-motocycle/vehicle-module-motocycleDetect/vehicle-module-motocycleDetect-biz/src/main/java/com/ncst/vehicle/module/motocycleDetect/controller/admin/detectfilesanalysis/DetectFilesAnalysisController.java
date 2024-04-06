package com.ncst.vehicle.module.motocycleDetect.controller.admin.detectfilesanalysis;

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

import com.ncst.vehicle.module.motocycleDetect.controller.admin.detectfilesanalysis.vo.*;
import com.ncst.vehicle.module.motocycleDetect.dal.dataobject.detectfilesanalysis.DetectFilesAnalysisDO;
import com.ncst.vehicle.module.motocycleDetect.convert.detectfilesanalysis.DetectFilesAnalysisConvert;
import com.ncst.vehicle.module.motocycleDetect.service.detectfilesanalysis.DetectFilesAnalysisService;

@Tag(name = "管理后台 - 检测文件结果分析")
@RestController
@RequestMapping("/motocycleDetect/detect-files-analysis")
@Validated
public class DetectFilesAnalysisController {

    @Resource
    private DetectFilesAnalysisService detectFilesAnalysisService;

    @PostMapping("/create")
    @Operation(summary = "创建检测文件结果分析")
    @PreAuthorize("@ss.hasPermission('motocycleDetect:detect-files-analysis:create')")
    public CommonResult<Long> createDetectFilesAnalysis(@Valid @RequestBody DetectFilesAnalysisCreateReqVO createReqVO) {
        return success(detectFilesAnalysisService.createDetectFilesAnalysis(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新检测文件结果分析")
    @PreAuthorize("@ss.hasPermission('motocycleDetect:detect-files-analysis:update')")
    public CommonResult<Boolean> updateDetectFilesAnalysis(@Valid @RequestBody DetectFilesAnalysisUpdateReqVO updateReqVO) {
        detectFilesAnalysisService.updateDetectFilesAnalysis(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除检测文件结果分析")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('motocycleDetect:detect-files-analysis:delete')")
    public CommonResult<Boolean> deleteDetectFilesAnalysis(@RequestParam("id") Long id) {
        detectFilesAnalysisService.deleteDetectFilesAnalysis(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得检测文件结果分析")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('motocycleDetect:detect-files-analysis:query')")
    public CommonResult<DetectFilesAnalysisRespVO> getDetectFilesAnalysis(@RequestParam("id") Long id) {
        DetectFilesAnalysisDO detectFilesAnalysis = detectFilesAnalysisService.getDetectFilesAnalysis(id);
        return success(DetectFilesAnalysisConvert.INSTANCE.convert(detectFilesAnalysis));
    }

    @GetMapping("/list")
    @Operation(summary = "获得检测文件结果分析列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('motocycleDetect:detect-files-analysis:query')")
    public CommonResult<List<DetectFilesAnalysisRespVO>> getDetectFilesAnalysisList(@RequestParam("ids") Collection<Long> ids) {
        List<DetectFilesAnalysisDO> list = detectFilesAnalysisService.getDetectFilesAnalysisList(ids);
        return success(DetectFilesAnalysisConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得检测文件结果分析分页")
    @PreAuthorize("@ss.hasPermission('motocycleDetect:detect-files-analysis:query')")
    public CommonResult<PageResult<DetectFilesAnalysisRespVO>> getDetectFilesAnalysisPage(@Valid DetectFilesAnalysisPageReqVO pageVO) {
        PageResult<DetectFilesAnalysisDO> pageResult = detectFilesAnalysisService.getDetectFilesAnalysisPage(pageVO);
        return success(DetectFilesAnalysisConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出检测文件结果分析 Excel")
    @PreAuthorize("@ss.hasPermission('motocycleDetect:detect-files-analysis:export')")
    @OperateLog(type = EXPORT)
    public void exportDetectFilesAnalysisExcel(@Valid DetectFilesAnalysisExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<DetectFilesAnalysisDO> list = detectFilesAnalysisService.getDetectFilesAnalysisList(exportReqVO);
        // 导出 Excel
        List<DetectFilesAnalysisExcelVO> datas = DetectFilesAnalysisConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "检测文件结果分析.xls", "数据", DetectFilesAnalysisExcelVO.class, datas);
    }

}
