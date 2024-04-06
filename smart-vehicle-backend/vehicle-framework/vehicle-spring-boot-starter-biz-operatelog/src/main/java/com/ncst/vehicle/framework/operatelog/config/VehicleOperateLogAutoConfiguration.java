package com.ncst.vehicle.framework.operatelog.config;

import com.ncst.vehicle.framework.operatelog.core.aop.OperateLogAspect;
import com.ncst.vehicle.framework.operatelog.core.service.OperateLogFrameworkService;
import com.ncst.vehicle.framework.operatelog.core.service.OperateLogFrameworkServiceImpl;
import com.ncst.vehicle.module.system.api.logger.OperateLogApi;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;

@AutoConfiguration
public class VehicleOperateLogAutoConfiguration {

    @Bean
    public OperateLogAspect operateLogAspect() {
        return new OperateLogAspect();
    }

    @Bean
    public OperateLogFrameworkService operateLogFrameworkService(OperateLogApi operateLogApi) {
        return new OperateLogFrameworkServiceImpl(operateLogApi);
    }

}
