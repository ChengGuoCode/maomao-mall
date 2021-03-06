package com.gdng.core.payment.service;

import com.gdng.inner.api.payment.dto.OrderPayReqDTO;
import com.gdng.inner.api.payment.dto.OrderPayResDTO;
import com.gdng.inner.api.task.dto.mq.PointSendDTO;

/**
 * @Auther: guocheng
 * @CreateDate: 2022/7/8 16:12
 * @Description:
 * @Version: 1.0.0
 */
public interface PaymentService {

    OrderPayResDTO pay(OrderPayReqDTO reqDTO);

    void sendPoint(PointSendDTO pointSendDTO);

}
