package com.ncst.vehicle.module.motocycleBike.service.bikereportthelossof;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import com.ncst.vehicle.module.motocycleBike.controller.admin.bikereportthelossof.vo.*;
import com.ncst.vehicle.module.motocycleBike.dal.dataobject.bikereportthelossof.BikeReportTheLossOfDO;
import com.ncst.vehicle.framework.common.pojo.PageResult;

import com.ncst.vehicle.module.motocycleBike.convert.bikereportthelossof.BikeReportTheLossOfConvert;
import com.ncst.vehicle.module.motocycleBike.dal.mysql.bikereportthelossof.BikeReportTheLossOfMapper;

import static com.ncst.vehicle.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.ncst.vehicle.module.motocycleBike.enums.ErrorCodeConstants.*;

/**
 * 电车挂失信息 Service 实现类
 *
 * @author ylyhappy
 */
@Service
@Validated
public class BikeReportTheLossOfServiceImpl implements BikeReportTheLossOfService {

    @Resource
    private BikeReportTheLossOfMapper bikeReportTheLossOfMapper;

    @Override
    public Long createBikeReportTheLossOf(BikeReportTheLossOfCreateReqVO createReqVO) {
        // 插入
        BikeReportTheLossOfDO bikeReportTheLossOf = BikeReportTheLossOfConvert.INSTANCE.convert(createReqVO);
        bikeReportTheLossOfMapper.insert(bikeReportTheLossOf);
        // 返回
        return bikeReportTheLossOf.getId();
    }

    @Override
    public void updateBikeReportTheLossOf(BikeReportTheLossOfUpdateReqVO updateReqVO) {
        // 校验存在
        validateBikeReportTheLossOfExists(updateReqVO.getId());
        // 更新
        BikeReportTheLossOfDO updateObj = BikeReportTheLossOfConvert.INSTANCE.convert(updateReqVO);
        bikeReportTheLossOfMapper.updateById(updateObj);
    }

    @Override
    public void deleteBikeReportTheLossOf(Long id) {
        // 校验存在
        validateBikeReportTheLossOfExists(id);
        // 删除
        bikeReportTheLossOfMapper.deleteById(id);
    }

    private void validateBikeReportTheLossOfExists(Long id) {
        if (bikeReportTheLossOfMapper.selectById(id) == null) {
            throw exception(BIKE_REPORT_THE_LOSS_OF_NOT_EXISTS);
        }
    }

    @Override
    public BikeReportTheLossOfDO getBikeReportTheLossOf(Long id) {
        return bikeReportTheLossOfMapper.selectById(id);
    }

    @Override
    public List<BikeReportTheLossOfDO> getBikeReportTheLossOfList(Collection<Long> ids) {
        return bikeReportTheLossOfMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<BikeReportTheLossOfDO> getBikeReportTheLossOfPage(BikeReportTheLossOfPageReqVO pageReqVO) {
        return bikeReportTheLossOfMapper.selectPage(pageReqVO);
    }

    @Override
    public List<BikeReportTheLossOfDO> getBikeReportTheLossOfList(BikeReportTheLossOfExportReqVO exportReqVO) {
        return bikeReportTheLossOfMapper.selectList(exportReqVO);
    }

}
