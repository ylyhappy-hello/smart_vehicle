package com.ncst.vehicle.module.motocycleBike.controller.admin.bikereportthelossof;

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

import com.ncst.vehicle.module.motocycleBike.controller.admin.bikereportthelossof.vo.*;
import com.ncst.vehicle.module.motocycleBike.dal.dataobject.bikereportthelossof.BikeReportTheLossOfDO;
import com.ncst.vehicle.module.motocycleBike.convert.bikereportthelossof.BikeReportTheLossOfConvert;
import com.ncst.vehicle.module.motocycleBike.service.bikereportthelossof.BikeReportTheLossOfService;

@Tag(name = "管理后台 - 电车挂失信息")
@RestController
@RequestMapping("/motocycleBike/bike-report-the-loss-of")
@Validated
public class BikeReportTheLossOfController {

    @Resource
    private BikeReportTheLossOfService bikeReportTheLossOfService;

    @PostMapping("/create")
    @Operation(summary = "创建电车挂失信息")
    @PreAuthorize("@ss.hasPermission('motocycleBike:bike-report-the-loss-of:create')")
    public CommonResult<Long> createBikeReportTheLossOf(@Valid @RequestBody BikeReportTheLossOfCreateReqVO createReqVO) {
        return success(bikeReportTheLossOfService.createBikeReportTheLossOf(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新电车挂失信息")
    @PreAuthorize("@ss.hasPermission('motocycleBike:bike-report-the-loss-of:update')")
    public CommonResult<Boolean> updateBikeReportTheLossOf(@Valid @RequestBody BikeReportTheLossOfUpdateReqVO updateReqVO) {
        bikeReportTheLossOfService.updateBikeReportTheLossOf(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除电车挂失信息")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('motocycleBike:bike-report-the-loss-of:delete')")
    public CommonResult<Boolean> deleteBikeReportTheLossOf(@RequestParam("id") Long id) {
        bikeReportTheLossOfService.deleteBikeReportTheLossOf(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得电车挂失信息")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('motocycleBike:bike-report-the-loss-of:query')")
    public CommonResult<BikeReportTheLossOfRespVO> getBikeReportTheLossOf(@RequestParam("id") Long id) {
        BikeReportTheLossOfDO bikeReportTheLossOf = bikeReportTheLossOfService.getBikeReportTheLossOf(id);
        return success(BikeReportTheLossOfConvert.INSTANCE.convert(bikeReportTheLossOf));
    }

    @GetMapping("/list")
    @Operation(summary = "获得电车挂失信息列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('motocycleBike:bike-report-the-loss-of:query')")
    public CommonResult<List<BikeReportTheLossOfRespVO>> getBikeReportTheLossOfList(@RequestParam("ids") Collection<Long> ids) {
        List<BikeReportTheLossOfDO> list = bikeReportTheLossOfService.getBikeReportTheLossOfList(ids);
        return success(BikeReportTheLossOfConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得电车挂失信息分页")
    @PreAuthorize("@ss.hasPermission('motocycleBike:bike-report-the-loss-of:query')")
    public CommonResult<PageResult<BikeReportTheLossOfRespVO>> getBikeReportTheLossOfPage(@Valid BikeReportTheLossOfPageReqVO pageVO) {
        PageResult<BikeReportTheLossOfDO> pageResult = bikeReportTheLossOfService.getBikeReportTheLossOfPage(pageVO);
        return success(BikeReportTheLossOfConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出电车挂失信息 Excel")
    @PreAuthorize("@ss.hasPermission('motocycleBike:bike-report-the-loss-of:export')")
    @OperateLog(type = EXPORT)
    public void exportBikeReportTheLossOfExcel(@Valid BikeReportTheLossOfExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<BikeReportTheLossOfDO> list = bikeReportTheLossOfService.getBikeReportTheLossOfList(exportReqVO);
        // 导出 Excel
        List<BikeReportTheLossOfExcelVO> datas = BikeReportTheLossOfConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "电车挂失信息.xls", "数据", BikeReportTheLossOfExcelVO.class, datas);
    }

}
