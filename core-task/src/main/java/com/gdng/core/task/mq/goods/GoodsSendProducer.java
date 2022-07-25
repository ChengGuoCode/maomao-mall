package com.gdng.core.task.mq.goods;

import com.gdng.inner.api.task.dto.mq.GoodsSendDTO;
import com.gdng.support.common.util.JacksonUtil;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Auther: guocheng
 * @CreateDate: 2022/7/25 15:52
 * @Description:
 * @Version: 1.0.0
 */
@Component
public class GoodsSendProducer {

    private static final String TASK_SEND_GOODS_EXCHANGE = "task.send.goods.exchange";

    private final AmqpTemplate amqpTemplate;

    @Autowired
    public GoodsSendProducer(AmqpTemplate amqpTemplate) {
        this.amqpTemplate = amqpTemplate;
    }

    public void sendMsg(GoodsSendDTO goodsSendDTO) {
        this.amqpTemplate.convertAndSend(TASK_SEND_GOODS_EXCHANGE, null, JacksonUtil.anyToJson(goodsSendDTO));
    }

}
