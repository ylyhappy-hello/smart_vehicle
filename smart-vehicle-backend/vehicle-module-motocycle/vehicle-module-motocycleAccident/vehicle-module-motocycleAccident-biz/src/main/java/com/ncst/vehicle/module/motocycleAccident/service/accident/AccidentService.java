package com.ncst.vehicle.module.motocycleAccident.service.accident;

import java.util.*;
import javax.validation.*;
import com.ncst.vehicle.module.motocycleAccident.controller.admin.accident.vo.*;
import com.ncst.vehicle.module.motocycleAccident.dal.dataobject.accident.AccidentDO;
import com.ncst.vehicle.framework.common.pojo.PageResult;

/**
 * 意外 Service 接口
 *
 * @author ylyhappy
 */
public interface AccidentService {

    /**
     * 创建意外
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createAccident(@Valid AccidentCreateReqVO createReqVO);

    /**
     * 更新意外
     *
     * @param updateReqVO 更新信息
     */
    void updateAccident(@Valid AccidentUpdateReqVO updateReqVO);

    /**
     * 删除意外
     *
     * @param id 编号
     */
    void deleteAccident(Long id);

    /**
     * 获得意外
     *
     * @param id 编号
     * @return 意外
     */
    AccidentDO getAccident(Long id);

    /**
     * 获得意外列表
     *
     * @param ids 编号
     * @return 意外列表
     */
    List<AccidentDO> getAccidentList(Collection<Long> ids);

    /**
     * 获得意外分页
     *
     * @param pageReqVO 分页查询
     * @return 意外分页
     */
    PageResult<AccidentDO> getAccidentPage(AccidentPageReqVO pageReqVO);

    /**
     * 获得意外列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 意外列表
     */
    List<AccidentDO> getAccidentList(AccidentExportReqVO exportReqVO);

}
