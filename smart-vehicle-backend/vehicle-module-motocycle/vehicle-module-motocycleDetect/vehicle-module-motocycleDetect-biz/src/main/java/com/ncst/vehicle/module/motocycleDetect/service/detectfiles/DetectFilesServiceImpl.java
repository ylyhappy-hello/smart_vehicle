package com.ncst.vehicle.module.motocycleDetect.service.detectfiles;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import com.ncst.vehicle.module.motocycleDetect.controller.admin.detectfiles.vo.*;
import com.ncst.vehicle.module.motocycleDetect.dal.dataobject.detectfiles.DetectFilesDO;
import com.ncst.vehicle.framework.common.pojo.PageResult;

import com.ncst.vehicle.module.motocycleDetect.convert.detectfiles.DetectFilesConvert;
import com.ncst.vehicle.module.motocycleDetect.dal.mysql.detectfiles.DetectFilesMapper;

import static com.ncst.vehicle.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.ncst.vehicle.module.motocycleDetect.enums.ErrorCodeConstants.*;

/**
 * 文件 Service 实现类
 *
 * @author ylyhappy
 */
@Service
@Validated
public class DetectFilesServiceImpl implements DetectFilesService {

    @Resource
    private DetectFilesMapper detectFilesMapper;

    @Override
    public Long createDetectFiles(DetectFilesCreateReqVO createReqVO) {
        // 插入
        DetectFilesDO detectFiles = DetectFilesConvert.INSTANCE.convert(createReqVO);
        detectFilesMapper.insert(detectFiles);
        // 返回
        return detectFiles.getId();
    }

    @Override
    public void updateDetectFiles(DetectFilesUpdateReqVO updateReqVO) {
        // 校验存在
        validateDetectFilesExists(updateReqVO.getId());
        // 更新
        DetectFilesDO updateObj = DetectFilesConvert.INSTANCE.convert(updateReqVO);
        detectFilesMapper.updateById(updateObj);
    }

    @Override
    public void deleteDetectFiles(Long id) {
        // 校验存在
        validateDetectFilesExists(id);
        // 删除
        detectFilesMapper.deleteById(id);
    }

    private void validateDetectFilesExists(Long id) {
        if (detectFilesMapper.selectById(id) == null) {
            throw exception(DETECT_FILES_NOT_EXISTS);
        }
    }

    @Override
    public DetectFilesDO getDetectFiles(Long id) {
        return detectFilesMapper.selectById(id);
    }

    @Override
    public List<DetectFilesDO> getDetectFilesList(Collection<Long> ids) {
        return detectFilesMapper.selectBatchIds(ids);
    }

    @Override
    public List<DetectFilesDO> getAllDetectFilesList() {
        return detectFilesMapper.selectAll();
    }

    @Override
    public PageResult<DetectFilesDO> getDetectFilesPage(DetectFilesPageReqVO pageReqVO) {
        return detectFilesMapper.selectPage(pageReqVO);
    }

    @Override
    public List<DetectFilesDO> getDetectAnalysisFilesList() {
        return detectFilesMapper.analysisList();
    }


    @Override
    public List<DetectFilesDO> getDetectFilesList(DetectFilesExportReqVO exportReqVO) {
        return detectFilesMapper.selectList(exportReqVO);
    }

}
