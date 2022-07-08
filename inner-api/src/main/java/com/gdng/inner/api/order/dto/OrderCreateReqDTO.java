package com.gdng.inner.api.order.dto;

import java.util.List;

/**
 * @Auther: guocheng
 * @CreateDate: 2022/7/8 14:03
 * @Description:
 * @Version: 1.0.0
 */
public class OrderCreateReqDTO {

    private Integer orderSource;
    private List<OrderItemDTO> orderItemList;

    public Integer getOrderSource() {
        return orderSource;
    }

    public void setOrderSource(Integer orderSource) {
        this.orderSource = orderSource;
    }

    public List<OrderItemDTO> getOrderItemList() {
        return orderItemList;
    }

    public void setOrderItemList(List<OrderItemDTO> orderItemList) {
        this.orderItemList = orderItemList;
    }
}
