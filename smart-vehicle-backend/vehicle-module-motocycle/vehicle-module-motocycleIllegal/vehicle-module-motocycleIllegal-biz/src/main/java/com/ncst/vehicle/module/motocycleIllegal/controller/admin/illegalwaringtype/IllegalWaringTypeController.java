package com.ncst.vehicle.module.motocycleIllegal.controller.admin.illegalwaringtype;

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

import com.ncst.vehicle.module.motocycleIllegal.controller.admin.illegalwaringtype.vo.*;
import com.ncst.vehicle.module.motocycleIllegal.dal.dataobject.illegalwaringtype.IllegalWaringTypeDO;
import com.ncst.vehicle.module.motocycleIllegal.convert.illegalwaringtype.IllegalWaringTypeConvert;
import com.ncst.vehicle.module.motocycleIllegal.service.illegalwaringtype.IllegalWaringTypeService;

@Tag(name = "管理后台 - 违规处罚力度")
@RestController
@RequestMapping("/motocycleIllegal/illegal-waring-type")
@Validated
public class IllegalWaringTypeController {

    @Resource
    private IllegalWaringTypeService illegalWaringTypeService;

    @PostMapping("/create")
    @Operation(summary = "创建违规处罚力度")
    @PreAuthorize("@ss.hasPermission('motocycleIllegal:illegal-waring-type:create')")
    public CommonResult<Long> createIllegalWaringType(@Valid @RequestBody IllegalWaringTypeCreateReqVO createReqVO) {
        return success(illegalWaringTypeService.createIllegalWaringType(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新违规处罚力度")
    @PreAuthorize("@ss.hasPermission('motocycleIllegal:illegal-waring-type:update')")
    public CommonResult<Boolean> updateIllegalWaringType(@Valid @RequestBody IllegalWaringTypeUpdateReqVO updateReqVO) {
        illegalWaringTypeService.updateIllegalWaringType(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除违规处罚力度")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('motocycleIllegal:illegal-waring-type:delete')")
    public CommonResult<Boolean> deleteIllegalWaringType(@RequestParam("id") Long id) {
        illegalWaringTypeService.deleteIllegalWaringType(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得违规处罚力度")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('motocycleIllegal:illegal-waring-type:query')")
    public CommonResult<IllegalWaringTypeRespVO> getIllegalWaringType(@RequestParam("id") Long id) {
        IllegalWaringTypeDO illegalWaringType = illegalWaringTypeService.getIllegalWaringType(id);
        return success(IllegalWaringTypeConvert.INSTANCE.convert(illegalWaringType));
    }

    @GetMapping("/list")
    @Operation(summary = "获得违规处罚力度列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('motocycleIllegal:illegal-waring-type:query')")
    public CommonResult<List<IllegalWaringTypeRespVO>> getIllegalWaringTypeList(@RequestParam("ids") Collection<Long> ids) {
        List<IllegalWaringTypeDO> list = illegalWaringTypeService.getIllegalWaringTypeList(ids);
        return success(IllegalWaringTypeConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得违规处罚力度分页")
    @PreAuthorize("@ss.hasPermission('motocycleIllegal:illegal-waring-type:query')")
    public CommonResult<PageResult<IllegalWaringTypeRespVO>> getIllegalWaringTypePage(@Valid IllegalWaringTypePageReqVO pageVO) {
        PageResult<IllegalWaringTypeDO> pageResult = illegalWaringTypeService.getIllegalWaringTypePage(pageVO);
        return success(IllegalWaringTypeConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出违规处罚力度 Excel")
    @PreAuthorize("@ss.hasPermission('motocycleIllegal:illegal-waring-type:export')")
    @OperateLog(type = EXPORT)
    public void exportIllegalWaringTypeExcel(@Valid IllegalWaringTypeExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<IllegalWaringTypeDO> list = illegalWaringTypeService.getIllegalWaringTypeList(exportReqVO);
        // 导出 Excel
        List<IllegalWaringTypeExcelVO> datas = IllegalWaringTypeConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "违规处罚力度.xls", "数据", IllegalWaringTypeExcelVO.class, datas);
    }

}
