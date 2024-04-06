package com.ncst.vehicle.module.motocycleIllegal.controller.admin.illegalauditlist;

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

import com.ncst.vehicle.module.motocycleIllegal.controller.admin.illegalauditlist.vo.*;
import com.ncst.vehicle.module.motocycleIllegal.dal.dataobject.illegalauditlist.IllegalAuditListDO;
import com.ncst.vehicle.module.motocycleIllegal.convert.illegalauditlist.IllegalAuditListConvert;
import com.ncst.vehicle.module.motocycleIllegal.service.illegalauditlist.IllegalAuditListService;

@Tag(name = "管理后台 - 违规审核")
@RestController
@RequestMapping("/motocycleIllegal/illegal-audit-list")
@Validated
public class IllegalAuditListController {

    @Resource
    private IllegalAuditListService illegalAuditListService;

    @PostMapping("/create")
    @Operation(summary = "创建违规审核")
    @PreAuthorize("@ss.hasPermission('motocycleIllegal:illegal-audit-list:create')")
    public CommonResult<Long> createIllegalAuditList(@Valid @RequestBody IllegalAuditListCreateReqVO createReqVO) {
        return success(illegalAuditListService.createIllegalAuditList(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新违规审核")
    @PreAuthorize("@ss.hasPermission('motocycleIllegal:illegal-audit-list:update')")
    public CommonResult<Boolean> updateIllegalAuditList(@Valid @RequestBody IllegalAuditListUpdateReqVO updateReqVO) {
        illegalAuditListService.updateIllegalAuditList(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除违规审核")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('motocycleIllegal:illegal-audit-list:delete')")
    public CommonResult<Boolean> deleteIllegalAuditList(@RequestParam("id") Long id) {
        illegalAuditListService.deleteIllegalAuditList(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得违规审核")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('motocycleIllegal:illegal-audit-list:query')")
    public CommonResult<IllegalAuditListRespVO> getIllegalAuditList(@RequestParam("id") Long id) {
        IllegalAuditListDO illegalAuditList = illegalAuditListService.getIllegalAuditList(id);
        return success(IllegalAuditListConvert.INSTANCE.convert(illegalAuditList));
    }

    @GetMapping("/list")
    @Operation(summary = "获得违规审核列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('motocycleIllegal:illegal-audit-list:query')")
    public CommonResult<List<IllegalAuditListRespVO>> getIllegalAuditListList(@RequestParam("ids") Collection<Long> ids) {
        List<IllegalAuditListDO> list = illegalAuditListService.getIllegalAuditListList(ids);
        return success(IllegalAuditListConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得违规审核分页")
    @PreAuthorize("@ss.hasPermission('motocycleIllegal:illegal-audit-list:query')")
    public CommonResult<PageResult<IllegalAuditListRespVO>> getIllegalAuditListPage(@Valid IllegalAuditListPageReqVO pageVO) {
        PageResult<IllegalAuditListDO> pageResult = illegalAuditListService.getIllegalAuditListPage(pageVO);
        return success(IllegalAuditListConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出违规审核 Excel")
    @PreAuthorize("@ss.hasPermission('motocycleIllegal:illegal-audit-list:export')")
    @OperateLog(type = EXPORT)
    public void exportIllegalAuditListExcel(@Valid IllegalAuditListExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<IllegalAuditListDO> list = illegalAuditListService.getIllegalAuditListList(exportReqVO);
        // 导出 Excel
        List<IllegalAuditListExcelVO> datas = IllegalAuditListConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "违规审核.xls", "数据", IllegalAuditListExcelVO.class, datas);
    }

}
