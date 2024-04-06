package com.ncst.vehicle.module.motocycleIllegal.service.illegalwaringtemplate;

import java.util.*;
import javax.validation.*;
import com.ncst.vehicle.module.motocycleIllegal.controller.admin.illegalwaringtemplate.vo.*;
import com.ncst.vehicle.module.motocycleIllegal.dal.dataobject.illegalwaringtemplate.IllegalWaringTemplateDO;
import com.ncst.vehicle.framework.common.pojo.PageResult;

/**
 * 违规警告模板 Service 接口
 *
 * @author ylyhappy
 */
public interface IllegalWaringTemplateService {

    /**
     * 创建违规警告模板
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createIllegalWaringTemplate(@Valid IllegalWaringTemplateCreateReqVO createReqVO);

    /**
     * 更新违规警告模板
     *
     * @param updateReqVO 更新信息
     */
    void updateIllegalWaringTemplate(@Valid IllegalWaringTemplateUpdateReqVO updateReqVO);

    /**
     * 删除违规警告模板
     *
     * @param id 编号
     */
    void deleteIllegalWaringTemplate(Long id);

    /**
     * 获得违规警告模板
     *
     * @param id 编号
     * @return 违规警告模板
     */
    IllegalWaringTemplateDO getIllegalWaringTemplate(Long id);

    /**
     * 获得违规警告模板列表
     *
     * @param ids 编号
     * @return 违规警告模板列表
     */
    List<IllegalWaringTemplateDO> getIllegalWaringTemplateList(Collection<Long> ids);

    /**
     * 获得违规警告模板分页
     *
     * @param pageReqVO 分页查询
     * @return 违规警告模板分页
     */
    PageResult<IllegalWaringTemplateDO> getIllegalWaringTemplatePage(IllegalWaringTemplatePageReqVO pageReqVO);

    /**
     * 获得违规警告模板列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 违规警告模板列表
     */
    List<IllegalWaringTemplateDO> getIllegalWaringTemplateList(IllegalWaringTemplateExportReqVO exportReqVO);

}
