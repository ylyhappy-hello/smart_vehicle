package com.ncst.vehicle.module.system.dal.mysql.mail;

import com.ncst.vehicle.framework.common.pojo.PageResult;
import com.ncst.vehicle.framework.mybatis.core.mapper.BaseMapperX;
import com.ncst.vehicle.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.ncst.vehicle.framework.mybatis.core.query.QueryWrapperX;
import com.ncst.vehicle.module.system.controller.admin.mail.vo.account.MailAccountPageReqVO;
import com.ncst.vehicle.module.system.dal.dataobject.mail.MailAccountDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MailAccountMapper extends BaseMapperX<MailAccountDO> {

    default PageResult<MailAccountDO> selectPage(MailAccountPageReqVO pageReqVO) {
        return selectPage(pageReqVO, new LambdaQueryWrapperX<MailAccountDO>()
                .likeIfPresent(MailAccountDO::getMail, pageReqVO.getMail())
                .likeIfPresent(MailAccountDO::getUsername , pageReqVO.getUsername()));
    }

}
