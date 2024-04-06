package com.ncst.vehicle.module.motocycleIllegal.service.illegalauditlist;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.annotation.Resource;

import com.ncst.vehicle.framework.test.core.ut.BaseDbUnitTest;

import com.ncst.vehicle.module.motocycleIllegal.controller.admin.illegalauditlist.vo.*;
import com.ncst.vehicle.module.motocycleIllegal.dal.dataobject.illegalauditlist.IllegalAuditListDO;
import com.ncst.vehicle.module.motocycleIllegal.dal.mysql.illegalauditlist.IllegalAuditListMapper;
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
 * {@link IllegalAuditListServiceImpl} 的单元测试类
 *
 * @author ylyhappy
 */
@Import(IllegalAuditListServiceImpl.class)
public class IllegalAuditListServiceImplTest extends BaseDbUnitTest {

    @Resource
    private IllegalAuditListServiceImpl illegalAuditListService;

    @Resource
    private IllegalAuditListMapper illegalAuditListMapper;

    @Test
    public void testCreateIllegalAuditList_success() {
        // 准备参数
        IllegalAuditListCreateReqVO reqVO = randomPojo(IllegalAuditListCreateReqVO.class);

        // 调用
        Long illegalAuditListId = illegalAuditListService.createIllegalAuditList(reqVO);
        // 断言
        assertNotNull(illegalAuditListId);
        // 校验记录的属性是否正确
        IllegalAuditListDO illegalAuditList = illegalAuditListMapper.selectById(illegalAuditListId);
        assertPojoEquals(reqVO, illegalAuditList);
    }

    @Test
    public void testUpdateIllegalAuditList_success() {
        // mock 数据
        IllegalAuditListDO dbIllegalAuditList = randomPojo(IllegalAuditListDO.class);
        illegalAuditListMapper.insert(dbIllegalAuditList);// @Sql: 先插入出一条存在的数据
        // 准备参数
        IllegalAuditListUpdateReqVO reqVO = randomPojo(IllegalAuditListUpdateReqVO.class, o -> {
            o.setId(dbIllegalAuditList.getId()); // 设置更新的 ID
        });

        // 调用
        illegalAuditListService.updateIllegalAuditList(reqVO);
        // 校验是否更新正确
        IllegalAuditListDO illegalAuditList = illegalAuditListMapper.selectById(reqVO.getId()); // 获取最新的
        assertPojoEquals(reqVO, illegalAuditList);
    }

    @Test
    public void testUpdateIllegalAuditList_notExists() {
        // 准备参数
        IllegalAuditListUpdateReqVO reqVO = randomPojo(IllegalAuditListUpdateReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> illegalAuditListService.updateIllegalAuditList(reqVO), ILLEGAL_AUDIT_LIST_NOT_EXISTS);
    }

    @Test
    public void testDeleteIllegalAuditList_success() {
        // mock 数据
        IllegalAuditListDO dbIllegalAuditList = randomPojo(IllegalAuditListDO.class);
        illegalAuditListMapper.insert(dbIllegalAuditList);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = dbIllegalAuditList.getId();

        // 调用
        illegalAuditListService.deleteIllegalAuditList(id);
       // 校验数据不存在了
       assertNull(illegalAuditListMapper.selectById(id));
    }

    @Test
    public void testDeleteIllegalAuditList_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> illegalAuditListService.deleteIllegalAuditList(id), ILLEGAL_AUDIT_LIST_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetIllegalAuditListPage() {
       // mock 数据
       IllegalAuditListDO dbIllegalAuditList = randomPojo(IllegalAuditListDO.class, o -> { // 等会查询到
           o.setIlleageActionId(null);
           o.setStatus(null);
           o.setApplicantMobile(null);
           o.setInspectorMobile(null);
           o.setCreateTime(null);
       });
       illegalAuditListMapper.insert(dbIllegalAuditList);
       // 测试 illeageActionId 不匹配
       illegalAuditListMapper.insert(cloneIgnoreId(dbIllegalAuditList, o -> o.setIlleageActionId(null)));
       // 测试 status 不匹配
       illegalAuditListMapper.insert(cloneIgnoreId(dbIllegalAuditList, o -> o.setStatus(null)));
       // 测试 applicantMobile 不匹配
       illegalAuditListMapper.insert(cloneIgnoreId(dbIllegalAuditList, o -> o.setApplicantMobile(null)));
       // 测试 inspectorMobile 不匹配
       illegalAuditListMapper.insert(cloneIgnoreId(dbIllegalAuditList, o -> o.setInspectorMobile(null)));
       // 测试 createTime 不匹配
       illegalAuditListMapper.insert(cloneIgnoreId(dbIllegalAuditList, o -> o.setCreateTime(null)));
       // 准备参数
       IllegalAuditListPageReqVO reqVO = new IllegalAuditListPageReqVO();
       reqVO.setIlleageActionId(null);
       reqVO.setStatus(null);
       reqVO.setApplicantMobile(null);
       reqVO.setInspectorMobile(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       PageResult<IllegalAuditListDO> pageResult = illegalAuditListService.getIllegalAuditListPage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbIllegalAuditList, pageResult.getList().get(0));
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetIllegalAuditListList() {
       // mock 数据
       IllegalAuditListDO dbIllegalAuditList = randomPojo(IllegalAuditListDO.class, o -> { // 等会查询到
           o.setIlleageActionId(null);
           o.setStatus(null);
           o.setApplicantMobile(null);
           o.setInspectorMobile(null);
           o.setCreateTime(null);
       });
       illegalAuditListMapper.insert(dbIllegalAuditList);
       // 测试 illeageActionId 不匹配
       illegalAuditListMapper.insert(cloneIgnoreId(dbIllegalAuditList, o -> o.setIlleageActionId(null)));
       // 测试 status 不匹配
       illegalAuditListMapper.insert(cloneIgnoreId(dbIllegalAuditList, o -> o.setStatus(null)));
       // 测试 applicantMobile 不匹配
       illegalAuditListMapper.insert(cloneIgnoreId(dbIllegalAuditList, o -> o.setApplicantMobile(null)));
       // 测试 inspectorMobile 不匹配
       illegalAuditListMapper.insert(cloneIgnoreId(dbIllegalAuditList, o -> o.setInspectorMobile(null)));
       // 测试 createTime 不匹配
       illegalAuditListMapper.insert(cloneIgnoreId(dbIllegalAuditList, o -> o.setCreateTime(null)));
       // 准备参数
       IllegalAuditListExportReqVO reqVO = new IllegalAuditListExportReqVO();
       reqVO.setIlleageActionId(null);
       reqVO.setStatus(null);
       reqVO.setApplicantMobile(null);
       reqVO.setInspectorMobile(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       List<IllegalAuditListDO> list = illegalAuditListService.getIllegalAuditListList(reqVO);
       // 断言
       assertEquals(1, list.size());
       assertPojoEquals(dbIllegalAuditList, list.get(0));
    }

}
