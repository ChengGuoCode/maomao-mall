package com.gdng.core.payment.mq.pay;

import com.gdng.inner.api.payment.dto.mq.PayCallbackDTO;
import com.gdng.support.common.util.JacksonUtil;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Auther: guocheng
 * @CreateDate: 2022/7/12 17:56
 * @Description:
 * @Version: 1.0.0
 */
@Component
public class PayCallbackProducer {

    private static final String PAY_CALLBACK_EXCHANGE = "pay.callback.exchange";

    private final AmqpTemplate amqpTemplate;

    @Autowired
    public PayCallbackProducer(AmqpTemplate amqpTemplate) {
        this.amqpTemplate = amqpTemplate;
    }

    public void sendMsg(PayCallbackDTO payCallbackDTO) {
        this.amqpTemplate.convertAndSend(PAY_CALLBACK_EXCHANGE, null, JacksonUtil.anyToJson(payCallbackDTO));
    }

}
