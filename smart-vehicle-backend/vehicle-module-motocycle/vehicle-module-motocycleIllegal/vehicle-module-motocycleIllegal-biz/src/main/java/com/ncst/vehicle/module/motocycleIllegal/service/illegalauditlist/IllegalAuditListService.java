package com.ncst.vehicle.module.motocycleIllegal.service.illegalauditlist;

import java.util.*;
import javax.validation.*;
import com.ncst.vehicle.module.motocycleIllegal.controller.admin.illegalauditlist.vo.*;
import com.ncst.vehicle.module.motocycleIllegal.dal.dataobject.illegalauditlist.IllegalAuditListDO;
import com.ncst.vehicle.framework.common.pojo.PageResult;

/**
 * 违规审核 Service 接口
 *
 * @author ylyhappy
 */
public interface IllegalAuditListService {

    /**
     * 创建违规审核
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createIllegalAuditList(@Valid IllegalAuditListCreateReqVO createReqVO);

    /**
     * 更新违规审核
     *
     * @param updateReqVO 更新信息
     */
    void updateIllegalAuditList(@Valid IllegalAuditListUpdateReqVO updateReqVO);

    /**
     * 删除违规审核
     *
     * @param id 编号
     */
    void deleteIllegalAuditList(Long id);

    /**
     * 获得违规审核
     *
     * @param id 编号
     * @return 违规审核
     */
    IllegalAuditListDO getIllegalAuditList(Long id);

    /**
     * 获得违规审核列表
     *
     * @param ids 编号
     * @return 违规审核列表
     */
    List<IllegalAuditListDO> getIllegalAuditListList(Collection<Long> ids);

    /**
     * 获得违规审核分页
     *
     * @param pageReqVO 分页查询
     * @return 违规审核分页
     */
    PageResult<IllegalAuditListDO> getIllegalAuditListPage(IllegalAuditListPageReqVO pageReqVO);

    /**
     * 获得违规审核列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 违规审核列表
     */
    List<IllegalAuditListDO> getIllegalAuditListList(IllegalAuditListExportReqVO exportReqVO);

}
