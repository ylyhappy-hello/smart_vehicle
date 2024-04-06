package com.ncst.vehicle.module.motocycleIllegal.service.illegalwaringtemplate;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.annotation.Resource;

import com.ncst.vehicle.framework.test.core.ut.BaseDbUnitTest;

import com.ncst.vehicle.module.motocycleIllegal.controller.admin.illegalwaringtemplate.vo.*;
import com.ncst.vehicle.module.motocycleIllegal.dal.dataobject.illegalwaringtemplate.IllegalWaringTemplateDO;
import com.ncst.vehicle.module.motocycleIllegal.dal.mysql.illegalwaringtemplate.IllegalWaringTemplateMapper;
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
 * {@link IllegalWaringTemplateServiceImpl} 的单元测试类
 *
 * @author ylyhappy
 */
@Import(IllegalWaringTemplateServiceImpl.class)
public class IllegalWaringTemplateServiceImplTest extends BaseDbUnitTest {

    @Resource
    private IllegalWaringTemplateServiceImpl illegalWaringTemplateService;

    @Resource
    private IllegalWaringTemplateMapper illegalWaringTemplateMapper;

    @Test
    public void testCreateIllegalWaringTemplate_success() {
        // 准备参数
        IllegalWaringTemplateCreateReqVO reqVO = randomPojo(IllegalWaringTemplateCreateReqVO.class);

        // 调用
        Long illegalWaringTemplateId = illegalWaringTemplateService.createIllegalWaringTemplate(reqVO);
        // 断言
        assertNotNull(illegalWaringTemplateId);
        // 校验记录的属性是否正确
        IllegalWaringTemplateDO illegalWaringTemplate = illegalWaringTemplateMapper.selectById(illegalWaringTemplateId);
        assertPojoEquals(reqVO, illegalWaringTemplate);
    }

    @Test
    public void testUpdateIllegalWaringTemplate_success() {
        // mock 数据
        IllegalWaringTemplateDO dbIllegalWaringTemplate = randomPojo(IllegalWaringTemplateDO.class);
        illegalWaringTemplateMapper.insert(dbIllegalWaringTemplate);// @Sql: 先插入出一条存在的数据
        // 准备参数
        IllegalWaringTemplateUpdateReqVO reqVO = randomPojo(IllegalWaringTemplateUpdateReqVO.class, o -> {
            o.setId(dbIllegalWaringTemplate.getId()); // 设置更新的 ID
        });

        // 调用
        illegalWaringTemplateService.updateIllegalWaringTemplate(reqVO);
        // 校验是否更新正确
        IllegalWaringTemplateDO illegalWaringTemplate = illegalWaringTemplateMapper.selectById(reqVO.getId()); // 获取最新的
        assertPojoEquals(reqVO, illegalWaringTemplate);
    }

    @Test
    public void testUpdateIllegalWaringTemplate_notExists() {
        // 准备参数
        IllegalWaringTemplateUpdateReqVO reqVO = randomPojo(IllegalWaringTemplateUpdateReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> illegalWaringTemplateService.updateIllegalWaringTemplate(reqVO), ILLEGAL_WARING_TEMPLATE_NOT_EXISTS);
    }

    @Test
    public void testDeleteIllegalWaringTemplate_success() {
        // mock 数据
        IllegalWaringTemplateDO dbIllegalWaringTemplate = randomPojo(IllegalWaringTemplateDO.class);
        illegalWaringTemplateMapper.insert(dbIllegalWaringTemplate);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = dbIllegalWaringTemplate.getId();

        // 调用
        illegalWaringTemplateService.deleteIllegalWaringTemplate(id);
       // 校验数据不存在了
       assertNull(illegalWaringTemplateMapper.selectById(id));
    }

    @Test
    public void testDeleteIllegalWaringTemplate_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> illegalWaringTemplateService.deleteIllegalWaringTemplate(id), ILLEGAL_WARING_TEMPLATE_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetIllegalWaringTemplatePage() {
       // mock 数据
       IllegalWaringTemplateDO dbIllegalWaringTemplate = randomPojo(IllegalWaringTemplateDO.class, o -> { // 等会查询到
           o.setType(null);
           o.setStatus(null);
           o.setCode(null);
           o.setName(null);
           o.setContent(null);
           o.setParams(null);
           o.setRemark(null);
           o.setApiTemplateId(null);
           o.setChannelId(null);
           o.setChannelCode(null);
           o.setCreateTime(null);
       });
       illegalWaringTemplateMapper.insert(dbIllegalWaringTemplate);
       // 测试 type 不匹配
       illegalWaringTemplateMapper.insert(cloneIgnoreId(dbIllegalWaringTemplate, o -> o.setType(null)));
       // 测试 status 不匹配
       illegalWaringTemplateMapper.insert(cloneIgnoreId(dbIllegalWaringTemplate, o -> o.setStatus(null)));
       // 测试 code 不匹配
       illegalWaringTemplateMapper.insert(cloneIgnoreId(dbIllegalWaringTemplate, o -> o.setCode(null)));
       // 测试 name 不匹配
       illegalWaringTemplateMapper.insert(cloneIgnoreId(dbIllegalWaringTemplate, o -> o.setName(null)));
       // 测试 content 不匹配
       illegalWaringTemplateMapper.insert(cloneIgnoreId(dbIllegalWaringTemplate, o -> o.setContent(null)));
       // 测试 params 不匹配
       illegalWaringTemplateMapper.insert(cloneIgnoreId(dbIllegalWaringTemplate, o -> o.setParams(null)));
       // 测试 remark 不匹配
       illegalWaringTemplateMapper.insert(cloneIgnoreId(dbIllegalWaringTemplate, o -> o.setRemark(null)));
       // 测试 apiTemplateId 不匹配
       illegalWaringTemplateMapper.insert(cloneIgnoreId(dbIllegalWaringTemplate, o -> o.setApiTemplateId(null)));
       // 测试 channelId 不匹配
       illegalWaringTemplateMapper.insert(cloneIgnoreId(dbIllegalWaringTemplate, o -> o.setChannelId(null)));
       // 测试 channelCode 不匹配
       illegalWaringTemplateMapper.insert(cloneIgnoreId(dbIllegalWaringTemplate, o -> o.setChannelCode(null)));
       // 测试 createTime 不匹配
       illegalWaringTemplateMapper.insert(cloneIgnoreId(dbIllegalWaringTemplate, o -> o.setCreateTime(null)));
       // 准备参数
       IllegalWaringTemplatePageReqVO reqVO = new IllegalWaringTemplatePageReqVO();
       reqVO.setType(null);
       reqVO.setStatus(null);
       reqVO.setCode(null);
       reqVO.setName(null);
       reqVO.setContent(null);
       reqVO.setParams(null);
       reqVO.setRemark(null);
       reqVO.setApiTemplateId(null);
       reqVO.setChannelId(null);
       reqVO.setChannelCode(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       PageResult<IllegalWaringTemplateDO> pageResult = illegalWaringTemplateService.getIllegalWaringTemplatePage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbIllegalWaringTemplate, pageResult.getList().get(0));
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetIllegalWaringTemplateList() {
       // mock 数据
       IllegalWaringTemplateDO dbIllegalWaringTemplate = randomPojo(IllegalWaringTemplateDO.class, o -> { // 等会查询到
           o.setType(null);
           o.setStatus(null);
           o.setCode(null);
           o.setName(null);
           o.setContent(null);
           o.setParams(null);
           o.setRemark(null);
           o.setApiTemplateId(null);
           o.setChannelId(null);
           o.setChannelCode(null);
           o.setCreateTime(null);
       });
       illegalWaringTemplateMapper.insert(dbIllegalWaringTemplate);
       // 测试 type 不匹配
       illegalWaringTemplateMapper.insert(cloneIgnoreId(dbIllegalWaringTemplate, o -> o.setType(null)));
       // 测试 status 不匹配
       illegalWaringTemplateMapper.insert(cloneIgnoreId(dbIllegalWaringTemplate, o -> o.setStatus(null)));
       // 测试 code 不匹配
       illegalWaringTemplateMapper.insert(cloneIgnoreId(dbIllegalWaringTemplate, o -> o.setCode(null)));
       // 测试 name 不匹配
       illegalWaringTemplateMapper.insert(cloneIgnoreId(dbIllegalWaringTemplate, o -> o.setName(null)));
       // 测试 content 不匹配
       illegalWaringTemplateMapper.insert(cloneIgnoreId(dbIllegalWaringTemplate, o -> o.setContent(null)));
       // 测试 params 不匹配
       illegalWaringTemplateMapper.insert(cloneIgnoreId(dbIllegalWaringTemplate, o -> o.setParams(null)));
       // 测试 remark 不匹配
       illegalWaringTemplateMapper.insert(cloneIgnoreId(dbIllegalWaringTemplate, o -> o.setRemark(null)));
       // 测试 apiTemplateId 不匹配
       illegalWaringTemplateMapper.insert(cloneIgnoreId(dbIllegalWaringTemplate, o -> o.setApiTemplateId(null)));
       // 测试 channelId 不匹配
       illegalWaringTemplateMapper.insert(cloneIgnoreId(dbIllegalWaringTemplate, o -> o.setChannelId(null)));
       // 测试 channelCode 不匹配
       illegalWaringTemplateMapper.insert(cloneIgnoreId(dbIllegalWaringTemplate, o -> o.setChannelCode(null)));
       // 测试 createTime 不匹配
       illegalWaringTemplateMapper.insert(cloneIgnoreId(dbIllegalWaringTemplate, o -> o.setCreateTime(null)));
       // 准备参数
       IllegalWaringTemplateExportReqVO reqVO = new IllegalWaringTemplateExportReqVO();
       reqVO.setType(null);
       reqVO.setStatus(null);
       reqVO.setCode(null);
       reqVO.setName(null);
       reqVO.setContent(null);
       reqVO.setParams(null);
       reqVO.setRemark(null);
       reqVO.setApiTemplateId(null);
       reqVO.setChannelId(null);
       reqVO.setChannelCode(null);
       reqVO.setCreateTime(buildBetweenTime(2023, 2, 1, 2023, 2, 28));

       // 调用
       List<IllegalWaringTemplateDO> list = illegalWaringTemplateService.getIllegalWaringTemplateList(reqVO);
       // 断言
       assertEquals(1, list.size());
       assertPojoEquals(dbIllegalWaringTemplate, list.get(0));
    }

}
