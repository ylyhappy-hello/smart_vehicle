package com.ncst.vehicle.module.motocycleIllegal.service.illegalaction;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.annotation.Resource;

import com.ncst.vehicle.framework.test.core.ut.BaseDbUnitTest;

import com.ncst.vehicle.module.motocycleIllegal.controller.admin.illegalaction.vo.*;
import com.ncst.vehicle.module.motocycleIllegal.dal.dataobject.illegalaction.IllegalActionDO;
import com.ncst.vehicle.module.motocycleIllegal.dal.mysql.illegalaction.IllegalActionMapper;
import com.ncst.vehicle.framework.common.pojo.PageResult;

import javax.annotation.Resource;
import org.springframework.context.annotation.Import;
import java.util.*;
import java.time.LocalDateTime;

import static cn.hutool.core.util.RandomUtil.*;
import static com.ncst.vehicle.module.motocycleIllegal.enums.ErrorCodeConstants.*;
import static com.ncst.vehicle.framework.test.core.util.AssertUtils.*;
import static com.ncst.vehicle.framework.test.core.util.RandomUtils.*;
import static com.ncst.vehicle.framework.common.util.date.LocalDateTimeUtils.*;
import static com.ncst.vehicle.framework.common.util.object.ObjectUtils.*;
import static com.ncst.vehicle.framework.common.util.date.DateUtils.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * {@link IllegalActionServiceImpl} 的单元测试类
 *
 * @author ylyhappy
 */
@Import(IllegalActionServiceImpl.class)
public class IllegalActionServiceImplTest extends BaseDbUnitTest {

    @Resource
    private IllegalActionServiceImpl illegalActionService;

    @Resource
    private IllegalActionMapper illegalActionMapper;

    @Test
    public void testCreateIllegalAction_success() {
        // 准备参数
        IllegalActionCreateReqVO reqVO = randomPojo(IllegalActionCreateReqVO.class);

        // 调用
        Long illegalActionId = illegalActionService.createIllegalAction(reqVO);
        // 断言
        assertNotNull(illegalActionId);
        // 校验记录的属性是否正确
        IllegalActionDO illegalAction = illegalActionMapper.selectById(illegalActionId);
        assertPojoEquals(reqVO, illegalAction);
    }

    @Test
    public void testUpdateIllegalAction_success() {
        // mock 数据
        IllegalActionDO dbIllegalAction = randomPojo(IllegalActionDO.class);
        illegalActionMapper.insert(dbIllegalAction);// @Sql: 先插入出一条存在的数据
        // 准备参数
        IllegalActionUpdateReqVO reqVO = randomPojo(IllegalActionUpdateReqVO.class, o -> {
            o.setId(dbIllegalAction.getId()); // 设置更新的 ID
        });

        // 调用
        illegalActionService.updateIllegalAction(reqVO);
        // 校验是否更新正确
        IllegalActionDO illegalAction = illegalActionMapper.selectById(reqVO.getId()); // 获取最新的
        assertPojoEquals(reqVO, illegalAction);
    }

    @Test
    public void testUpdateIllegalAction_notExists() {
        // 准备参数
        IllegalActionUpdateReqVO reqVO = randomPojo(IllegalActionUpdateReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> illegalActionService.updateIllegalAction(reqVO), ILLEGAL_ACTION_NOT_EXISTS);
    }

    @Test
    public void testDeleteIllegalAction_success() {
        // mock 数据
        IllegalActionDO dbIllegalAction = randomPojo(IllegalActionDO.class);
        illegalActionMapper.insert(dbIllegalAction);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = dbIllegalAction.getId();

        // 调用
        illegalActionService.deleteIllegalAction(id);
       // 校验数据不存在了
       assertNull(illegalActionMapper.selectById(id));
    }

    @Test
    public void testDeleteIllegalAction_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> illegalActionService.deleteIllegalAction(id), ILLEGAL_ACTION_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetIllegalActionPage() {
       // mock 数据
       IllegalActionDO dbIllegalAction = randomPojo(IllegalActionDO.class, o -> { // 等会查询到
           o.setPosition(null);
           o.setType(null);
           o.setReason(null);
           o.setAuditResult(null);
           o.setPunish(null);
           o.setCreateTime(null);
       });
       illegalActionMapper.insert(dbIllegalAction);
       // 测试 position 不匹配
       illegalActionMapper.insert(cloneIgnoreId(dbIllegalAction, o -> o.setPosition(null)));
       // 测试 type 不匹配
       illegalActionMapper.insert(cloneIgnoreId(dbIllegalAction, o -> o.setType(null)));
       // 测试 reason 不匹配
       illegalActionMapper.insert(cloneIgnoreId(dbIllegalAction, o -> o.setReason(null)));
       // 测试 auditResult 不匹配
       illegalActionMapper.insert(cloneIgnoreId(dbIllegalAction, o -> o.setAuditResult(null)));
       // 测试 punish 不匹配
       illegalActionMapper.insert(cloneIgnoreId(dbIllegalAction, o -> o.setPunish(null)));
       // 测试 createTime 不匹配
       illegalActionMapper.insert(cloneIgnoreId(dbIllegalAction, o -> o.setCreateTime(null)));
       // 准备参数
       IllegalActionPageReqVO reqVO = new IllegalActionPageReqVO();
       reqVO.setPosition(null);
       reqVO.setType(null);
       reqVO.setReason(null);
       reqVO.setAuditResult(null);
       reqVO.setPunish(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       PageResult<IllegalActionDO> pageResult = illegalActionService.getIllegalActionPage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbIllegalAction, pageResult.getList().get(0));
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetIllegalActionList() {
       // mock 数据
       IllegalActionDO dbIllegalAction = randomPojo(IllegalActionDO.class, o -> { // 等会查询到
           o.setPosition(null);
           o.setType(null);
           o.setReason(null);
           o.setAuditResult(null);
           o.setPunish(null);
           o.setCreateTime(null);
       });
       illegalActionMapper.insert(dbIllegalAction);
       // 测试 position 不匹配
       illegalActionMapper.insert(cloneIgnoreId(dbIllegalAction, o -> o.setPosition(null)));
       // 测试 type 不匹配
       illegalActionMapper.insert(cloneIgnoreId(dbIllegalAction, o -> o.setType(null)));
       // 测试 reason 不匹配
       illegalActionMapper.insert(cloneIgnoreId(dbIllegalAction, o -> o.setReason(null)));
       // 测试 auditResult 不匹配
       illegalActionMapper.insert(cloneIgnoreId(dbIllegalAction, o -> o.setAuditResult(null)));
       // 测试 punish 不匹配
       illegalActionMapper.insert(cloneIgnoreId(dbIllegalAction, o -> o.setPunish(null)));
       // 测试 createTime 不匹配
       illegalActionMapper.insert(cloneIgnoreId(dbIllegalAction, o -> o.setCreateTime(null)));
       // 准备参数
       IllegalActionExportReqVO reqVO = new IllegalActionExportReqVO();
       reqVO.setPosition(null);
       reqVO.setType(null);
       reqVO.setReason(null);
       reqVO.setAuditResult(null);
       reqVO.setPunish(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       List<IllegalActionDO> list = illegalActionService.getIllegalActionList(reqVO);
       // 断言
       assertEquals(1, list.size());
       assertPojoEquals(dbIllegalAction, list.get(0));
    }

}
