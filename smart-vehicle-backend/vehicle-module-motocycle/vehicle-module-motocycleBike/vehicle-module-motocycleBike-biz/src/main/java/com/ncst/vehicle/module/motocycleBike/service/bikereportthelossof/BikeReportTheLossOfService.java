package com.ncst.vehicle.module.motocycleBike.service.bikereportthelossof;

import java.util.*;
import javax.validation.*;
import com.ncst.vehicle.module.motocycleBike.controller.admin.bikereportthelossof.vo.*;
import com.ncst.vehicle.module.motocycleBike.dal.dataobject.bikereportthelossof.BikeReportTheLossOfDO;
import com.ncst.vehicle.framework.common.pojo.PageResult;

/**
 * 电车挂失信息 Service 接口
 *
 * @author ylyhappy
 */
public interface BikeReportTheLossOfService {

    /**
     * 创建电车挂失信息
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createBikeReportTheLossOf(@Valid BikeReportTheLossOfCreateReqVO createReqVO);

    /**
     * 更新电车挂失信息
     *
     * @param updateReqVO 更新信息
     */
    void updateBikeReportTheLossOf(@Valid BikeReportTheLossOfUpdateReqVO updateReqVO);

    /**
     * 删除电车挂失信息
     *
     * @param id 编号
     */
    void deleteBikeReportTheLossOf(Long id);

    /**
     * 获得电车挂失信息
     *
     * @param id 编号
     * @return 电车挂失信息
     */
    BikeReportTheLossOfDO getBikeReportTheLossOf(Long id);

    /**
     * 获得电车挂失信息列表
     *
     * @param ids 编号
     * @return 电车挂失信息列表
     */
    List<BikeReportTheLossOfDO> getBikeReportTheLossOfList(Collection<Long> ids);

    /**
     * 获得电车挂失信息分页
     *
     * @param pageReqVO 分页查询
     * @return 电车挂失信息分页
     */
    PageResult<BikeReportTheLossOfDO> getBikeReportTheLossOfPage(BikeReportTheLossOfPageReqVO pageReqVO);

    /**
     * 获得电车挂失信息列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 电车挂失信息列表
     */
    List<BikeReportTheLossOfDO> getBikeReportTheLossOfList(BikeReportTheLossOfExportReqVO exportReqVO);

}
