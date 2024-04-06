package com.ncst.vehicle.module.motocycleBike.service.bikeentity;

import java.util.*;
import javax.validation.*;
import com.ncst.vehicle.module.motocycleBike.controller.admin.bikeentity.vo.*;
import com.ncst.vehicle.module.motocycleBike.dal.dataobject.bikeentity.BikeEntityDO;
import com.ncst.vehicle.framework.common.pojo.PageResult;

/**
 * 电车实体 Service 接口
 *
 * @author ylyhappy
 */
public interface BikeEntityService {

    /**
     * 创建电车实体
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createBikeEntity(@Valid BikeEntityCreateReqVO createReqVO);

    /**
     * 更新电车实体
     *
     * @param updateReqVO 更新信息
     */
    void updateBikeEntity(@Valid BikeEntityUpdateReqVO updateReqVO);

    /**
     * 删除电车实体
     *
     * @param id 编号
     */
    void deleteBikeEntity(Long id);

    /**
     * 获得电车实体
     *
     * @param id 编号
     * @return 电车实体
     */
    BikeEntityDO getBikeEntity(Long id);

    /**
     * 获得电车实体列表
     *
     * @param ids 编号
     * @return 电车实体列表
     */
    List<BikeEntityDO> getBikeEntityList(Collection<Long> ids);

    /**
     * 获得电车实体分页
     *
     * @param pageReqVO 分页查询
     * @return 电车实体分页
     */
    PageResult<BikeEntityDO> getBikeEntityPage(BikeEntityPageReqVO pageReqVO);

    /**
     * 获得电车实体列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 电车实体列表
     */
    List<BikeEntityDO> getBikeEntityList(BikeEntityExportReqVO exportReqVO);

}
