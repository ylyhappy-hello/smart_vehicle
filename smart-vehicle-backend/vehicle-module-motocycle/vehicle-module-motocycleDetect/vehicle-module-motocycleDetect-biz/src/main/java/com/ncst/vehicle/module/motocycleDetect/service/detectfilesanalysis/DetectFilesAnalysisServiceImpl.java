package com.ncst.vehicle.module.motocycleDetect.service.detectfilesanalysis;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import com.ncst.vehicle.module.motocycleDetect.controller.admin.detectfilesanalysis.vo.*;
import com.ncst.vehicle.module.motocycleDetect.dal.dataobject.detectfilesanalysis.DetectFilesAnalysisDO;
import com.ncst.vehicle.framework.common.pojo.PageResult;

import com.ncst.vehicle.module.motocycleDetect.convert.detectfilesanalysis.DetectFilesAnalysisConvert;
import com.ncst.vehicle.module.motocycleDetect.dal.mysql.detectfilesanalysis.DetectFilesAnalysisMapper;

import static com.ncst.vehicle.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.ncst.vehicle.module.motocycleDetect.enums.ErrorCodeConstants.*;

/**
 * 检测文件结果分析 Service 实现类
 *
 * @author ylyhappy
 */
@Service
@Validated
public class DetectFilesAnalysisServiceImpl implements DetectFilesAnalysisService {

    @Resource
    private DetectFilesAnalysisMapper detectFilesAnalysisMapper;

    @Override
    public Long createDetectFilesAnalysis(DetectFilesAnalysisCreateReqVO createReqVO) {
        // 插入
        DetectFilesAnalysisDO detectFilesAnalysis = DetectFilesAnalysisConvert.INSTANCE.convert(createReqVO);
        detectFilesAnalysisMapper.insert(detectFilesAnalysis);
        // 返回
        return detectFilesAnalysis.getId();
    }

    @Override
    public void updateDetectFilesAnalysis(DetectFilesAnalysisUpdateReqVO updateReqVO) {
        // 校验存在
        validateDetectFilesAnalysisExists(updateReqVO.getId());
        // 更新
        DetectFilesAnalysisDO updateObj = DetectFilesAnalysisConvert.INSTANCE.convert(updateReqVO);
        detectFilesAnalysisMapper.updateById(updateObj);
    }

    @Override
    public void deleteDetectFilesAnalysis(Long id) {
        // 校验存在
        validateDetectFilesAnalysisExists(id);
        // 删除
        detectFilesAnalysisMapper.deleteById(id);
    }

    private void validateDetectFilesAnalysisExists(Long id) {
        if (detectFilesAnalysisMapper.selectById(id) == null) {
            throw exception(DETECT_FILES_ANALYSIS_NOT_EXISTS);
        }
    }

    @Override
    public DetectFilesAnalysisDO getDetectFilesAnalysis(Long id) {
        return detectFilesAnalysisMapper.selectById(id);
    }

    @Override
    public List<DetectFilesAnalysisDO> getDetectFilesAnalysisList(Collection<Long> ids) {
        return detectFilesAnalysisMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<DetectFilesAnalysisDO> getDetectFilesAnalysisPage(DetectFilesAnalysisPageReqVO pageReqVO) {
        return detectFilesAnalysisMapper.selectPage(pageReqVO);
    }

    @Override
    public List<DetectFilesAnalysisDO> getDetectFilesAnalysisList(DetectFilesAnalysisExportReqVO exportReqVO) {
        return detectFilesAnalysisMapper.selectList(exportReqVO);
    }

}
