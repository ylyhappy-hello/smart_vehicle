package com.ncst.vehicle.module.equipment.controller.admin;

import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;

import javax.validation.*;
import java.util.*;

import com.ncst.vehicle.framework.common.pojo.CommonResult;
import static com.ncst.vehicle.framework.common.pojo.CommonResult.success;


import com.ncst.vehicle.module.equipment.DeviceInfo;
import com.ncst.vehicle.module.equipment.EquimentService;
import com.ncst.vehicle.module.equipment.EquipmentVo;


@Tag(name = "管理后台 - 设备管理")
@RestController
@RequestMapping("/motocycleEquiment/equipment")
@Validated
public class EquimentController {

    @Resource
    private EquimentService equimentService;

    @PostMapping("/list")
    @Operation(summary = "获取设备列表")
    @PreAuthorize("@ss.hasPermission('motocycleAccident:accident:create')")
    public CommonResult<List<DeviceInfo>> createAccident() {
        return success(equimentService.getList());
    }

}
