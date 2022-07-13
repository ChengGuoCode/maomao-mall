package com.gdng.core.order.mq.pay;

import com.gdng.core.order.service.OrderService;
import com.gdng.inner.api.payment.dto.mq.PayCallbackDTO;
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
 * @CreateDate: 2022/7/13 09:40
 * @Description:
 * @Version: 1.0.0
 */
@Component
@RabbitListener(
        bindings = @QueueBinding(
                value = @Queue(value = "pay.callback.queue", autoDelete = "false"),
                exchange = @Exchange(value = "pay.callback.exchange", type = ExchangeTypes.FANOUT))
)
public class PayCallbackConsumer {

    private static final Logger log = LoggerFactory.getLogger(PayCallbackConsumer.class);

    private final OrderService orderService;

    @Autowired
    public PayCallbackConsumer(OrderService orderService) {
        this.orderService = orderService;
    }

    @RabbitHandler(isDefault = true)
    public void process(@Payload String message, Channel channel, @Headers Map<String, Object> headers) {
        Long tag = (Long) headers.get(AmqpHeaders.DELIVERY_TAG);
        try {
            PayCallbackDTO payCallbackDTO = JacksonUtil.jsonToBean(message, PayCallbackDTO.class);
            orderService.payCallback(payCallbackDTO);
            channel.basicAck(tag, false);
        } catch (Exception e) {
            log.error("支付回调异常", e);
            boolean requeue = !(e instanceof GdngException);
            try {
                channel.basicNack(tag, false, requeue);
            } catch (IOException e1) {
                log.error("支付回调消息重入队列异常", e1);
            }
        }
    }

}
