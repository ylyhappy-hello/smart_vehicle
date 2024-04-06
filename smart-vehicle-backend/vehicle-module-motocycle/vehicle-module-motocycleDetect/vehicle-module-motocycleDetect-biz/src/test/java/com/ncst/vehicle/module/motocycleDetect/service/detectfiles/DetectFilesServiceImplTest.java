package com.ncst.vehicle.module.motocycleDetect.service.detectfiles;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.annotation.Resource;

import com.ncst.vehicle.framework.test.core.ut.BaseDbUnitTest;

import com.ncst.vehicle.module.motocycleDetect.controller.admin.detectfiles.vo.*;
import com.ncst.vehicle.module.motocycleDetect.dal.dataobject.detectfiles.DetectFilesDO;
import com.ncst.vehicle.module.motocycleDetect.dal.mysql.detectfiles.DetectFilesMapper;
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
 * {@link DetectFilesServiceImpl} 的单元测试类
 *
 * @author ylyhappy
 */
@Import(DetectFilesServiceImpl.class)
public class DetectFilesServiceImplTest extends BaseDbUnitTest {

    @Resource
    private DetectFilesServiceImpl detectFilesService;

    @Resource
    private DetectFilesMapper detectFilesMapper;

    @Test
    public void testCreateDetectFiles_success() {
        // 准备参数
        DetectFilesCreateReqVO reqVO = randomPojo(DetectFilesCreateReqVO.class);

        // 调用
        Long detectFilesId = detectFilesService.createDetectFiles(reqVO);
        // 断言
        assertNotNull(detectFilesId);
        // 校验记录的属性是否正确
        DetectFilesDO detectFiles = detectFilesMapper.selectById(detectFilesId);
        assertPojoEquals(reqVO, detectFiles);
    }

    @Test
    public void testUpdateDetectFiles_success() {
        // mock 数据
        DetectFilesDO dbDetectFiles = randomPojo(DetectFilesDO.class);
        detectFilesMapper.insert(dbDetectFiles);// @Sql: 先插入出一条存在的数据
        // 准备参数
        DetectFilesUpdateReqVO reqVO = randomPojo(DetectFilesUpdateReqVO.class, o -> {
            o.setId(dbDetectFiles.getId()); // 设置更新的 ID
        });

        // 调用
        detectFilesService.updateDetectFiles(reqVO);
        // 校验是否更新正确
        DetectFilesDO detectFiles = detectFilesMapper.selectById(reqVO.getId()); // 获取最新的
        assertPojoEquals(reqVO, detectFiles);
    }

    @Test
    public void testUpdateDetectFiles_notExists() {
        // 准备参数
        DetectFilesUpdateReqVO reqVO = randomPojo(DetectFilesUpdateReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> detectFilesService.updateDetectFiles(reqVO), DETECT_FILES_NOT_EXISTS);
    }

    @Test
    public void testDeleteDetectFiles_success() {
        // mock 数据
        DetectFilesDO dbDetectFiles = randomPojo(DetectFilesDO.class);
        detectFilesMapper.insert(dbDetectFiles);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = dbDetectFiles.getId();

        // 调用
        detectFilesService.deleteDetectFiles(id);
       // 校验数据不存在了
       assertNull(detectFilesMapper.selectById(id));
    }

    @Test
    public void testDeleteDetectFiles_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> detectFilesService.deleteDetectFiles(id), DETECT_FILES_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetDetectFilesPage() {
       // mock 数据
       DetectFilesDO dbDetectFiles = randomPojo(DetectFilesDO.class, o -> { // 等会查询到
           o.setName(null);
           o.setPath(null);
           o.setUrl(null);
           o.setType(null);
           o.setSize(null);
           o.setCreateTime(null);
       });
       detectFilesMapper.insert(dbDetectFiles);
       // 测试 name 不匹配
       detectFilesMapper.insert(cloneIgnoreId(dbDetectFiles, o -> o.setName(null)));
       // 测试 path 不匹配
       detectFilesMapper.insert(cloneIgnoreId(dbDetectFiles, o -> o.setPath(null)));
       // 测试 url 不匹配
       detectFilesMapper.insert(cloneIgnoreId(dbDetectFiles, o -> o.setUrl(null)));
       // 测试 type 不匹配
       detectFilesMapper.insert(cloneIgnoreId(dbDetectFiles, o -> o.setType(null)));
       // 测试 size 不匹配
       detectFilesMapper.insert(cloneIgnoreId(dbDetectFiles, o -> o.setSize(null)));
       // 测试 createTime 不匹配
       detectFilesMapper.insert(cloneIgnoreId(dbDetectFiles, o -> o.setCreateTime(null)));
       // 准备参数
       DetectFilesPageReqVO reqVO = new DetectFilesPageReqVO();
       reqVO.setConfigId(null);
       reqVO.setName(null);
       reqVO.setPath(null);
       reqVO.setUrl(null);
       reqVO.setType(null);
       reqVO.setSize(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       PageResult<DetectFilesDO> pageResult = detectFilesService.getDetectFilesPage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbDetectFiles, pageResult.getList().get(0));
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetDetectFilesList() {
       // mock 数据
       DetectFilesDO dbDetectFiles = randomPojo(DetectFilesDO.class, o -> { // 等会查询到
           o.setName(null);
           o.setPath(null);
           o.setUrl(null);
           o.setType(null);
           o.setSize(null);
           o.setCreateTime(null);
       });
       detectFilesMapper.insert(dbDetectFiles);
       // 测试 name 不匹配
       detectFilesMapper.insert(cloneIgnoreId(dbDetectFiles, o -> o.setName(null)));
       // 测试 path 不匹配
       detectFilesMapper.insert(cloneIgnoreId(dbDetectFiles, o -> o.setPath(null)));
       // 测试 url 不匹配
       detectFilesMapper.insert(cloneIgnoreId(dbDetectFiles, o -> o.setUrl(null)));
       // 测试 type 不匹配
       detectFilesMapper.insert(cloneIgnoreId(dbDetectFiles, o -> o.setType(null)));
       // 测试 size 不匹配
       detectFilesMapper.insert(cloneIgnoreId(dbDetectFiles, o -> o.setSize(null)));
       // 测试 createTime 不匹配
       detectFilesMapper.insert(cloneIgnoreId(dbDetectFiles, o -> o.setCreateTime(null)));
       // 准备参数
       DetectFilesExportReqVO reqVO = new DetectFilesExportReqVO();
       reqVO.setConfigId(null);
       reqVO.setName(null);
       reqVO.setPath(null);
       reqVO.setUrl(null);
       reqVO.setType(null);
       reqVO.setSize(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       List<DetectFilesDO> list = detectFilesService.getDetectFilesList(reqVO);
       // 断言
       assertEquals(1, list.size());
       assertPojoEquals(dbDetectFiles, list.get(0));
    }

}
