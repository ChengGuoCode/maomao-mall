package com.gdng.core.payment.mq.task.point;

import com.gdng.core.payment.service.PaymentService;
import com.gdng.inner.api.task.dto.mq.PointSendDTO;
import com.gdng.support.common.exception.GdngException;
import com.gdng.support.common.util.JacksonUtil;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

/**
 * @Auther: guocheng
 * @CreateDate: 2022/7/25 16:25
 * @Description:
 * @Version: 1.0.0
 */
@Component
@RabbitListener(
        bindings = @QueueBinding(
                value = @Queue(value = "task.send.point.queue", autoDelete = "false"),
                exchange = @Exchange(value = "task.send.point.exchange", type = ExchangeTypes.FANOUT))
)
public class PointSendConsumer {

    private static final Logger log = LoggerFactory.getLogger(PointSendConsumer.class);

    private final PaymentService paymentService;

    @Autowired
    public PointSendConsumer(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @RabbitHandler(isDefault = true)
    public void process(@Payload String message, Channel channel, @Headers Map<String, Object> headers) {
        Long tag = (Long) headers.get(AmqpHeaders.DELIVERY_TAG);
        try {
            PointSendDTO pointSendDTO = JacksonUtil.jsonToBean(message, PointSendDTO.class);
            paymentService.sendPoint(pointSendDTO);
            channel.basicAck(tag, false);
        } catch (Exception e) {
            log.error("任务赠送积分异常", e);
            boolean requeue = !(e instanceof GdngException);
            try {
                channel.basicNack(tag, false, requeue);
            } catch (IOException e1) {
                log.error("任务赠送积分消息重入队列异常", e1);
            }
        }
    }

}
