package com.ncst.vehicle.framework.idempotent.config;

import com.ncst.vehicle.framework.idempotent.core.aop.IdempotentAspect;
import com.ncst.vehicle.framework.idempotent.core.keyresolver.impl.DefaultIdempotentKeyResolver;
import com.ncst.vehicle.framework.idempotent.core.keyresolver.impl.ExpressionIdempotentKeyResolver;
import com.ncst.vehicle.framework.idempotent.core.keyresolver.IdempotentKeyResolver;
import com.ncst.vehicle.framework.idempotent.core.redis.IdempotentRedisDAO;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import com.ncst.vehicle.framework.redis.config.VehicleRedisAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.List;

@AutoConfiguration(after = VehicleRedisAutoConfiguration.class)
public class VehicleIdempotentConfiguration {

    @Bean
    public IdempotentAspect idempotentAspect(List<IdempotentKeyResolver> keyResolvers, IdempotentRedisDAO idempotentRedisDAO) {
        return new IdempotentAspect(keyResolvers, idempotentRedisDAO);
    }

    @Bean
    public IdempotentRedisDAO idempotentRedisDAO(StringRedisTemplate stringRedisTemplate) {
        return new IdempotentRedisDAO(stringRedisTemplate);
    }

    // ========== 各种 IdempotentKeyResolver Bean ==========

    @Bean
    public DefaultIdempotentKeyResolver defaultIdempotentKeyResolver() {
        return new DefaultIdempotentKeyResolver();
    }

    @Bean
    public ExpressionIdempotentKeyResolver expressionIdempotentKeyResolver() {
        return new ExpressionIdempotentKeyResolver();
    }

}
