package com.ncst.vehicle.module.system.mq.consumer.notify;

import com.ncst.vehicle.framework.mq.core.pubsub.AbstractChannelMessageListener;
import com.ncst.vehicle.module.system.mq.message.notify.NotifyTemplateRefreshMessage;
import com.ncst.vehicle.module.system.service.notify.NotifyTemplateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 针对 {@link NotifyTemplateRefreshMessage} 的消费者
 *
 * @author xrcoder
 */
@Component
@Slf4j
public class NotifyTemplateRefreshConsumer extends AbstractChannelMessageListener<NotifyTemplateRefreshMessage> {

    @Resource
    private NotifyTemplateService notifyTemplateService;

    @Override
    public void onMessage(NotifyTemplateRefreshMessage message) {
        log.info("[onMessage][收到 NotifyTemplate 刷新消息]");
        notifyTemplateService.initLocalCache();
    }

}
