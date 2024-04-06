package com.ncst.vehicle.module.motocycleBike.controller.admin.bikeentity;

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

import com.ncst.vehicle.module.motocycleBike.controller.admin.bikeentity.vo.*;
import com.ncst.vehicle.module.motocycleBike.dal.dataobject.bikeentity.BikeEntityDO;
import com.ncst.vehicle.module.motocycleBike.convert.bikeentity.BikeEntityConvert;
import com.ncst.vehicle.module.motocycleBike.service.bikeentity.BikeEntityService;

@Tag(name = "管理后台 - 电车实体")
@RestController
@RequestMapping("/motocycleBike/bike-entity")
@Validated
public class BikeEntityController {

    @Resource
    private BikeEntityService bikeEntityService;

    @PostMapping("/create")
    @Operation(summary = "创建电车实体")
    @PreAuthorize("@ss.hasPermission('motocycleBike:bike-entity:create')")
    public CommonResult<Long> createBikeEntity(@Valid @RequestBody BikeEntityCreateReqVO createReqVO) {
        return success(bikeEntityService.createBikeEntity(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新电车实体")
    @PreAuthorize("@ss.hasPermission('motocycleBike:bike-entity:update')")
    public CommonResult<Boolean> updateBikeEntity(@Valid @RequestBody BikeEntityUpdateReqVO updateReqVO) {
        bikeEntityService.updateBikeEntity(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除电车实体")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('motocycleBike:bike-entity:delete')")
    public CommonResult<Boolean> deleteBikeEntity(@RequestParam("id") Long id) {
        bikeEntityService.deleteBikeEntity(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得电车实体")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('motocycleBike:bike-entity:query')")
    public CommonResult<BikeEntityRespVO> getBikeEntity(@RequestParam("id") Long id) {
        BikeEntityDO bikeEntity = bikeEntityService.getBikeEntity(id);
        return success(BikeEntityConvert.INSTANCE.convert(bikeEntity));
    }

    @GetMapping("/list")
    @Operation(summary = "获得电车实体列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    @PreAuthorize("@ss.hasPermission('motocycleBike:bike-entity:query')")
    public CommonResult<List<BikeEntityRespVO>> getBikeEntityList(@RequestParam("ids") Collection<Long> ids) {
        List<BikeEntityDO> list = bikeEntityService.getBikeEntityList(ids);
        return success(BikeEntityConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得电车实体分页")
    @PreAuthorize("@ss.hasPermission('motocycleBike:bike-entity:query')")
    public CommonResult<PageResult<BikeEntityRespVO>> getBikeEntityPage(@Valid BikeEntityPageReqVO pageVO) {
        PageResult<BikeEntityDO> pageResult = bikeEntityService.getBikeEntityPage(pageVO);
        return success(BikeEntityConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出电车实体 Excel")
    @PreAuthorize("@ss.hasPermission('motocycleBike:bike-entity:export')")
    @OperateLog(type = EXPORT)
    public void exportBikeEntityExcel(@Valid BikeEntityExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<BikeEntityDO> list = bikeEntityService.getBikeEntityList(exportReqVO);
        // 导出 Excel
        List<BikeEntityExcelVO> datas = BikeEntityConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "电车实体.xls", "数据", BikeEntityExcelVO.class, datas);
    }

}
