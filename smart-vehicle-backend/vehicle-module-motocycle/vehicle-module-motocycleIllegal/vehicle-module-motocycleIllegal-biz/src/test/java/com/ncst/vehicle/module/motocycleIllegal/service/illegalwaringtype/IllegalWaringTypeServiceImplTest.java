package com.ncst.vehicle.module.motocycleIllegal.service.illegalwaringtype;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.annotation.Resource;

import com.ncst.vehicle.framework.test.core.ut.BaseDbUnitTest;

import com.ncst.vehicle.module.motocycleIllegal.controller.admin.illegalwaringtype.vo.*;
import com.ncst.vehicle.module.motocycleIllegal.dal.dataobject.illegalwaringtype.IllegalWaringTypeDO;
import com.ncst.vehicle.module.motocycleIllegal.dal.mysql.illegalwaringtype.IllegalWaringTypeMapper;
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
 * {@link IllegalWaringTypeServiceImpl} 的单元测试类
 *
 * @author ylyhappy
 */
@Import(IllegalWaringTypeServiceImpl.class)
public class IllegalWaringTypeServiceImplTest extends BaseDbUnitTest {

    @Resource
    private IllegalWaringTypeServiceImpl illegalWaringTypeService;

    @Resource
    private IllegalWaringTypeMapper illegalWaringTypeMapper;

    @Test
    public void testCreateIllegalWaringType_success() {
        // 准备参数
        IllegalWaringTypeCreateReqVO reqVO = randomPojo(IllegalWaringTypeCreateReqVO.class);

        // 调用
        Long illegalWaringTypeId = illegalWaringTypeService.createIllegalWaringType(reqVO);
        // 断言
        assertNotNull(illegalWaringTypeId);
        // 校验记录的属性是否正确
        IllegalWaringTypeDO illegalWaringType = illegalWaringTypeMapper.selectById(illegalWaringTypeId);
        assertPojoEquals(reqVO, illegalWaringType);
    }

    @Test
    public void testUpdateIllegalWaringType_success() {
        // mock 数据
        IllegalWaringTypeDO dbIllegalWaringType = randomPojo(IllegalWaringTypeDO.class);
        illegalWaringTypeMapper.insert(dbIllegalWaringType);// @Sql: 先插入出一条存在的数据
        // 准备参数
        IllegalWaringTypeUpdateReqVO reqVO = randomPojo(IllegalWaringTypeUpdateReqVO.class, o -> {
            o.setId(dbIllegalWaringType.getId()); // 设置更新的 ID
        });

        // 调用
        illegalWaringTypeService.updateIllegalWaringType(reqVO);
        // 校验是否更新正确
        IllegalWaringTypeDO illegalWaringType = illegalWaringTypeMapper.selectById(reqVO.getId()); // 获取最新的
        assertPojoEquals(reqVO, illegalWaringType);
    }

    @Test
    public void testUpdateIllegalWaringType_notExists() {
        // 准备参数
        IllegalWaringTypeUpdateReqVO reqVO = randomPojo(IllegalWaringTypeUpdateReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> illegalWaringTypeService.updateIllegalWaringType(reqVO), ILLEGAL_WARING_TYPE_NOT_EXISTS);
    }

    @Test
    public void testDeleteIllegalWaringType_success() {
        // mock 数据
        IllegalWaringTypeDO dbIllegalWaringType = randomPojo(IllegalWaringTypeDO.class);
        illegalWaringTypeMapper.insert(dbIllegalWaringType);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = dbIllegalWaringType.getId();

        // 调用
        illegalWaringTypeService.deleteIllegalWaringType(id);
       // 校验数据不存在了
       assertNull(illegalWaringTypeMapper.selectById(id));
    }

    @Test
    public void testDeleteIllegalWaringType_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> illegalWaringTypeService.deleteIllegalWaringType(id), ILLEGAL_WARING_TYPE_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetIllegalWaringTypePage() {
       // mock 数据
       IllegalWaringTypeDO dbIllegalWaringType = randomPojo(IllegalWaringTypeDO.class, o -> { // 等会查询到
           o.setWaringId(null);
           o.setType(null);
           o.setPenalty(null);
           o.setDaysInDetention(null);
           o.setCreateTime(null);
       });
       illegalWaringTypeMapper.insert(dbIllegalWaringType);
       // 测试 waringId 不匹配
       illegalWaringTypeMapper.insert(cloneIgnoreId(dbIllegalWaringType, o -> o.setWaringId(null)));
       // 测试 type 不匹配
       illegalWaringTypeMapper.insert(cloneIgnoreId(dbIllegalWaringType, o -> o.setType(null)));
       // 测试 penalty 不匹配
       illegalWaringTypeMapper.insert(cloneIgnoreId(dbIllegalWaringType, o -> o.setPenalty(null)));
       // 测试 daysInDetention 不匹配
       illegalWaringTypeMapper.insert(cloneIgnoreId(dbIllegalWaringType, o -> o.setDaysInDetention(null)));
       // 测试 createTime 不匹配
       illegalWaringTypeMapper.insert(cloneIgnoreId(dbIllegalWaringType, o -> o.setCreateTime(null)));
       // 准备参数
       IllegalWaringTypePageReqVO reqVO = new IllegalWaringTypePageReqVO();
       reqVO.setWaringId(null);
       reqVO.setType(null);
       reqVO.setPenalty(null);
       reqVO.setDaysInDetention(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       PageResult<IllegalWaringTypeDO> pageResult = illegalWaringTypeService.getIllegalWaringTypePage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbIllegalWaringType, pageResult.getList().get(0));
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetIllegalWaringTypeList() {
       // mock 数据
       IllegalWaringTypeDO dbIllegalWaringType = randomPojo(IllegalWaringTypeDO.class, o -> { // 等会查询到
           o.setWaringId(null);
           o.setType(null);
           o.setPenalty(null);
           o.setDaysInDetention(null);
           o.setCreateTime(null);
       });
       illegalWaringTypeMapper.insert(dbIllegalWaringType);
       // 测试 waringId 不匹配
       illegalWaringTypeMapper.insert(cloneIgnoreId(dbIllegalWaringType, o -> o.setWaringId(null)));
       // 测试 type 不匹配
       illegalWaringTypeMapper.insert(cloneIgnoreId(dbIllegalWaringType, o -> o.setType(null)));
       // 测试 penalty 不匹配
       illegalWaringTypeMapper.insert(cloneIgnoreId(dbIllegalWaringType, o -> o.setPenalty(null)));
       // 测试 daysInDetention 不匹配
       illegalWaringTypeMapper.insert(cloneIgnoreId(dbIllegalWaringType, o -> o.setDaysInDetention(null)));
       // 测试 createTime 不匹配
       illegalWaringTypeMapper.insert(cloneIgnoreId(dbIllegalWaringType, o -> o.setCreateTime(null)));
       // 准备参数
       IllegalWaringTypeExportReqVO reqVO = new IllegalWaringTypeExportReqVO();
       reqVO.setWaringId(null);
       reqVO.setType(null);
       reqVO.setPenalty(null);
       reqVO.setDaysInDetention(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       List<IllegalWaringTypeDO> list = illegalWaringTypeService.getIllegalWaringTypeList(reqVO);
       // 断言
       assertEquals(1, list.size());
       assertPojoEquals(dbIllegalWaringType, list.get(0));
    }

}
