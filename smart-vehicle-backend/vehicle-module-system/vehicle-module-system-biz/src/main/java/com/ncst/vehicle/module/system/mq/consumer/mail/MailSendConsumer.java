package com.ncst.vehicle.module.system.mq.consumer.mail;

import com.ncst.vehicle.framework.mq.core.stream.AbstractStreamMessageListener;
import com.ncst.vehicle.module.system.mq.message.mail.MailSendMessage;
import com.ncst.vehicle.module.system.mq.message.sms.SmsSendMessage;
import com.ncst.vehicle.module.system.service.mail.MailSendService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 针对 {@link MailSendMessage} 的消费者
 *
 * @author 芋道源码
 */
@Component
@Slf4j
public class MailSendConsumer extends AbstractStreamMessageListener<MailSendMessage> {

    @Resource
    private MailSendService mailSendService;

    @Override
    public void onMessage(MailSendMessage message) {
        log.info("[onMessage][消息内容({})]", message);
        mailSendService.doSendMail(message);
    }

}
