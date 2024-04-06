package com.ncst.vehicle.module.motocycleIllegal.service.illegalwaringtype;

import java.util.*;
import javax.validation.*;
import com.ncst.vehicle.module.motocycleIllegal.controller.admin.illegalwaringtype.vo.*;
import com.ncst.vehicle.module.motocycleIllegal.dal.dataobject.illegalwaringtype.IllegalWaringTypeDO;
import com.ncst.vehicle.framework.common.pojo.PageResult;

/**
 * 违规处罚力度 Service 接口
 *
 * @author ylyhappy
 */
public interface IllegalWaringTypeService {

    /**
     * 创建违规处罚力度
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createIllegalWaringType(@Valid IllegalWaringTypeCreateReqVO createReqVO);

    /**
     * 更新违规处罚力度
     *
     * @param updateReqVO 更新信息
     */
    void updateIllegalWaringType(@Valid IllegalWaringTypeUpdateReqVO updateReqVO);

    /**
     * 删除违规处罚力度
     *
     * @param id 编号
     */
    void deleteIllegalWaringType(Long id);

    /**
     * 获得违规处罚力度
     *
     * @param id 编号
     * @return 违规处罚力度
     */
    IllegalWaringTypeDO getIllegalWaringType(Long id);

    /**
     * 获得违规处罚力度列表
     *
     * @param ids 编号
     * @return 违规处罚力度列表
     */
    List<IllegalWaringTypeDO> getIllegalWaringTypeList(Collection<Long> ids);

    /**
     * 获得违规处罚力度分页
     *
     * @param pageReqVO 分页查询
     * @return 违规处罚力度分页
     */
    PageResult<IllegalWaringTypeDO> getIllegalWaringTypePage(IllegalWaringTypePageReqVO pageReqVO);

    /**
     * 获得违规处罚力度列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 违规处罚力度列表
     */
    List<IllegalWaringTypeDO> getIllegalWaringTypeList(IllegalWaringTypeExportReqVO exportReqVO);

}
