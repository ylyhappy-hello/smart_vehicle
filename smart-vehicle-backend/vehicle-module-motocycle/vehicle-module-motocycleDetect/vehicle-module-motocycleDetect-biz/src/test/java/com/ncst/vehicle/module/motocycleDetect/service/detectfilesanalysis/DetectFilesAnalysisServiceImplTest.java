package com.ncst.vehicle.module.motocycleDetect.service.detectfilesanalysis;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.annotation.Resource;

import com.ncst.vehicle.framework.test.core.ut.BaseDbUnitTest;

import com.ncst.vehicle.module.motocycleDetect.controller.admin.detectfilesanalysis.vo.*;
import com.ncst.vehicle.module.motocycleDetect.dal.dataobject.detectfilesanalysis.DetectFilesAnalysisDO;
import com.ncst.vehicle.module.motocycleDetect.dal.mysql.detectfilesanalysis.DetectFilesAnalysisMapper;
import com.ncst.vehicle.framework.common.pojo.PageResult;

import javax.annotation.Resource;
import org.springframework.context.annotation.Import;
import java.util.*;
import java.time.LocalDateTime;

import static cn.hutool.core.util.RandomUtil.*;
import static com.ncst.vehicle.module.motocycleDetect.enums.ErrorCodeConstants.*;
import static com.ncst.vehicle.framework.test.core.util.AssertUtils.*;
import static com.ncst.vehicle.framework.test.core.util.RandomUtils.*;
import static com.ncst.vehicle.framework.common.util.date.LocalDateTimeUtils.*;
import static com.ncst.vehicle.framework.common.util.object.ObjectUtils.*;
import static com.ncst.vehicle.framework.common.util.date.DateUtils.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * {@link DetectFilesAnalysisServiceImpl} 的单元测试类
 *
 * @author ylyhappy
 */
@Import(DetectFilesAnalysisServiceImpl.class)
public class DetectFilesAnalysisServiceImplTest extends BaseDbUnitTest {

    @Resource
    private DetectFilesAnalysisServiceImpl detectFilesAnalysisService;

    @Resource
    private DetectFilesAnalysisMapper detectFilesAnalysisMapper;

    @Test
    public void testCreateDetectFilesAnalysis_success() {
        // 准备参数
        DetectFilesAnalysisCreateReqVO reqVO = randomPojo(DetectFilesAnalysisCreateReqVO.class);

        // 调用
        Long detectFilesAnalysisId = detectFilesAnalysisService.createDetectFilesAnalysis(reqVO);
        // 断言
        assertNotNull(detectFilesAnalysisId);
        // 校验记录的属性是否正确
        DetectFilesAnalysisDO detectFilesAnalysis = detectFilesAnalysisMapper.selectById(detectFilesAnalysisId);
        assertPojoEquals(reqVO, detectFilesAnalysis);
    }

    @Test
    public void testUpdateDetectFilesAnalysis_success() {
        // mock 数据
        DetectFilesAnalysisDO dbDetectFilesAnalysis = randomPojo(DetectFilesAnalysisDO.class);
        detectFilesAnalysisMapper.insert(dbDetectFilesAnalysis);// @Sql: 先插入出一条存在的数据
        // 准备参数
        DetectFilesAnalysisUpdateReqVO reqVO = randomPojo(DetectFilesAnalysisUpdateReqVO.class, o -> {
            o.setId(dbDetectFilesAnalysis.getId()); // 设置更新的 ID
        });

        // 调用
        detectFilesAnalysisService.updateDetectFilesAnalysis(reqVO);
        // 校验是否更新正确
        DetectFilesAnalysisDO detectFilesAnalysis = detectFilesAnalysisMapper.selectById(reqVO.getId()); // 获取最新的
        assertPojoEquals(reqVO, detectFilesAnalysis);
    }

    @Test
    public void testUpdateDetectFilesAnalysis_notExists() {
        // 准备参数
        DetectFilesAnalysisUpdateReqVO reqVO = randomPojo(DetectFilesAnalysisUpdateReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> detectFilesAnalysisService.updateDetectFilesAnalysis(reqVO), DETECT_FILES_ANALYSIS_NOT_EXISTS);
    }

    @Test
    public void testDeleteDetectFilesAnalysis_success() {
        // mock 数据
        DetectFilesAnalysisDO dbDetectFilesAnalysis = randomPojo(DetectFilesAnalysisDO.class);
        detectFilesAnalysisMapper.insert(dbDetectFilesAnalysis);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = dbDetectFilesAnalysis.getId();

        // 调用
        detectFilesAnalysisService.deleteDetectFilesAnalysis(id);
       // 校验数据不存在了
       assertNull(detectFilesAnalysisMapper.selectById(id));
    }

    @Test
    public void testDeleteDetectFilesAnalysis_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> detectFilesAnalysisService.deleteDetectFilesAnalysis(id), DETECT_FILES_ANALYSIS_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetDetectFilesAnalysisPage() {
       // mock 数据
       DetectFilesAnalysisDO dbDetectFilesAnalysis = randomPojo(DetectFilesAnalysisDO.class, o -> { // 等会查询到
           o.setFileId(null);
           o.setType(null);
           o.setResult(null);
           o.setIllegal(null);
           o.setCreateTime(null);
       });
       detectFilesAnalysisMapper.insert(dbDetectFilesAnalysis);
       // 测试 fileId 不匹配
       detectFilesAnalysisMapper.insert(cloneIgnoreId(dbDetectFilesAnalysis, o -> o.setFileId(null)));
       // 测试 type 不匹配
       detectFilesAnalysisMapper.insert(cloneIgnoreId(dbDetectFilesAnalysis, o -> o.setType(null)));
       // 测试 result 不匹配
       detectFilesAnalysisMapper.insert(cloneIgnoreId(dbDetectFilesAnalysis, o -> o.setResult(null)));
       // 测试 illegal 不匹配
       detectFilesAnalysisMapper.insert(cloneIgnoreId(dbDetectFilesAnalysis, o -> o.setIllegal(null)));
       // 测试 createTime 不匹配
       detectFilesAnalysisMapper.insert(cloneIgnoreId(dbDetectFilesAnalysis, o -> o.setCreateTime(null)));
       // 准备参数
       DetectFilesAnalysisPageReqVO reqVO = new DetectFilesAnalysisPageReqVO();
       reqVO.setFileId(null);
       reqVO.setType(null);
       reqVO.setResult(null);
       reqVO.setIllegal(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       PageResult<DetectFilesAnalysisDO> pageResult = detectFilesAnalysisService.getDetectFilesAnalysisPage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbDetectFilesAnalysis, pageResult.getList().get(0));
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetDetectFilesAnalysisList() {
       // mock 数据
       DetectFilesAnalysisDO dbDetectFilesAnalysis = randomPojo(DetectFilesAnalysisDO.class, o -> { // 等会查询到
           o.setFileId(null);
           o.setType(null);
           o.setResult(null);
           o.setIllegal(null);
           o.setCreateTime(null);
       });
       detectFilesAnalysisMapper.insert(dbDetectFilesAnalysis);
       // 测试 fileId 不匹配
       detectFilesAnalysisMapper.insert(cloneIgnoreId(dbDetectFilesAnalysis, o -> o.setFileId(null)));
       // 测试 type 不匹配
       detectFilesAnalysisMapper.insert(cloneIgnoreId(dbDetectFilesAnalysis, o -> o.setType(null)));
       // 测试 result 不匹配
       detectFilesAnalysisMapper.insert(cloneIgnoreId(dbDetectFilesAnalysis, o -> o.setResult(null)));
       // 测试 illegal 不匹配
       detectFilesAnalysisMapper.insert(cloneIgnoreId(dbDetectFilesAnalysis, o -> o.setIllegal(null)));
       // 测试 createTime 不匹配
       detectFilesAnalysisMapper.insert(cloneIgnoreId(dbDetectFilesAnalysis, o -> o.setCreateTime(null)));
       // 准备参数
       DetectFilesAnalysisExportReqVO reqVO = new DetectFilesAnalysisExportReqVO();
       reqVO.setFileId(null);
       reqVO.setType(null);
       reqVO.setResult(null);
       reqVO.setIllegal(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       List<DetectFilesAnalysisDO> list = detectFilesAnalysisService.getDetectFilesAnalysisList(reqVO);
       // 断言
       assertEquals(1, list.size());
       assertPojoEquals(dbDetectFilesAnalysis, list.get(0));
    }

}
