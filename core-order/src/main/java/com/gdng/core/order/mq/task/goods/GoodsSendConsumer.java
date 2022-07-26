package com.gdng.core.order.mq.task.goods;

import com.gdng.core.order.service.OrderService;
import com.gdng.inner.api.task.dto.mq.GoodsSendDTO;
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
                value = @Queue(value = "task.send.goods.queue", autoDelete = "false"),
                exchange = @Exchange(value = "task.send.goods.exchange", type = ExchangeTypes.FANOUT))
)
public class GoodsSendConsumer {

    private static final Logger log = LoggerFactory.getLogger(GoodsSendConsumer.class);

    private final OrderService orderService;

    @Autowired
    public GoodsSendConsumer(OrderService orderService) {
        this.orderService = orderService;
    }

    @RabbitHandler(isDefault = true)
    public void process(@Payload String message, Channel channel, @Headers Map<String, Object> headers) {
        Long tag = (Long) headers.get(AmqpHeaders.DELIVERY_TAG);
        try {
            GoodsSendDTO goodsSendDTO = JacksonUtil.jsonToBean(message, GoodsSendDTO.class);
            orderService.sendGoods(goodsSendDTO);
            channel.basicAck(tag, false);
        } catch (Exception e) {
            log.error("任务赠送商品异常", e);
            boolean requeue = !(e instanceof GdngException);
            try {
                channel.basicNack(tag, false, requeue);
            } catch (IOException e1) {
                log.error("任务赠送商品消息重入队列异常", e1);
            }
        }
    }

}
