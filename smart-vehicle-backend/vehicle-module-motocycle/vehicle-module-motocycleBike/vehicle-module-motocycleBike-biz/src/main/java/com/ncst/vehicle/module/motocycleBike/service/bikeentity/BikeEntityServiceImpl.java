package com.ncst.vehicle.module.motocycleBike.service.bikeentity;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import com.ncst.vehicle.module.motocycleBike.controller.admin.bikeentity.vo.*;
import com.ncst.vehicle.module.motocycleBike.dal.dataobject.bikeentity.BikeEntityDO;
import com.ncst.vehicle.framework.common.pojo.PageResult;

import com.ncst.vehicle.module.motocycleBike.convert.bikeentity.BikeEntityConvert;
import com.ncst.vehicle.module.motocycleBike.dal.mysql.bikeentity.BikeEntityMapper;

import static com.ncst.vehicle.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.ncst.vehicle.module.motocycleBike.enums.ErrorCodeConstants.*;

/**
 * 电车实体 Service 实现类
 *
 * @author ylyhappy
 */
@Service
@Validated
public class BikeEntityServiceImpl implements BikeEntityService {

    @Resource
    private BikeEntityMapper bikeEntityMapper;

    @Override
    public Long createBikeEntity(BikeEntityCreateReqVO createReqVO) {
        // 插入
        BikeEntityDO bikeEntity = BikeEntityConvert.INSTANCE.convert(createReqVO);
        bikeEntityMapper.insert(bikeEntity);
        // 返回
        return bikeEntity.getId();
    }

    @Override
    public void updateBikeEntity(BikeEntityUpdateReqVO updateReqVO) {
        // 校验存在
        validateBikeEntityExists(updateReqVO.getId());
        // 更新
        BikeEntityDO updateObj = BikeEntityConvert.INSTANCE.convert(updateReqVO);
        bikeEntityMapper.updateById(updateObj);
    }

    @Override
    public void deleteBikeEntity(Long id) {
        // 校验存在
        validateBikeEntityExists(id);
        // 删除
        bikeEntityMapper.deleteById(id);
    }

    private void validateBikeEntityExists(Long id) {
        if (bikeEntityMapper.selectById(id) == null) {
            throw exception(BIKE_ENTITY_NOT_EXISTS);
        }
    }

    @Override
    public BikeEntityDO getBikeEntity(Long id) {
        return bikeEntityMapper.selectById(id);
    }

    @Override
    public List<BikeEntityDO> getBikeEntityList(Collection<Long> ids) {
        return bikeEntityMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<BikeEntityDO> getBikeEntityPage(BikeEntityPageReqVO pageReqVO) {
        return bikeEntityMapper.selectPage(pageReqVO);
    }

    @Override
    public List<BikeEntityDO> getBikeEntityList(BikeEntityExportReqVO exportReqVO) {
        return bikeEntityMapper.selectList(exportReqVO);
    }

}
