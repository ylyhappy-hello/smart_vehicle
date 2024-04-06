package com.ncst.vehicle.module.motocycleIllegal.service.illegalaction;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import com.ncst.vehicle.module.motocycleIllegal.controller.admin.illegalaction.vo.*;
import com.ncst.vehicle.module.motocycleIllegal.dal.dataobject.illegalaction.IllegalActionDO;
import com.ncst.vehicle.framework.common.pojo.PageResult;

import com.ncst.vehicle.module.motocycleIllegal.convert.illegalaction.IllegalActionConvert;
import com.ncst.vehicle.module.motocycleIllegal.dal.mysql.illegalaction.IllegalActionMapper;

import static com.ncst.vehicle.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.ncst.vehicle.module.motocycleIllegal.enums.ErrorCodeConstants.*;

/**
 * 违规行为 Service 实现类
 *
 * @author ylyhappy
 */
@Service
@Validated
public class IllegalActionServiceImpl implements IllegalActionService {

    @Resource
    private IllegalActionMapper illegalActionMapper;

    @Override
    public Long createIllegalAction(IllegalActionCreateReqVO createReqVO) {
        // 插入
        IllegalActionDO illegalAction = IllegalActionConvert.INSTANCE.convert(createReqVO);
        illegalActionMapper.insert(illegalAction);
        // 返回
        return illegalAction.getId();
    }

    @Override
    public void updateIllegalAction(IllegalActionUpdateReqVO updateReqVO) {
        // 校验存在
        validateIllegalActionExists(updateReqVO.getId());
        // 更新
        IllegalActionDO updateObj = IllegalActionConvert.INSTANCE.convert(updateReqVO);
        illegalActionMapper.updateById(updateObj);
    }

    @Override
    public void deleteIllegalAction(Long id) {
        // 校验存在
        validateIllegalActionExists(id);
        // 删除
        illegalActionMapper.deleteById(id);
    }

    private void validateIllegalActionExists(Long id) {
        if (illegalActionMapper.selectById(id) == null) {
            throw exception(ILLEGAL_ACTION_NOT_EXISTS);
        }
    }

    @Override
    public IllegalActionDO getIllegalAction(Long id) {
        return illegalActionMapper.selectById(id);
    }

    @Override
    public List<IllegalActionDO> getIllegalActionList(Collection<Long> ids) {
        return illegalActionMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<IllegalActionDO> getIllegalActionPage(IllegalActionPageReqVO pageReqVO) {
        return illegalActionMapper.selectPage(pageReqVO);
    }

    @Override
    public List<IllegalActionDO> getIllegalActionList(IllegalActionExportReqVO exportReqVO) {
        return illegalActionMapper.selectList(exportReqVO);
    }

}
