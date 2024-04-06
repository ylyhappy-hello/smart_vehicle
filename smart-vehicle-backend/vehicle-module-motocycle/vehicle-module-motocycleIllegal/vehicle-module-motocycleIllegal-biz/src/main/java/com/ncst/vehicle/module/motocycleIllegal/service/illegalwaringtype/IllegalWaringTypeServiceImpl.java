package com.ncst.vehicle.module.motocycleIllegal.service.illegalwaringtype;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import com.ncst.vehicle.module.motocycleIllegal.controller.admin.illegalwaringtype.vo.*;
import com.ncst.vehicle.module.motocycleIllegal.dal.dataobject.illegalwaringtype.IllegalWaringTypeDO;
import com.ncst.vehicle.framework.common.pojo.PageResult;

import com.ncst.vehicle.module.motocycleIllegal.convert.illegalwaringtype.IllegalWaringTypeConvert;
import com.ncst.vehicle.module.motocycleIllegal.dal.mysql.illegalwaringtype.IllegalWaringTypeMapper;

import static com.ncst.vehicle.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.ncst.vehicle.module.motocycleIllegal.enums.ErrorCodeConstants.*;

/**
 * 违规处罚力度 Service 实现类
 *
 * @author ylyhappy
 */
@Service
@Validated
public class IllegalWaringTypeServiceImpl implements IllegalWaringTypeService {

    @Resource
    private IllegalWaringTypeMapper illegalWaringTypeMapper;

    @Override
    public Long createIllegalWaringType(IllegalWaringTypeCreateReqVO createReqVO) {
        // 插入
        IllegalWaringTypeDO illegalWaringType = IllegalWaringTypeConvert.INSTANCE.convert(createReqVO);
        illegalWaringTypeMapper.insert(illegalWaringType);
        // 返回
        return illegalWaringType.getId();
    }

    @Override
    public void updateIllegalWaringType(IllegalWaringTypeUpdateReqVO updateReqVO) {
        // 校验存在
        validateIllegalWaringTypeExists(updateReqVO.getId());
        // 更新
        IllegalWaringTypeDO updateObj = IllegalWaringTypeConvert.INSTANCE.convert(updateReqVO);
        illegalWaringTypeMapper.updateById(updateObj);
    }

    @Override
    public void deleteIllegalWaringType(Long id) {
        // 校验存在
        validateIllegalWaringTypeExists(id);
        // 删除
        illegalWaringTypeMapper.deleteById(id);
    }

    private void validateIllegalWaringTypeExists(Long id) {
        if (illegalWaringTypeMapper.selectById(id) == null) {
            throw exception(ILLEGAL_WARING_TYPE_NOT_EXISTS);
        }
    }

    @Override
    public IllegalWaringTypeDO getIllegalWaringType(Long id) {
        return illegalWaringTypeMapper.selectById(id);
    }

    @Override
    public List<IllegalWaringTypeDO> getIllegalWaringTypeList(Collection<Long> ids) {
        return illegalWaringTypeMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<IllegalWaringTypeDO> getIllegalWaringTypePage(IllegalWaringTypePageReqVO pageReqVO) {
        return illegalWaringTypeMapper.selectPage(pageReqVO);
    }

    @Override
    public List<IllegalWaringTypeDO> getIllegalWaringTypeList(IllegalWaringTypeExportReqVO exportReqVO) {
        return illegalWaringTypeMapper.selectList(exportReqVO);
    }

}
