package com.ncst.vehicle.module.motocycleIllegal.controller.admin.illegalwaringtemplate;

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

import com.ncst.vehicle.module.motocycleIllegal.controller.admin.illegalwaringtemplate.vo.*;
import com.ncst.vehicle.module.motocycleIllegal.dal.dataobject.illegalwaringtemplate.IllegalWaringTemplateDO;
import com.ncst.vehicle.module.motocycleIllegal.convert.illegalwaringtemplate.IllegalWaringTemplateConvert;
import com.ncst.vehicle.module.motocycleIllegal.service.illegalwaringtemplate.IllegalWaringTemplateService;

@Tag(name = "管理后台 - 违规警告模板")
@RestController
@RequestMapping("/motocycleIllegal/illegal-waring-template")
@Validated
public class IllegalWaringTemplateController {

    @Resource
    private IllegalWaringTemplateService illegalWaringTemplateService;

    @PostMapping("/create")
    @Operation(summary = "创建违规警告模板")
    @PreAuthorize("@ss.hasPermission('motocycleIllegal:illegal-waring-template:create')")
    public CommonResult<Long> createIllegalWaringTemplate(@Valid @RequestBody IllegalWaringTemplateCreateReqVO createReqVO) {
        return success(illegalWaringTemplateService.createIllegalWaringTemplate(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新违规警告模板")
    @PreAuthorize("@ss.hasPermission('motocycleIllegal:illegal-waring-template:update')")
    public CommonResult<Boolean> updateIllegalWaringTemplate(@Valid @RequestBody IllegalWaringTemplateUpdateReqVO updateReqVO) {
        illegalWaringTemplateService.updateIllegalWaringTemplate(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除违规警告模板")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('motocycleIllegal:illegal-waring-template:delete')")
    public CommonResult<Boolean> deleteIllegalWaringTemplate(@RequestParam("id") Long id) {
        illegalWaringTemplateService.deleteIllegalWaringTemplate(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得违规警告模板")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('motocycleIllegal:illegal-waring-template:query')")
    public CommonResult<IllegalWaringTemplateRespVO> getIllegalWaringTemplate(@RequestParam("id") Long id) {
        IllegalWaringTemplateDO illegalWaringTemplate = illegalWaringTemplateService.getIllegalWaringTemplate(id);
        return success(IllegalWaringTemplateConvert.INSTANCE.convert(illegalWaringTemplate));
    }

    @GetMapping("/list")
    @Operation(summary = "获得违规警告模板列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('motocycleIllegal:illegal-waring-template:query')")
    public CommonResult<List<IllegalWaringTemplateRespVO>> getIllegalWaringTemplateList(@RequestParam("ids") Collection<Long> ids) {
        List<IllegalWaringTemplateDO> list = illegalWaringTemplateService.getIllegalWaringTemplateList(ids);
        return success(IllegalWaringTemplateConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得违规警告模板分页")
    @PreAuthorize("@ss.hasPermission('motocycleIllegal:illegal-waring-template:query')")
    public CommonResult<PageResult<IllegalWaringTemplateRespVO>> getIllegalWaringTemplatePage(@Valid IllegalWaringTemplatePageReqVO pageVO) {
        PageResult<IllegalWaringTemplateDO> pageResult = illegalWaringTemplateService.getIllegalWaringTemplatePage(pageVO);
        return success(IllegalWaringTemplateConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出违规警告模板 Excel")
    @PreAuthorize("@ss.hasPermission('motocycleIllegal:illegal-waring-template:export')")
    @OperateLog(type = EXPORT)
    public void exportIllegalWaringTemplateExcel(@Valid IllegalWaringTemplateExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<IllegalWaringTemplateDO> list = illegalWaringTemplateService.getIllegalWaringTemplateList(exportReqVO);
        // 导出 Excel
        List<IllegalWaringTemplateExcelVO> datas = IllegalWaringTemplateConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "违规警告模板.xls", "数据", IllegalWaringTemplateExcelVO.class, datas);
    }

}
