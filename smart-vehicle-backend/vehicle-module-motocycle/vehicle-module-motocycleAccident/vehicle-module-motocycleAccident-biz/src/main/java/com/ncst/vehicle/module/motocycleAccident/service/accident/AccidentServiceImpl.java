package com.ncst.vehicle.module.motocycleAccident.service.accident;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import com.ncst.vehicle.module.motocycleAccident.controller.admin.accident.vo.*;
import com.ncst.vehicle.module.motocycleAccident.dal.dataobject.accident.AccidentDO;
import com.ncst.vehicle.framework.common.pojo.PageResult;

import com.ncst.vehicle.module.motocycleAccident.convert.accident.AccidentConvert;
import com.ncst.vehicle.module.motocycleAccident.dal.mysql.accident.AccidentMapper;

import static com.ncst.vehicle.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.ncst.vehicle.module.motocycleAccident.enums.ErrorCodeConstants.*;

/**
 * 意外 Service 实现类
 *
 * @author ylyhappy
 */
@Service
@Validated
public class AccidentServiceImpl implements AccidentService {

    @Resource
    private AccidentMapper accidentMapper;

    @Override
    public Long createAccident(AccidentCreateReqVO createReqVO) {
        // 插入
        AccidentDO accident = AccidentConvert.INSTANCE.convert(createReqVO);
        accidentMapper.insert(accident);
        // 返回
        return accident.getId();
    }

    @Override
    public void updateAccident(AccidentUpdateReqVO updateReqVO) {
        // 校验存在
        validateAccidentExists(updateReqVO.getId());
        // 更新
        AccidentDO updateObj = AccidentConvert.INSTANCE.convert(updateReqVO);
        accidentMapper.updateById(updateObj);
    }

    @Override
    public void deleteAccident(Long id) {
        // 校验存在
        validateAccidentExists(id);
        // 删除
        accidentMapper.deleteById(id);
    }

    private void validateAccidentExists(Long id) {
        if (accidentMapper.selectById(id) == null) {
            throw exception(ACCIDENT_NOT_EXISTS);
        }
    }

    @Override
    public AccidentDO getAccident(Long id) {
        return accidentMapper.selectById(id);
    }

    @Override
    public List<AccidentDO> getAccidentList(Collection<Long> ids) {
        return accidentMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<AccidentDO> getAccidentPage(AccidentPageReqVO pageReqVO) {
        return accidentMapper.selectPage(pageReqVO);
    }

    @Override
    public List<AccidentDO> getAccidentList(AccidentExportReqVO exportReqVO) {
        return accidentMapper.selectList(exportReqVO);
    }

}
