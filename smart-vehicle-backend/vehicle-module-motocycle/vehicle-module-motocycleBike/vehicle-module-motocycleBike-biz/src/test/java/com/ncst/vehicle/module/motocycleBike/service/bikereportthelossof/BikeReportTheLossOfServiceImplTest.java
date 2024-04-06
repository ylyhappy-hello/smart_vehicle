package com.ncst.vehicle.module.motocycleBike.service.bikereportthelossof;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.annotation.Resource;

import com.ncst.vehicle.framework.test.core.ut.BaseDbUnitTest;

import com.ncst.vehicle.module.motocycleBike.controller.admin.bikereportthelossof.vo.*;
import com.ncst.vehicle.module.motocycleBike.dal.dataobject.bikereportthelossof.BikeReportTheLossOfDO;
import com.ncst.vehicle.module.motocycleBike.dal.mysql.bikereportthelossof.BikeReportTheLossOfMapper;
import com.ncst.vehicle.framework.common.pojo.PageResult;

import javax.annotation.Resource;
import org.springframework.context.annotation.Import;
import java.util.*;
import java.time.LocalDateTime;

import static cn.hutool.core.util.RandomUtil.*;
import static com.ncst.vehicle.module.motocycleBike.enums.ErrorCodeConstants.*;
import static com.ncst.vehicle.framework.test.core.util.AssertUtils.*;
import static com.ncst.vehicle.framework.test.core.util.RandomUtils.*;
import static com.ncst.vehicle.framework.common.util.date.LocalDateTimeUtils.*;
import static com.ncst.vehicle.framework.common.util.object.ObjectUtils.*;
import static com.ncst.vehicle.framework.common.util.date.DateUtils.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * {@link BikeReportTheLossOfServiceImpl} 的单元测试类
 *
 * @author ylyhappy
 */
@Import(BikeReportTheLossOfServiceImpl.class)
public class BikeReportTheLossOfServiceImplTest extends BaseDbUnitTest {

    @Resource
    private BikeReportTheLossOfServiceImpl bikeReportTheLossOfService;

    @Resource
    private BikeReportTheLossOfMapper bikeReportTheLossOfMapper;

    @Test
    public void testCreateBikeReportTheLossOf_success() {
        // 准备参数
        BikeReportTheLossOfCreateReqVO reqVO = randomPojo(BikeReportTheLossOfCreateReqVO.class);

        // 调用
        Long bikeReportTheLossOfId = bikeReportTheLossOfService.createBikeReportTheLossOf(reqVO);
        // 断言
        assertNotNull(bikeReportTheLossOfId);
        // 校验记录的属性是否正确
        BikeReportTheLossOfDO bikeReportTheLossOf = bikeReportTheLossOfMapper.selectById(bikeReportTheLossOfId);
        assertPojoEquals(reqVO, bikeReportTheLossOf);
    }

    @Test
    public void testUpdateBikeReportTheLossOf_success() {
        // mock 数据
        BikeReportTheLossOfDO dbBikeReportTheLossOf = randomPojo(BikeReportTheLossOfDO.class);
        bikeReportTheLossOfMapper.insert(dbBikeReportTheLossOf);// @Sql: 先插入出一条存在的数据
        // 准备参数
        BikeReportTheLossOfUpdateReqVO reqVO = randomPojo(BikeReportTheLossOfUpdateReqVO.class, o -> {
            o.setId(dbBikeReportTheLossOf.getId()); // 设置更新的 ID
        });

        // 调用
        bikeReportTheLossOfService.updateBikeReportTheLossOf(reqVO);
        // 校验是否更新正确
        BikeReportTheLossOfDO bikeReportTheLossOf = bikeReportTheLossOfMapper.selectById(reqVO.getId()); // 获取最新的
        assertPojoEquals(reqVO, bikeReportTheLossOf);
    }

    @Test
    public void testUpdateBikeReportTheLossOf_notExists() {
        // 准备参数
        BikeReportTheLossOfUpdateReqVO reqVO = randomPojo(BikeReportTheLossOfUpdateReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> bikeReportTheLossOfService.updateBikeReportTheLossOf(reqVO), BIKE_REPORT_THE_LOSS_OF_NOT_EXISTS);
    }

    @Test
    public void testDeleteBikeReportTheLossOf_success() {
        // mock 数据
        BikeReportTheLossOfDO dbBikeReportTheLossOf = randomPojo(BikeReportTheLossOfDO.class);
        bikeReportTheLossOfMapper.insert(dbBikeReportTheLossOf);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = dbBikeReportTheLossOf.getId();

        // 调用
        bikeReportTheLossOfService.deleteBikeReportTheLossOf(id);
       // 校验数据不存在了
       assertNull(bikeReportTheLossOfMapper.selectById(id));
    }

    @Test
    public void testDeleteBikeReportTheLossOf_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> bikeReportTheLossOfService.deleteBikeReportTheLossOf(id), BIKE_REPORT_THE_LOSS_OF_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetBikeReportTheLossOfPage() {
       // mock 数据
       BikeReportTheLossOfDO dbBikeReportTheLossOf = randomPojo(BikeReportTheLossOfDO.class, o -> { // 等会查询到
           o.setReportTheLossOfUserid(null);
           o.setReportTheLossOfAuditid(null);
           o.setBikeId(null);
           o.setStatus(null);
           o.setCreateTime(null);
       });
       bikeReportTheLossOfMapper.insert(dbBikeReportTheLossOf);
       // 测试 reportTheLossOfUserid 不匹配
       bikeReportTheLossOfMapper.insert(cloneIgnoreId(dbBikeReportTheLossOf, o -> o.setReportTheLossOfUserid(null)));
       // 测试 reportTheLossOfAuditid 不匹配
       bikeReportTheLossOfMapper.insert(cloneIgnoreId(dbBikeReportTheLossOf, o -> o.setReportTheLossOfAuditid(null)));
       // 测试 bikeId 不匹配
       bikeReportTheLossOfMapper.insert(cloneIgnoreId(dbBikeReportTheLossOf, o -> o.setBikeId(null)));
       // 测试 status 不匹配
       bikeReportTheLossOfMapper.insert(cloneIgnoreId(dbBikeReportTheLossOf, o -> o.setStatus(null)));
       // 测试 createTime 不匹配
       bikeReportTheLossOfMapper.insert(cloneIgnoreId(dbBikeReportTheLossOf, o -> o.setCreateTime(null)));
       // 准备参数
       BikeReportTheLossOfPageReqVO reqVO = new BikeReportTheLossOfPageReqVO();
       reqVO.setReportTheLossOfUserid(null);
       reqVO.setReportTheLossOfAuditid(null);
       reqVO.setBikeId(null);
       reqVO.setStatus(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       PageResult<BikeReportTheLossOfDO> pageResult = bikeReportTheLossOfService.getBikeReportTheLossOfPage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbBikeReportTheLossOf, pageResult.getList().get(0));
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetBikeReportTheLossOfList() {
       // mock 数据
       BikeReportTheLossOfDO dbBikeReportTheLossOf = randomPojo(BikeReportTheLossOfDO.class, o -> { // 等会查询到
           o.setReportTheLossOfUserid(null);
           o.setReportTheLossOfAuditid(null);
           o.setBikeId(null);
           o.setStatus(null);
           o.setCreateTime(null);
       });
       bikeReportTheLossOfMapper.insert(dbBikeReportTheLossOf);
       // 测试 reportTheLossOfUserid 不匹配
       bikeReportTheLossOfMapper.insert(cloneIgnoreId(dbBikeReportTheLossOf, o -> o.setReportTheLossOfUserid(null)));
       // 测试 reportTheLossOfAuditid 不匹配
       bikeReportTheLossOfMapper.insert(cloneIgnoreId(dbBikeReportTheLossOf, o -> o.setReportTheLossOfAuditid(null)));
       // 测试 bikeId 不匹配
       bikeReportTheLossOfMapper.insert(cloneIgnoreId(dbBikeReportTheLossOf, o -> o.setBikeId(null)));
       // 测试 status 不匹配
       bikeReportTheLossOfMapper.insert(cloneIgnoreId(dbBikeReportTheLossOf, o -> o.setStatus(null)));
       // 测试 createTime 不匹配
       bikeReportTheLossOfMapper.insert(cloneIgnoreId(dbBikeReportTheLossOf, o -> o.setCreateTime(null)));
       // 准备参数
       BikeReportTheLossOfExportReqVO reqVO = new BikeReportTheLossOfExportReqVO();
       reqVO.setReportTheLossOfUserid(null);
       reqVO.setReportTheLossOfAuditid(null);
       reqVO.setBikeId(null);
       reqVO.setStatus(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       List<BikeReportTheLossOfDO> list = bikeReportTheLossOfService.getBikeReportTheLossOfList(reqVO);
       // 断言
       assertEquals(1, list.size());
       assertPojoEquals(dbBikeReportTheLossOf, list.get(0));
    }

}
