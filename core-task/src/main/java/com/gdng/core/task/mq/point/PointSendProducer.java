package com.gdng.core.task.mq.point;

import com.gdng.inner.api.task.dto.mq.PointSendDTO;
import com.gdng.support.common.util.JacksonUtil;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Auther: guocheng
 * @CreateDate: 2022/7/25 15:40
 * @Description:
 * @Version: 1.0.0
 */
@Component
public class PointSendProducer {

    private static final String TASK_SEND_POINT_EXCHANGE = "task.send.point.exchange";

    private final AmqpTemplate amqpTemplate;

    @Autowired
    public PointSendProducer(AmqpTemplate amqpTemplate) {
        this.amqpTemplate = amqpTemplate;
    }

    public void sendMsg(PointSendDTO pointSendDTO) {
        this.amqpTemplate.convertAndSend(TASK_SEND_POINT_EXCHANGE, null, JacksonUtil.anyToJson(pointSendDTO));
    }

}
