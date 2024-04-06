package com.ncst.vehicle.module.motocycleIllegal.service.illegalaction;

import java.util.*;
import javax.validation.*;
import com.ncst.vehicle.module.motocycleIllegal.controller.admin.illegalaction.vo.*;
import com.ncst.vehicle.module.motocycleIllegal.dal.dataobject.illegalaction.IllegalActionDO;
import com.ncst.vehicle.framework.common.pojo.PageResult;

/**
 * 违规行为 Service 接口
 *
 * @author ylyhappy
 */
public interface IllegalActionService {

    /**
     * 创建违规行为
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createIllegalAction(@Valid IllegalActionCreateReqVO createReqVO);

    /**
     * 更新违规行为
     *
     * @param updateReqVO 更新信息
     */
    void updateIllegalAction(@Valid IllegalActionUpdateReqVO updateReqVO);

    /**
     * 删除违规行为
     *
     * @param id 编号
     */
    void deleteIllegalAction(Long id);

    /**
     * 获得违规行为
     *
     * @param id 编号
     * @return 违规行为
     */
    IllegalActionDO getIllegalAction(Long id);

    /**
     * 获得违规行为列表
     *
     * @param ids 编号
     * @return 违规行为列表
     */
    List<IllegalActionDO> getIllegalActionList(Collection<Long> ids);

    /**
     * 获得违规行为分页
     *
     * @param pageReqVO 分页查询
     * @return 违规行为分页
     */
    PageResult<IllegalActionDO> getIllegalActionPage(IllegalActionPageReqVO pageReqVO);

    /**
     * 获得违规行为列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 违规行为列表
     */
    List<IllegalActionDO> getIllegalActionList(IllegalActionExportReqVO exportReqVO);

}
