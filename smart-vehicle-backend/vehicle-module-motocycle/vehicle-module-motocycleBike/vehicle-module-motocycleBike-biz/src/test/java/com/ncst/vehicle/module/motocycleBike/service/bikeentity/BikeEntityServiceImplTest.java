package com.ncst.vehicle.module.motocycleBike.service.bikeentity;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.annotation.Resource;

import com.ncst.vehicle.framework.test.core.ut.BaseDbUnitTest;

import com.ncst.vehicle.module.motocycleBike.controller.admin.bikeentity.vo.*;
import com.ncst.vehicle.module.motocycleBike.dal.dataobject.bikeentity.BikeEntityDO;
import com.ncst.vehicle.module.motocycleBike.dal.mysql.bikeentity.BikeEntityMapper;
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
 * {@link BikeEntityServiceImpl} 的单元测试类
 *
 * @author ylyhappy
 */
@Import(BikeEntityServiceImpl.class)
public class BikeEntityServiceImplTest extends BaseDbUnitTest {

    @Resource
    private BikeEntityServiceImpl bikeEntityService;

    @Resource
    private BikeEntityMapper bikeEntityMapper;

    @Test
    public void testCreateBikeEntity_success() {
        // 准备参数
        BikeEntityCreateReqVO reqVO = randomPojo(BikeEntityCreateReqVO.class);

        // 调用
        Long bikeEntityId = bikeEntityService.createBikeEntity(reqVO);
        // 断言
        assertNotNull(bikeEntityId);
        // 校验记录的属性是否正确
        BikeEntityDO bikeEntity = bikeEntityMapper.selectById(bikeEntityId);
        assertPojoEquals(reqVO, bikeEntity);
    }

    @Test
    public void testUpdateBikeEntity_success() {
        // mock 数据
        BikeEntityDO dbBikeEntity = randomPojo(BikeEntityDO.class);
        bikeEntityMapper.insert(dbBikeEntity);// @Sql: 先插入出一条存在的数据
        // 准备参数
        BikeEntityUpdateReqVO reqVO = randomPojo(BikeEntityUpdateReqVO.class, o -> {
            o.setId(dbBikeEntity.getId()); // 设置更新的 ID
        });

        // 调用
        bikeEntityService.updateBikeEntity(reqVO);
        // 校验是否更新正确
        BikeEntityDO bikeEntity = bikeEntityMapper.selectById(reqVO.getId()); // 获取最新的
        assertPojoEquals(reqVO, bikeEntity);
    }

    @Test
    public void testUpdateBikeEntity_notExists() {
        // 准备参数
        BikeEntityUpdateReqVO reqVO = randomPojo(BikeEntityUpdateReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> bikeEntityService.updateBikeEntity(reqVO), BIKE_ENTITY_NOT_EXISTS);
    }

    @Test
    public void testDeleteBikeEntity_success() {
        // mock 数据
        BikeEntityDO dbBikeEntity = randomPojo(BikeEntityDO.class);
        bikeEntityMapper.insert(dbBikeEntity);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = dbBikeEntity.getId();

        // 调用
        bikeEntityService.deleteBikeEntity(id);
       // 校验数据不存在了
       assertNull(bikeEntityMapper.selectById(id));
    }

    @Test
    public void testDeleteBikeEntity_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> bikeEntityService.deleteBikeEntity(id), BIKE_ENTITY_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetBikeEntityPage() {
       // mock 数据
       BikeEntityDO dbBikeEntity = randomPojo(BikeEntityDO.class, o -> { // 等会查询到
           o.setIlleageActionId(null);
           o.setStatus(null);
           o.setCreateTime(null);
       });
       bikeEntityMapper.insert(dbBikeEntity);
       // 测试 illeageActionId 不匹配
       bikeEntityMapper.insert(cloneIgnoreId(dbBikeEntity, o -> o.setIlleageActionId(null)));
       // 测试 status 不匹配
       bikeEntityMapper.insert(cloneIgnoreId(dbBikeEntity, o -> o.setStatus(null)));
       // 测试 createTime 不匹配
       bikeEntityMapper.insert(cloneIgnoreId(dbBikeEntity, o -> o.setCreateTime(null)));
       // 准备参数
       BikeEntityPageReqVO reqVO = new BikeEntityPageReqVO();
       reqVO.setIlleageActionId(null);
       reqVO.setStatus(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       PageResult<BikeEntityDO> pageResult = bikeEntityService.getBikeEntityPage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbBikeEntity, pageResult.getList().get(0));
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetBikeEntityList() {
       // mock 数据
       BikeEntityDO dbBikeEntity = randomPojo(BikeEntityDO.class, o -> { // 等会查询到
           o.setIlleageActionId(null);
           o.setStatus(null);
           o.setCreateTime(null);
       });
       bikeEntityMapper.insert(dbBikeEntity);
       // 测试 illeageActionId 不匹配
       bikeEntityMapper.insert(cloneIgnoreId(dbBikeEntity, o -> o.setIlleageActionId(null)));
       // 测试 status 不匹配
       bikeEntityMapper.insert(cloneIgnoreId(dbBikeEntity, o -> o.setStatus(null)));
       // 测试 createTime 不匹配
       bikeEntityMapper.insert(cloneIgnoreId(dbBikeEntity, o -> o.setCreateTime(null)));
       // 准备参数
       BikeEntityExportReqVO reqVO = new BikeEntityExportReqVO();
       reqVO.setIlleageActionId(null);
       reqVO.setStatus(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       List<BikeEntityDO> list = bikeEntityService.getBikeEntityList(reqVO);
       // 断言
       assertEquals(1, list.size());
       assertPojoEquals(dbBikeEntity, list.get(0));
    }

}
