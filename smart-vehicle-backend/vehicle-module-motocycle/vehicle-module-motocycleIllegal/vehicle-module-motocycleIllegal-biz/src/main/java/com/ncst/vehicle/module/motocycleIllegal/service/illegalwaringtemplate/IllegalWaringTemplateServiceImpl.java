package com.ncst.vehicle.module.motocycleIllegal.service.illegalwaringtemplate;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import com.ncst.vehicle.module.motocycleIllegal.controller.admin.illegalwaringtemplate.vo.*;
import com.ncst.vehicle.module.motocycleIllegal.dal.dataobject.illegalwaringtemplate.IllegalWaringTemplateDO;
import com.ncst.vehicle.framework.common.pojo.PageResult;

import com.ncst.vehicle.module.motocycleIllegal.convert.illegalwaringtemplate.IllegalWaringTemplateConvert;
import com.ncst.vehicle.module.motocycleIllegal.dal.mysql.illegalwaringtemplate.IllegalWaringTemplateMapper;

import static com.ncst.vehicle.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.ncst.vehicle.module.motocycleIllegal.enums.ErrorCodeConstants.*;

/**
 * 违规警告模板 Service 实现类
 *
 * @author ylyhappy
 */
@Service
@Validated
public class IllegalWaringTemplateServiceImpl implements IllegalWaringTemplateService {

    @Resource
    private IllegalWaringTemplateMapper illegalWaringTemplateMapper;

    @Override
    public Long createIllegalWaringTemplate(IllegalWaringTemplateCreateReqVO createReqVO) {
        // 插入
        IllegalWaringTemplateDO illegalWaringTemplate = IllegalWaringTemplateConvert.INSTANCE.convert(createReqVO);
        illegalWaringTemplateMapper.insert(illegalWaringTemplate);
        // 返回
        return illegalWaringTemplate.getId();
    }

    @Override
    public void updateIllegalWaringTemplate(IllegalWaringTemplateUpdateReqVO updateReqVO) {
        // 校验存在
        validateIllegalWaringTemplateExists(updateReqVO.getId());
        // 更新
        IllegalWaringTemplateDO updateObj = IllegalWaringTemplateConvert.INSTANCE.convert(updateReqVO);
        illegalWaringTemplateMapper.updateById(updateObj);
    }

    @Override
    public void deleteIllegalWaringTemplate(Long id) {
        // 校验存在
        validateIllegalWaringTemplateExists(id);
        // 删除
        illegalWaringTemplateMapper.deleteById(id);
    }

    private void validateIllegalWaringTemplateExists(Long id) {
        if (illegalWaringTemplateMapper.selectById(id) == null) {
            throw exception(ILLEGAL_WARING_TEMPLATE_NOT_EXISTS);
        }
    }

    @Override
    public IllegalWaringTemplateDO getIllegalWaringTemplate(Long id) {
        return illegalWaringTemplateMapper.selectById(id);
    }

    @Override
    public List<IllegalWaringTemplateDO> getIllegalWaringTemplateList(Collection<Long> ids) {
        return illegalWaringTemplateMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<IllegalWaringTemplateDO> getIllegalWaringTemplatePage(IllegalWaringTemplatePageReqVO pageReqVO) {
        return illegalWaringTemplateMapper.selectPage(pageReqVO);
    }

    @Override
    public List<IllegalWaringTemplateDO> getIllegalWaringTemplateList(IllegalWaringTemplateExportReqVO exportReqVO) {
        return illegalWaringTemplateMapper.selectList(exportReqVO);
    }

}
