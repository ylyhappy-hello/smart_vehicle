package com.ncst.vehicle.module.motocycleDetect.service.detectfiles;

import java.util.*;
import javax.validation.*;
import com.ncst.vehicle.module.motocycleDetect.controller.admin.detectfiles.vo.*;
import com.ncst.vehicle.module.motocycleDetect.dal.dataobject.detectfiles.DetectFilesDO;
import com.ncst.vehicle.framework.common.pojo.PageResult;

/**
 * 文件 Service 接口
 *
 * @author ylyhappy
 */
public interface DetectFilesService {

    /**
     * 创建文件
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createDetectFiles(@Valid DetectFilesCreateReqVO createReqVO);

    /**
     * 更新文件
     *
     * @param updateReqVO 更新信息
     */
    void updateDetectFiles(@Valid DetectFilesUpdateReqVO updateReqVO);

    /**
     * 删除文件
     *
     * @param id 编号
     */
    void deleteDetectFiles(Long id);

    /**
     * 获得文件
     *
     * @param id 编号
     * @return 文件
     */
    DetectFilesDO getDetectFiles(Long id);

    /**
     * 获得文件分析列表
     *
     * @return 文件分析列表
     */
    List<DetectFilesDO> getDetectAnalysisFilesList();

    /**
     * 获得文件列表
     *
     * @param ids 编号
     * @return 文件列表
     */
    List<DetectFilesDO> getDetectFilesList(Collection<Long> ids);

    /**
     * 获得所有文件
     *
     * @return 文件列表
     */
    List<DetectFilesDO> getAllDetectFilesList();


    /**
     * 获得文件分页
     *
     * @param pageReqVO 分页查询
     * @return 文件分页
     */
    PageResult<DetectFilesDO> getDetectFilesPage(DetectFilesPageReqVO pageReqVO);

    /**
     * 获得文件列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 文件列表
     */
    List<DetectFilesDO> getDetectFilesList(DetectFilesExportReqVO exportReqVO);

}
