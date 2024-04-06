package com.ncst.vehicle.module.motocycleDetect.service.detectfilesanalysis;

import java.util.*;
import javax.validation.*;
import com.ncst.vehicle.module.motocycleDetect.controller.admin.detectfilesanalysis.vo.*;
import com.ncst.vehicle.module.motocycleDetect.dal.dataobject.detectfilesanalysis.DetectFilesAnalysisDO;
import com.ncst.vehicle.framework.common.pojo.PageResult;

/**
 * 检测文件结果分析 Service 接口
 *
 * @author ylyhappy
 */
public interface DetectFilesAnalysisService {

    /**
     * 创建检测文件结果分析
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createDetectFilesAnalysis(@Valid DetectFilesAnalysisCreateReqVO createReqVO);

    /**
     * 更新检测文件结果分析
     *
     * @param updateReqVO 更新信息
     */
    void updateDetectFilesAnalysis(@Valid DetectFilesAnalysisUpdateReqVO updateReqVO);

    /**
     * 删除检测文件结果分析
     *
     * @param id 编号
     */
    void deleteDetectFilesAnalysis(Long id);

    /**
     * 获得检测文件结果分析
     *
     * @param id 编号
     * @return 检测文件结果分析
     */
    DetectFilesAnalysisDO getDetectFilesAnalysis(Long id);

    /**
     * 获得检测文件结果分析列表
     *
     * @param ids 编号
     * @return 检测文件结果分析列表
     */
    List<DetectFilesAnalysisDO> getDetectFilesAnalysisList(Collection<Long> ids);

    /**
     * 获得检测文件结果分析分页
     *
     * @param pageReqVO 分页查询
     * @return 检测文件结果分析分页
     */
    PageResult<DetectFilesAnalysisDO> getDetectFilesAnalysisPage(DetectFilesAnalysisPageReqVO pageReqVO);

    /**
     * 获得检测文件结果分析列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 检测文件结果分析列表
     */
    List<DetectFilesAnalysisDO> getDetectFilesAnalysisList(DetectFilesAnalysisExportReqVO exportReqVO);

}
