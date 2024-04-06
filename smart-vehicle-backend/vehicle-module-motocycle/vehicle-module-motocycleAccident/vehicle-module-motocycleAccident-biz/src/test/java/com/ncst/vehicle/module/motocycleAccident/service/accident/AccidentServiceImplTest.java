package com.ncst.vehicle.module.motocycleAccident.service.accident;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.annotation.Resource;

import com.ncst.vehicle.framework.test.core.ut.BaseDbUnitTest;

import com.ncst.vehicle.module.motocycleAccident.controller.admin.accident.vo.*;
import com.ncst.vehicle.module.motocycleAccident.dal.dataobject.accident.AccidentDO;
import com.ncst.vehicle.module.motocycleAccident.dal.mysql.accident.AccidentMapper;
import com.ncst.vehicle.framework.common.pojo.PageResult;

import javax.annotation.Resource;
import org.springframework.context.annotation.Import;
import java.util.*;
import java.time.LocalDateTime;

import static cn.hutool.core.util.RandomUtil.*;
import static com.ncst.vehicle.module.motocycleAccident.enums.ErrorCodeConstants.*;
import static com.ncst.vehicle.framework.test.core.util.AssertUtils.*;
import static com.ncst.vehicle.framework.test.core.util.RandomUtils.*;
import static com.ncst.vehicle.framework.common.util.date.LocalDateTimeUtils.*;
import static com.ncst.vehicle.framework.common.util.object.ObjectUtils.*;
import static com.ncst.vehicle.framework.common.util.date.DateUtils.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * {@link AccidentServiceImpl} 的单元测试类
 *
 * @author ylyhappy
 */
@Import(AccidentServiceImpl.class)
public class AccidentServiceImplTest extends BaseDbUnitTest {

    @Resource
    private AccidentServiceImpl accidentService;

    @Resource
    private AccidentMapper accidentMapper;

    @Test
    public void testCreateAccident_success() {
        // 准备参数
        AccidentCreateReqVO reqVO = randomPojo(AccidentCreateReqVO.class);

        // 调用
        Long accidentId = accidentService.createAccident(reqVO);
        // 断言
        assertNotNull(accidentId);
        // 校验记录的属性是否正确
        AccidentDO accident = accidentMapper.selectById(accidentId);
        assertPojoEquals(reqVO, accident);
    }

    @Test
    public void testUpdateAccident_success() {
        // mock 数据
        AccidentDO dbAccident = randomPojo(AccidentDO.class);
        accidentMapper.insert(dbAccident);// @Sql: 先插入出一条存在的数据
        // 准备参数
        AccidentUpdateReqVO reqVO = randomPojo(AccidentUpdateReqVO.class, o -> {
            o.setId(dbAccident.getId()); // 设置更新的 ID
        });

        // 调用
        accidentService.updateAccident(reqVO);
        // 校验是否更新正确
        AccidentDO accident = accidentMapper.selectById(reqVO.getId()); // 获取最新的
        assertPojoEquals(reqVO, accident);
    }

    @Test
    public void testUpdateAccident_notExists() {
        // 准备参数
        AccidentUpdateReqVO reqVO = randomPojo(AccidentUpdateReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> accidentService.updateAccident(reqVO), ACCIDENT_NOT_EXISTS);
    }

    @Test
    public void testDeleteAccident_success() {
        // mock 数据
        AccidentDO dbAccident = randomPojo(AccidentDO.class);
        accidentMapper.insert(dbAccident);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = dbAccident.getId();

        // 调用
        accidentService.deleteAccident(id);
       // 校验数据不存在了
       assertNull(accidentMapper.selectById(id));
    }

    @Test
    public void testDeleteAccident_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> accidentService.deleteAccident(id), ACCIDENT_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetAccidentPage() {
       // mock 数据
       AccidentDO dbAccident = randomPojo(AccidentDO.class, o -> { // 等会查询到
           o.setPosition(null);
           o.setReason(null);
           o.setDetail(null);
           o.setCreateTime(null);
       });
       accidentMapper.insert(dbAccident);
       // 测试 position 不匹配
       accidentMapper.insert(cloneIgnoreId(dbAccident, o -> o.setPosition(null)));
       // 测试 reason 不匹配
       accidentMapper.insert(cloneIgnoreId(dbAccident, o -> o.setReason(null)));
       // 测试 detail 不匹配
       accidentMapper.insert(cloneIgnoreId(dbAccident, o -> o.setDetail(null)));
       // 测试 createTime 不匹配
       accidentMapper.insert(cloneIgnoreId(dbAccident, o -> o.setCreateTime(null)));
       // 准备参数
       AccidentPageReqVO reqVO = new AccidentPageReqVO();
       reqVO.setPosition(null);
       reqVO.setReason(null);
       reqVO.setDetail(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       PageResult<AccidentDO> pageResult = accidentService.getAccidentPage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbAccident, pageResult.getList().get(0));
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetAccidentList() {
       // mock 数据
       AccidentDO dbAccident = randomPojo(AccidentDO.class, o -> { // 等会查询到
           o.setPosition(null);
           o.setReason(null);
           o.setDetail(null);
           o.setCreateTime(null);
       });
       accidentMapper.insert(dbAccident);
       // 测试 position 不匹配
       accidentMapper.insert(cloneIgnoreId(dbAccident, o -> o.setPosition(null)));
       // 测试 reason 不匹配
       accidentMapper.insert(cloneIgnoreId(dbAccident, o -> o.setReason(null)));
       // 测试 detail 不匹配
       accidentMapper.insert(cloneIgnoreId(dbAccident, o -> o.setDetail(null)));
       // 测试 createTime 不匹配
       accidentMapper.insert(cloneIgnoreId(dbAccident, o -> o.setCreateTime(null)));
       // 准备参数
       AccidentExportReqVO reqVO = new AccidentExportReqVO();
       reqVO.setPosition(null);
       reqVO.setReason(null);
       reqVO.setDetail(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       List<AccidentDO> list = accidentService.getAccidentList(reqVO);
       // 断言
       assertEquals(1, list.size());
       assertPojoEquals(dbAccident, list.get(0));
    }

}
