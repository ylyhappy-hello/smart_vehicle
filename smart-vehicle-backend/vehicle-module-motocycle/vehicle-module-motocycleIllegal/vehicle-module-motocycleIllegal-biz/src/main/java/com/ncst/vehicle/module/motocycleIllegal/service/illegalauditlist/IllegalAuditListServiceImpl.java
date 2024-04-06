package com.ncst.vehicle.module.motocycleIllegal.service.illegalauditlist;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import com.ncst.vehicle.module.motocycleIllegal.controller.admin.illegalauditlist.vo.*;
import com.ncst.vehicle.module.motocycleIllegal.dal.dataobject.illegalauditlist.IllegalAuditListDO;
import com.ncst.vehicle.framework.common.pojo.PageResult;

import com.ncst.vehicle.module.motocycleIllegal.convert.illegalauditlist.IllegalAuditListConvert;
import com.ncst.vehicle.module.motocycleIllegal.dal.mysql.illegalauditlist.IllegalAuditListMapper;

import static com.ncst.vehicle.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.ncst.vehicle.module.motocycleIllegal.enums.ErrorCodeConstants.*;

/**
 * 违规审核 Service 实现类
 *
 * @author ylyhappy
 */
@Service
@Validated
public class IllegalAuditListServiceImpl implements IllegalAuditListService {

    @Resource
    private IllegalAuditListMapper illegalAuditListMapper;

    @Override
    public Long createIllegalAuditList(IllegalAuditListCreateReqVO createReqVO) {
        // 插入
        IllegalAuditListDO illegalAuditList = IllegalAuditListConvert.INSTANCE.convert(createReqVO);
        illegalAuditListMapper.insert(illegalAuditList);
        // 返回
        return illegalAuditList.getId();
    }

    @Override
    public void updateIllegalAuditList(IllegalAuditListUpdateReqVO updateReqVO) {
        // 校验存在
        validateIllegalAuditListExists(updateReqVO.getId());
        // 更新
        IllegalAuditListDO updateObj = IllegalAuditListConvert.INSTANCE.convert(updateReqVO);
        illegalAuditListMapper.updateById(updateObj);
    }

    @Override
    public void deleteIllegalAuditList(Long id) {
        // 校验存在
        validateIllegalAuditListExists(id);
        // 删除
        illegalAuditListMapper.deleteById(id);
    }

    private void validateIllegalAuditListExists(Long id) {
        if (illegalAuditListMapper.selectById(id) == null) {
            throw exception(ILLEGAL_AUDIT_LIST_NOT_EXISTS);
        }
    }

    @Override
    public IllegalAuditListDO getIllegalAuditList(Long id) {
        return illegalAuditListMapper.selectById(id);
    }

    @Override
    public List<IllegalAuditListDO> getIllegalAuditListList(Collection<Long> ids) {
        return illegalAuditListMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<IllegalAuditListDO> getIllegalAuditListPage(IllegalAuditListPageReqVO pageReqVO) {
        return illegalAuditListMapper.selectPage(pageReqVO);
    }

    @Override
    public List<IllegalAuditListDO> getIllegalAuditListList(IllegalAuditListExportReqVO exportReqVO) {
        return illegalAuditListMapper.selectList(exportReqVO);
    }

}
