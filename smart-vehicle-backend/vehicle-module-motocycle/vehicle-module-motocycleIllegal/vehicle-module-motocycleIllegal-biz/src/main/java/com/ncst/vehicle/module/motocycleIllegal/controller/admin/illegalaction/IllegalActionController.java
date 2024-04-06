package com.ncst.vehicle.module.motocycleIllegal.controller.admin.illegalaction;

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

import com.ncst.vehicle.module.motocycleIllegal.controller.admin.illegalaction.vo.*;
import com.ncst.vehicle.module.motocycleIllegal.dal.dataobject.illegalaction.IllegalActionDO;
import com.ncst.vehicle.module.motocycleIllegal.convert.illegalaction.IllegalActionConvert;
import com.ncst.vehicle.module.motocycleIllegal.service.illegalaction.IllegalActionService;

@Tag(name = "管理后台 - 模型训练记录")
@RestController
@RequestMapping("/motocycleIllegal/illegal-action")
@Validated
public class IllegalActionController {

    @Resource
    private IllegalActionService illegalActionService;

    @PostMapping("/create")
    @Operation(summary = "创建模型训练记录")
    @PreAuthorize("@ss.hasPermission('motocycleIllegal:illegal-action:create')")
    public CommonResult<Long> createIllegalAction(@Valid @RequestBody IllegalActionCreateReqVO createReqVO) {
        return success(illegalActionService.createIllegalAction(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新模型训练记录")
    @PreAuthorize("@ss.hasPermission('motocycleIllegal:illegal-action:update')")
    public CommonResult<Boolean> updateIllegalAction(@Valid @RequestBody IllegalActionUpdateReqVO updateReqVO) {
        illegalActionService.updateIllegalAction(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除模型训练记录")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('motocycleIllegal:illegal-action:delete')")
    public CommonResult<Boolean> deleteIllegalAction(@RequestParam("id") Long id) {
        illegalActionService.deleteIllegalAction(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得模型训练记录")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('motocycleIllegal:illegal-action:query')")
    public CommonResult<IllegalActionRespVO> getIllegalAction(@RequestParam("id") Long id) {
        IllegalActionDO illegalAction = illegalActionService.getIllegalAction(id);
        return success(IllegalActionConvert.INSTANCE.convert(illegalAction));
    }

    @GetMapping("/list")
    @Operation(summary = "获得模型训练记录列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('motocycleIllegal:illegal-action:query')")
    public CommonResult<List<IllegalActionRespVO>> getIllegalActionList(@RequestParam("ids") Collection<Long> ids) {
        List<IllegalActionDO> list = illegalActionService.getIllegalActionList(ids);
        return success(IllegalActionConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得模型训练记录分页")
    @PreAuthorize("@ss.hasPermission('motocycleIllegal:illegal-action:query')")
    public CommonResult<PageResult<IllegalActionRespVO>> getIllegalActionPage(@Valid IllegalActionPageReqVO pageVO) {
        PageResult<IllegalActionDO> pageResult = illegalActionService.getIllegalActionPage(pageVO);
        return success(IllegalActionConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出模型训练记录 Excel")
    @PreAuthorize("@ss.hasPermission('motocycleIllegal:illegal-action:export')")
    @OperateLog(type = EXPORT)
    public void exportIllegalActionExcel(@Valid IllegalActionExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<IllegalActionDO> list = illegalActionService.getIllegalActionList(exportReqVO);
        // 导出 Excel
        List<IllegalActionExcelVO> datas = IllegalActionConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "模型训练记录.xls", "数据", IllegalActionExcelVO.class, datas);
    }

}
